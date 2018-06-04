package com.battlesnake.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.battlesnake.game.data.Move;
import com.battlesnake.game.math.Point;
import com.battlesnake.http.request.MoveRequest;

/**
 * @author Ben Austin
 * @author Jaxson Van Doorn
 * @author Zak White
 */
public class Board
{
    private static final int EMPTY = 0;
    private static final int WALL = 1;

    private static final int ME = 2;

    private static final int HEADS = 3;

    private static final int FOOD = 4;
    private static final int FAKE_WALL = 5;

    private String id;

    private int turn;

    private SmartSnake you;

    private List<SmartSnake> snakes;

    private int width;
    private int height;
    private List<Point> food;
    private int[][] board;
    private int[][] region;
    private final int INFINITY = Integer.MAX_VALUE;

    public Board(MoveRequest request)
    {
        this.id = request.getId();
        this.turn = request.getTurn();
        this.you = new SmartSnake(request.getYou(), turn);
        this.snakes = new ArrayList<>();
        List<Snake> oldSnakes = request.getSnakes();
        for (Snake snake: oldSnakes)
        {
            this.snakes.add(new SmartSnake(snake, turn));
        }
        this.width = request.getWidth();
        this.height = request.getHeight();
        this.food = request.getFood();

        removeDead();
        toGrid();
        fillInBoxes();

        System.out.println(toRegionString());
    }

    public boolean exists(Point point)
    {
        if (point.getX() < 0) return false;
        if (point.getY() < 0) return false;
        if (point.getX() > width() - 1) return false;
        if (point.getY() > height() - 1) return false;
        return true;
    }

    private void fillInBoxes()
    {
        this.region = new int[width()][height()];

        for (int i = 0; i < board.length; i ++)
        {
            System.arraycopy(board[i], 0, region[i], 0, board[i].length);
        }

        int startId = 10;

        for (int y = 0; y < height(); y ++)
        {
            for (int x = 0; x < width(); x ++)
            {
                Point currentPoint = new Point(x, y);
                if (!isRegionFilled(currentPoint))
                {
                    LinkedList<MovePoint> points = new LinkedList<>();
                    ArrayList<MovePoint> list = new ArrayList<>();

                    MovePoint loopPoint
                            = new MovePoint(null, currentPoint, null);
                    points.add(loopPoint);
                    list.add(loopPoint);
                    while (!points.isEmpty())
                    {
                        loopPoint = points.pollFirst();
                        List<MovePoint> moves = getPossibleMoves(loopPoint);
                        for (MovePoint move: moves)
                        {
                            if (list.contains(move)) continue;
                            points.add(move);
                            list.add(move);
                            region[move.point().getX()][move.point().getY()]
                                    = startId;
                        }
                    }
                    startId ++;
                }
            }
        }
    }

