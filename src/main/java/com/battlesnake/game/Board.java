package com.battlesnake.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import com.battlesnake.game.math.MovePoint;
import com.battlesnake.game.math.Point;
import com.battlesnake.game.snake.Snake;

@Log4j2
@Accessors(fluent = true)
public class Board {

    public static enum Tile {
        EMPTY,
        WALL,
        ME,
        HEADS,
        FOOD,
        FAKE_WALL,
        TAIL;

        public String toString() {
            return Integer.valueOf(this.ordinal()).toString();
        }
    }

    private static interface Exit {
        public boolean shouldExit(MovePoint point, Point initial);

        public List<MovePoint> onFailure(List<MovePoint> path);
    }

    private static final int FUDGE_FACTOR = 2;
    private static final int IGNORE_SIZE = 4;

    private transient Tile[][] board;

    private transient Integer[][] regions;

    private List<Point> food;

    @Getter private int height;
    private List<Snake> snakes;

    @Getter private int width;
    @Getter private transient Snake you;

    @Getter private transient int turn;
    private transient String gameId;

    public boolean exists(Point point) {
        if (point.x() < 0) return false;
        if (point.y() < 0) return false;
        if (point.x() > width() - 1) return false;
        if (point.y() > height() - 1) return false;
        return true;
    }

