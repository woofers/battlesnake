package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.http.request.MoveRequest;
import ca.casualt.battlesnake.game.data.Move;
import ca.casualt.battlesnake.game.math.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.PriorityQueue;

/**
 * @author Jaxson Van Doorn
 * @author Ben Austin
 */
public class Board
{
    private String id;
    private int turn;

    private SmartSnake you;

    private List<SmartSnake> snakes;

    private int width;
    private int height;

    private List<Point> food;

    private int[][] board;

    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int ME = 2;
    private static final int HEADS = 3;
    private static final int FOOD = 4;

    private static class MovePoint
    {
        private Point point;
        private Move move;

        public MovePoint(Move move, Point point)
        {
            this.move = move;
            this.point = point;
        }

        public Point point()
        {
            return point;
        }

        public Move move()
        {
            return move;
        }

        public boolean equals(Object other)
        {
            if (other instanceof MovePoint) return equals((MovePoint)other);
            return false;
        }

        public boolean equals(MovePoint other)
        {
            return point().equals(other);
        }
    }

    public Board(MoveRequest request)
    {
        this.id = request.getId();
        this.turn = request.getTurn();
        this.you = new SmartSnake(request.getYou());
        this.snakes = new ArrayList<SmartSnake>();
        List<Snake> oldSnakes = request.getSnakes();
        for (Snake snake: oldSnakes)
        {
            this.snakes.add(new SmartSnake(snake));
        }
        this.width = request.getWidth();
        this.height = request.getHeight();
        this.food = request.getFood();

        toGrid();
        print();
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

            if (snake.isFriendly())
            {
                board[head.getX()][head.getY()] = ME;
            }
            else
            {
                board[head.getX()][head.getY()] = HEADS;
            }
        }
    }

    // 1, 1
    // 1, 1
    protected Move findPath(Point point, Point currentPoint)
    {
        PriorityQueue<MovePoint> points = new PriorityQueue<MovePoint>();

        Point loopPoint = currentPoint;
        points.add(new MovePoint(null, loopPoint));
        while (!points.isEmpty())
        {
            if (point.equals(currentPoint))
            {
                return null;
            }
            List<MovePoint> moves = getPossibleMoves(loopPoint);
            for (MovePoint move: moves)
            {
                if (points.contains(move)) continue;
                points.add(move);
            }
            loopPoint = points.poll().point();
        }

        // BFS here
        return Move.down;
    }

    private List<MovePoint> getPossibleMoves(Point point)
    {
        ArrayList<MovePoint> list = new ArrayList<MovePoint>();
        Point up = Move.up.translate(point);
        Point down = Move.down.translate(point);
        Point left = Move.left.translate(point);
        Point right = Move.right.translate(point);
        if (up != null && !isFilled(up))
        {
            list.add(new MovePoint(Move.up, up));
        }
        if (down != null && !isFilled(down))
        {
            list.add(new MovePoint(Move.down, down));
        }
        if (left != null && !isFilled(left))
        {
            list.add(new MovePoint(Move.left, left));
        }
        if (right != null && !isFilled(right))
        {
            list.add(new MovePoint(Move.right, right));
        }
        return list;
    }

    public boolean isFilled(Point point)
    {
        return board[point.getX()][point.getY()] == EMPTY;
    }

    protected Point findSafestPoint()
    {
        return new Point(1, 1);
    }

    protected Point findBestAttackPoint()
    {
        return new Point(1, 1);
    }

    protected Point findBestFood()
    {
        return new Point(1, 1);
    }

    protected int longestSnakeLength()
    {
        int max = Integer.MIN_VALUE;
        for (SmartSnake snake: snakes)
        {
            if (!snake.isFriendly() && snake.length() > max)
            {
                max = snake.length();
            }
        }
        return max;
    }

    public int width()
    {
        return width;
    }

    public int height()
    {
        return height;
    }

    public SmartSnake mySnake()
    {
        return you;
    }

    public void print()
    {
        System.out.println(toString());
    }

    public String toString()
    {
        String value = "";
        for (int y = 0; y < height; y ++)
        {
            for (int x = 0; x < width; x ++)
            {
                value += board[x][y];
                value += " ";
            }
            value += "\n";
        }
        return value;
    }
}
