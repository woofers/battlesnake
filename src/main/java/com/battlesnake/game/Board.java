package com.battlesnake.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.battlesnake.game.math.MovePoint;
import com.battlesnake.game.math.Point;
import com.battlesnake.game.snake.Snake;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author Ben Austin
 * @author Jaxson Van Doorn
 * @author Zak White
 */
public class Board {
    private static Logger log = LogManager.getLogger();

    private static final int EMPTY = 0;
    private static final int TAIL = 6;
    private static final int FAKE_WALL = 5;

    private static final int FOOD = 4;

    private static final int HEADS = 3;

    private static final int ME = 2;
    private static final int WALL = 1;

    private transient int[][] board;

    private List<Point> food;

    private int height;
    private transient int[][] region;
    private List<Snake> snakes;

    private int width;
    private transient Snake you;

    private transient int turn;
    private transient String gameId;

    public boolean exists(Point point) {
        if (point.getX() < 0) return false;
        if (point.getY() < 0) return false;
        if (point.getX() > width() - 1) return false;
        if (point.getY() > height() - 1) return false;
        return true;
    }

    private void fillInBoxes() {
        this.region = new int[width()][height()];

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, region[i], 0, board[i].length);
        }

        int startId = 10;

        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                Point currentPoint = new Point(x, y);
                if (!isRegionFilled(currentPoint)) {
                    LinkedList<MovePoint> points = new LinkedList<>();
                    ArrayList<MovePoint> list = new ArrayList<>();

                    MovePoint loopPoint = new MovePoint(null, currentPoint,
                        null);
                    points.add(loopPoint);
                    list.add(loopPoint);
                    while (!points.isEmpty()) {
                        loopPoint = points.pollFirst();
                        List<MovePoint> moves = getPossibleMoves(loopPoint);
                        for (MovePoint move : moves) {
                            if (list.contains(move)) {
                                continue;
                            }
                            points.add(move);
                            list.add(move);
                            region[move.point().getX()][move.point()
                                .getY()] = startId;
                        }
                    }
                    startId++;
                }
            }
        }
    }

    private List<Point> findAdjacent(Point point) {
        return new ArrayList<>(Move.adjacent(point).values());
    }

    protected List<Point> findBestAttackPoint() {
        ArrayList<Point> list = new ArrayList<>();
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (board[x][y] == HEADS) {
                    list.add(new Point(x, y));
                }
            }
        }
        return list;
    }

    protected List<Point> findBestFood() {
        return food;
    }

    private List<Point> findHeads() {
        ArrayList<Point> list = new ArrayList<>();
        for (Snake snake : snakes) {
            if (!snake.equals(you())) {
                list.addAll(findAdjacent(snake.body().get(0)));
                list.add(snake.head());
            }
        }
        return list;

    }

    private List<Point> findOurTail() {
        return findAdjacent(you().body().get(you().body().size() - 1));
    }

    protected Move findPath(List<Point> destinations, Point currentPoint) {
        LinkedList<MovePoint> points = new LinkedList<>();
        ArrayList<MovePoint> list = new ArrayList<>();

        for (int i = 0; i < destinations.size(); i++) {
            if (destinations.get(i).equals(currentPoint)) {
                destinations.remove(i);
                i--;
            }
        }

        MovePoint loopPoint = new MovePoint(null, currentPoint, null);
        points.add(loopPoint);
        list.add(loopPoint);
        while (!points.isEmpty()) {
            loopPoint = points.pollFirst();
            for (Point destination : destinations) {
                if (loopPoint.point()
                    .equals(destination)) return loopPoint.initialMove();
            }
            List<MovePoint> moves = getPossibleMoves(loopPoint);
            for (MovePoint move : moves) {
                if (list.contains(move)) {
                    continue;
                }
                points.add(move);
                list.add(move);
            }
        }

        return null;
    }

    protected List<Point> findSafestPoint() {
        ArrayList<Point> list = new ArrayList<>();
        for (Snake snake : snakes) {
            list.add(snake.body().get(snake.body().size()));
        }
        return list;
    }

    private List<MovePoint> getPossibleMoves(MovePoint point) {
        return getPossibleMoves(point, true);
    }

    private List<MovePoint> getPossibleMoves(MovePoint point, boolean excludeDanger) {
        ArrayList<MovePoint> moves = new ArrayList<>();
        Move initial = point.initialMove();
        for (Entry<Move, Point> move: Move.adjacent(point.point()).entrySet()) {
            if (movable(move.getValue(), excludeDanger)) {
                moves.add(new MovePoint(
                              move.getKey(),
                              move.getValue(),
                              initial != null ? initial : move.getKey()
                         )
                );
            }
        }
        return moves;
    }

    public Move goToFallback(Point point) {
        log.info("Falling back to dangerous moves");
        List<MovePoint> moves = getPossibleMoves(new MovePoint(null, point, null), false);
        if (moves.isEmpty()) return Move.left;
        return moves.get(0).move();
    }

    private boolean movable(Point point, boolean excludeDanger) {
        return !isFilled(point)
            && (excludeDanger ? !isDangerousSpotFilled(point) : true);
    }

    public Move goToAttack(Point currentPoint) {
        log.info("Attacking");
        return findPath(findHeads(), currentPoint);
    }

    public Move goToFood(Point currentPoint) {
        log.info("Eating");
        return findPath(findBestFood(), currentPoint);
    }

    public Move goToTail(Point currentPoint) {
        log.info("Going to tail");
        Move move = null;
        for (int i = you().body().size() - 1; i > 0; i--) {
            move = findPath(findAdjacent(you().body().get(i)), currentPoint);
            if (move != null) return move;
        }
        return null;
    }

    public int height() {
        return height;
    }

    public void init(Game state) {
        this.you = state.you();
        this.turn = state.turn();
        this.gameId = state.id();

        removeDead();
        toGrid();
    }

    public boolean isDangerousSpotFilled(Point point) {
        if (!exists(point)) return false;
        return board[point.getX()][point.getY()] == FAKE_WALL
            || board[point.getX()][point.getY()] == TAIL;
    }

    public boolean isFilled(Point point) {
        return isFilled(point, board);
    }

    private boolean isFilled(Point point, int[][] board) {
        if (!exists(point)) return true;
        return board[point.getX()][point.getY()] != EMPTY
            && board[point.getX()][point.getY()] != FOOD
            && board[point.getX()][point.getY()] != FAKE_WALL
            && board[point.getX()][point.getY()] != TAIL;
    }

    public boolean isRegionFilled(Point point) {
        return isFilled(point, region);
    }

    public int longestSnakeLength() {
        int max = Integer.MIN_VALUE;
        for (Snake snake : snakes) {
            if (!snake.equals(you()) && snake.length() > max) {
                max = snake.length();
            }
        }
        return max;
    }

    public BoardMove gameMove() {
        return you().gameMove(this);
    }

    public void print() {
        log.info(toString());
    }

    private void removeDead() {
        for (int i = 0; i < snakes.size(); i++) {
            if (snakes.get(i).isDead()) {
                snakes.remove(i);
                i--;
            }
        }
    }

    private void toGrid() {
        this.board = new int[width()][height()];

        for (Point snack : food) {
            board[snack.getX()][snack.getY()] = FOOD;
        }

        for (Snake snake : snakes) {
            List<Point> body = snake.body();
            Point head = body.get(0);
            for (int i = 0; i < body.size(); i++) {
                if ((i == body.size() - 1) && body.size() > 1) {
                    board[body.get(i).getX()][body.get(i).getY()] = TAIL;
                }
                else {
                    board[body.get(i).getX()][body.get(i).getY()] = WALL;
                }
            }

            if (snake.equals(you())) {
                board[head.getX()][head.getY()] = ME;
            } else {
                board[head.getX()][head.getY()] = HEADS;

                if (!you().longerThan(snake)) {
                    List<Point> around = findAdjacent(head);
                    for (Point point : around) {
                        if (exists(point)) {
                            board[point.getX()][point.getY()] = FAKE_WALL;
                        }
                    }
                }
            }
        }
    }

    public String toRegionString() {
        return toString(region);
    }

    @Override
    public String toString() {
        return toString(board);
    }

    public String toString(int[][] board) {
        String value = String.format("Turn %s\n", turn);
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                value += board[x][y];
                value += " ";
            }
            value += "\n";
        }
        return value;
    }

    public int width() {
        return width;
    }

    public Snake you() {
        return you;
    }

    public int turn() {
        return turn;
    }
}