    private List<Point> findAdjacent(Point point) {
        return new ArrayList<>(Move.adjacent(point).values());
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

    private void fill(Point point) {
        if (!exists(point)) return;
        regions[point.x()][point.y()] = 0;
    }

    private void fillIn() {
        this.regions = new Integer[width()][height()];
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (isFilled(new Point(x, y))) {
                    regions[x][y] = 0;
                }
            }
        }
        for (Snake snake : snakes) {
            if (snake.equals(you()) || snake.length() <= 1) continue;
            Point head = snake.head();
            Point neck = snake.body().get(1);
            Point delta = head.delta(neck);
            for (int i = 1; i <= FUDGE_FACTOR; i ++) {
                fill(new Point(head.x() + delta.x() * i, head.y() + delta.y() * i));
            }
        }
        Exit condition = new Exit() {
            public boolean shouldExit(MovePoint point, Point initial) {
                return false;
            }

            public List<MovePoint> onFailure(List<MovePoint> path) {
                return path;
            }
        };
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (regions[x][y] != null) continue;
                List<MovePoint> region = floodFill(new Point(x, y), condition, false);
                for (MovePoint point : region) {
                    regions[point.point().x()][point.point().y()] = region.size();
                }
            }
        }
    }

    protected Move findPath(List<Point> destinations, Point point) {
        return findPath(destinations, point, true);
    }

    protected Move findPath(List<Point> destinations, Point point, boolean checkBox) {
        for (int i = 0; i < destinations.size(); i++) {
            if (destinations.get(i).equals(point)) {
                destinations.remove(i);
                i--;
            }
        }
        log.info("Starting path-finding to {}",
                 destinations.stream()
                 .map(Object::toString)
                 .collect(Collectors.joining(", "))
        );
        Exit condition = new Exit() {
            public boolean shouldExit(MovePoint point, Point initial) {
                for (Point destination : destinations) {
                    if (point.point().equals(destination)) {
                        log.info(
                            "Found path to {} with a distance of {} by moving {}",
                            point,
                            point.length(),
                            point.initialMove());
                            int smallRegion = Math.max(IGNORE_SIZE, (int)Math.floor(you().length() / 2));
                            Point newPoint = point.initialMove().translate(initial);
                            int region = regionSize(newPoint);
                            if (checkBox && region <= smallRegion) {
                                log.info(
                                    "Rejecting moving {} since it results in moving into a "
                                  + "bad region of size {} when we need at least {}",
                                    point.initialMove(),
                                    region,
                                    smallRegion + 1);
                                return false;
                            }
                        return true;
                    }
                }
                return false;
            }

            public List<MovePoint> onFailure(List<MovePoint> path) {
                return new ArrayList<MovePoint>();
            }
        };
        List<MovePoint> path = floodFill(point, condition, true);
        if (path.isEmpty()) return null;
        return path.get(path.size() - 1).initialMove();
    }

    protected List<MovePoint> floodFill(Point point, Exit condition, boolean excludeDanger) {
        LinkedList<MovePoint> points = new LinkedList<>();
        ArrayList<MovePoint> list = new ArrayList<>();
        ArrayList<MovePoint> visited = new ArrayList<>();

        MovePoint loopPoint = new MovePoint(null, point, null);
        points.add(loopPoint);
        list.add(loopPoint);
        while (!points.isEmpty()) {
            loopPoint = points.pollFirst();
            visited.add(loopPoint);
            if (condition.shouldExit(loopPoint, point)) {
                return visited;
            }
            List<MovePoint> moves = getPossibleMoves(loopPoint, excludeDanger);
            for (MovePoint move : moves) {
                move.length(loopPoint.length() + 1);
                if (list.contains(move)) continue;
                points.add(move);
                list.add(move);
            }
        }
        return condition.onFailure(visited);
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
        if (moves.isEmpty()) return Move.fallback();
        for (MovePoint move: moves) {
            if (isFood(move.point())) return move.move();
        }
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
            move = findPath(findAdjacent(you().body().get(i)), currentPoint, false);
            if (move != null) return move;
        }
        return null;
    }

    public void init(Game state) {
        this.you = state.you();
        this.turn = state.turn();
        this.gameId = state.id();

        removeDead();
        toGrid();
        fillIn();
    }

    public boolean isDangerousSpotFilled(Point point) {
        if (!exists(point)) return false;
        return board[point.x()][point.y()] == Tile.FAKE_WALL
            || board[point.x()][point.y()] == Tile.TAIL;
    }

    public boolean isFood(Point point) {
        if (!exists(point)) return false;
        return board[point.x()][point.y()] == Tile.FOOD;
    }

    public boolean isFilled(Point point) {
        return isFilled(point, board);
    }

    private boolean isFilled(Point point, Tile[][] board) {
        if (!exists(point)) return true;
        return board[point.x()][point.y()] != Tile.EMPTY
            && board[point.x()][point.y()] != Tile.FOOD
            && board[point.x()][point.y()] != Tile.FAKE_WALL
            && board[point.x()][point.y()] != Tile.TAIL;
    }

    public int regionSize(Point point) {
        if (!exists(point)) return 0;
        return regions[point.x()][point.y()];
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

    public Move move() {
        return you().move(this);
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
        this.board = new Tile[width()][height()];
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                board[x][y] = Tile.EMPTY;
            }
        }

        for (Point snack : food) {
            board[snack.x()][snack.y()] = Tile.FOOD;
        }

        for (Snake snake : snakes) {
            List<Point> body = snake.body();
            Point head = body.get(0);
            for (int i = 0; i < body.size(); i++) {
                if ((i == body.size() - 1)
                    && body.size() > 1
                    && !snake.justAte()) {
                    board[body.get(i).x()][body.get(i).y()] = Tile.TAIL;
                }
                else {
                    board[body.get(i).x()][body.get(i).y()] = Tile.WALL;
                }
            }

            if (snake.equals(you())) {
                board[head.x()][head.y()] = Tile.ME;
            }
            else {
                board[head.x()][head.y()] = Tile.HEADS;

                if (!you().longerThan(snake)) {
                    List<Point> around = findAdjacent(head);
                    for (Point point : around) {
                        if (exists(point)) {
                            if (board[point.x()][point.y()] == Tile.EMPTY
                             || board[point.x()][point.y()] == Tile.FOOD) {
                                board[point.x()][point.y()] = Tile.FAKE_WALL;
                            }
                        }
                    }
                }
            }
        }
    }

    public String toRegionString() {
        return toString(regions);
    }

    @Override
    public String toString() {
        return toString(board);
    }

    public String toString(Object[][] board) {
        String out = String.format("Turn %s\n", turn);
        int[] padding = new int[width()];
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                int length = String.valueOf(board[x][y]).length();
                if (length > padding[x]) padding[x] = length;
            }
        }
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                String value = String.valueOf(board[x][y]);
                int diff = padding[x] - value.length();
                if (diff > 0) {
                    for (int i = 0; i < diff; i ++) {
                        out += " ";
                    }
                }
                out += value;
                out += " ";
            }
            out += "\n";
        }
        return out;
    }
}