    private List<Point> findAdjacent(Point point)
    {
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(point.getX() - 1, point.getY()));
        list.add(new Point(point.getX() + 1, point.getY()));
        list.add(new Point(point.getX(), point.getY() - 1));
        list.add(new Point(point.getX(), point.getY() + 1));
        return list;
    }

    protected List<Point> findBestAttackPoint()
    {
        ArrayList<Point> list = new ArrayList<>();
        for (int y = 0; y < height(); y ++)
        {
            for (int x = 0; x < width(); x ++)
            {
                if (board[x][y] == HEADS)
                {
                    list.add(new Point(x, y));
                }
            }
        }
        return list;
    }

    protected List<Point> findBestFood()
    {
        return food;
    }

    private List<Point> findHeads()
    {
        ArrayList<Point> list = new ArrayList<>();
        for (SmartSnake snake: snakes)
        {
            if (!snake.equals(mySnake()))
            {
                list.addAll(findAdjacent(snake.body().get(0)));
                list.add(snake.body().get(0));
            }
        }
        return list;

    }

    private List<Point> findOurClosestTail()
    {
        return new ArrayList<>();
    }

    private List<Point> findOurTail()
    {
        return findAdjacent(mySnake().body().get(mySnake().body().size() - 1));
    }

    protected Move findPath(List<Point> destinations, Point currentPoint)
    {
        LinkedList<MovePoint> points = new LinkedList<>();
        ArrayList<MovePoint> list = new ArrayList<>();

        for (int i = 0; i < destinations.size(); i ++)
        {
            if (destinations.get(i).equals(currentPoint))
            {
                destinations.remove(i);
                i --;
            }
        }

        MovePoint loopPoint = new MovePoint(null, currentPoint, null);
        points.add(loopPoint);
        list.add(loopPoint);
        while (!points.isEmpty())
        {
            loopPoint = points.pollFirst();
            for (Point destination: destinations)
            {
                if (loopPoint.point().equals(destination))
                {
                    return loopPoint.initialMove();
                }
            }
            List<MovePoint> moves = getPossibleMoves(loopPoint);
            for (MovePoint move: moves)
            {
                if (list.contains(move)) continue;
                points.add(move);
                list.add(move);
            }
        }

        return null;
    }

    protected List<Point> findSafestPoint()
    {
        ArrayList<Point> list = new ArrayList<>();
        for (SmartSnake snake: snakes)
        {
            list.add(snake.body().get(snake.body().size()));
        }
        return list;
    }

    private List<MovePoint> getPossibleMoves(MovePoint point)
    {
        ArrayList<MovePoint> list = new ArrayList<>();
        Point up = Move.up.translate(point.point());
        Point down = Move.down.translate(point.point());
        Point left = Move.left.translate(point.point());
        Point right = Move.right.translate(point.point());
        Move initial = point.initialMove();

        if (up != null && !isFilled(up))
        {
            if (initial == null)
            {
                list.add(new MovePoint(Move.up, up, Move.up));
            }
            else
            {
                list.add(new MovePoint(Move.up, up, initial));
            }
        }
        if (down != null && !isFilled(down))
        {
            if (initial == null)
            {
                list.add(new MovePoint(Move.down, down, Move.down));
            }
            else
            {
                list.add(new MovePoint(Move.down, down, initial));
            }
        }
        if (left != null && !isFilled(left))
        {
            if (initial == null)
            {
                list.add(new MovePoint(Move.left, left, Move.left));
            }
            else
            {
                list.add(new MovePoint(Move.left, left, initial));
            }
        }
        if (right != null && !isFilled(right))
        {
            if (initial == null)
            {
                list.add(new MovePoint(Move.right, right, Move.right));
            }
            else
            {
                list.add(new MovePoint(Move.right, right, initial));
            }
        }
        return list;
    }

    protected Move goToAttack(Point currentPoint)
    {
        return findPath(findHeads(), currentPoint);
    }

    protected Move goToFood(Point currentPoint)
    {
        return findPath(findBestFood(), currentPoint);
    }

    protected Move goToTail(Point currentPoint)
    {
        Move move = null;
        for (int i = mySnake().body().size() - 1; i > 0; i --)
        {
            move = findPath(
                    findAdjacent(mySnake().body().get(i)), currentPoint);
            if (move != null) return move;
        }
        return null;
    }

    public int height()
    {
        return height;
    }

    public boolean isFilled(Point point)
    {
        return isFilled(point, board);
    }

    private boolean isFilled(Point point, int[][] board)
    {
        if (!exists(point)) return true;
        return board[point.getX()][point.getY()] != EMPTY
                &&
                board[point.getX()][point.getY()] != FOOD;
    }

    public boolean isRegionFilled(Point point)
    {
        return isFilled(point, region);
    }

    protected int longestSnakeLength()
    {
        int max = Integer.MIN_VALUE;
        for (SmartSnake snake: snakes)
        {
            if (!snake.equals(mySnake()) && snake.length() > max)
            {
                max = snake.length();
            }
        }
        return max;
    }

    public SmartSnake mySnake()
    {
        return you;
    }

    public void print()
    {
        System.out.println(toString());
    }

    private Move randomMove(List<MovePoint> moves)
    {
        return moves.get(ThreadLocalRandom.current().nextInt(moves.size()))
                .initialMove();
    }

    private void removeDead()
    {
        for (int i = 0; i < snakes.size(); i ++)
        {
            if (snakes.get(i).isDead())
            {
                snakes.remove(i);
                i --;
            }
        }
    }

    private void toGrid()
    {
        this.board = new int[width()][height()];

        for (Point snack: food)
        {
            board[snack.getX()][snack.getY()] = FOOD;
        }

        for (SmartSnake snake: snakes)
        {
            List<Point> body = snake.body();
            Point head = body.get(0);
            for (Point bodyPart: body)
            {
                board[bodyPart.getX()][bodyPart.getY()] = WALL;
            }

            if (snake.equals(mySnake()))
            {
                board[head.getX()][head.getY()] = ME;
            }
            else
            {
                board[head.getX()][head.getY()] = HEADS;

                if (!mySnake().longerThan(snake))
                {
                    List<Point> around = findAdjacent(head);
                    for (Point point: around)
                    {
                        if (exists(point))
                            board[point.getX()][point.getY()] = WALL;
                    }
                }
            }
        }
    }

    public String toRegionString()
    {
        return toString(region);
    }

    @Override
    public String toString()
    {
        return toString(board);
    }

    public String toString(int[][] board)
    {
        String value = "";
        for (int y = 0; y < height(); y ++)
        {
            for (int x = 0; x < width(); x ++)
            {
                value += board[x][y];
                value += " ";
            }
            value += "\n";
        }
        return value;
    }

    public int width()
    {
        return width;
    }
}
