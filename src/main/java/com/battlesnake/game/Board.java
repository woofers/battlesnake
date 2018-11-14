package com.battlesnake.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.battlesnake.game.math.MovePoint;
import com.battlesnake.game.math.Point;
import com.battlesnake.game.snake.Snake;

/**
 * @author Ben Austin
 * @author Jaxson Van Doorn
 * @author Zak White
 */
public class Board {
    private static final int EMPTY = 0;
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
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(point.getX() - 1, point.getY()));
        list.add(new Point(point.getX() + 1, point.getY()));
        list.add(new Point(point.getX(), point.getY() - 1));
        list.add(new Point(point.getX(), point.getY() + 1));
        return list;
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
        ArrayList<MovePoint> list = new ArrayList<>();
        Point up = Move.up.translate(point.point());
        Point down = Move.down.translate(point.point());
        Point left = Move.left.translate(point.point());
        Point right = Move.right.translate(point.point());
        Move initial = point.initialMove();

        if (!isFilled(up)) {
            list.add(new MovePoint(Move.up, up, initial != null ? initial : Move.up));
        }
        if (!isFilled(down)) {
            list.add(new MovePoint(Move.down, down, initial != null ? initial : Move.down));
        }
        if (!isFilled(left)) {
            list.add(new MovePoint(Move.left, left, initial != null ? initial : Move.left));
        }
        if (!isFilled(right)) {
            list.add(new MovePoint(Move.right, right, initial != null ? initial : Move.right));
        }
        return list;
    }

    public Move goToAttack(Point currentPoint) {
        return findPath(findHeads(), currentPoint);
    }

    public Move goToFood(Point currentPoint) {
        return findPath(findBestFood(), currentPoint);
    }

    public Move goToTail(Point currentPoint) {
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

    public void init(Snake you) {
        this.you = you;

        removeDead();
        toGrid();
        fillInBoxes();

        System.out.println(toRegionString());
    }

    public boolean isFilled(Point point) {
        return isFilled(point, board);
    }

    private boolean isFilled(Point point, int[][] board) {
        if (!exists(point)) return true;
        return board[point.getX()][point.getY()] != EMPTY
            && board[point.getX()][point.getY()] != FOOD;
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
        System.out.println(toString());
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
            for (Point bodyPart : body) {
                board[bodyPart.getX()][bodyPart.getY()] = WALL;
            }

            if (snake.equals(you())) {
                board[head.getX()][head.getY()] = ME;
            } else {
                board[head.getX()][head.getY()] = HEADS;

                if (!you().longerThan(snake)) {
                    List<Point> around = findAdjacent(head);
                    for (Point point : around) {
                        if (exists(point)) {
                            board[point.getX()][point.getY()] = WALL;
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
        String value = "";
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
}
