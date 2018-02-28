package ca.casualt.battlesnake.game;

import ca.casualt.battlesnake.http.request.MoveRequest;
import ca.casualt.battlesnake.game.data.Move;
import ca.casualt.battlesnake.game.math.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;

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
        private Move initialMove;

        public MovePoint(Move move, Point point, Move initialMove)
        {
            this.move = move;
            this.point = point;
            this.initialMove = initialMove;
        }

        public Point point()
        {
            return point;
        }

        public Move move()
        {
            return move;
        }

        public Move initialMove()
        {
            return initialMove;
        }

        public boolean equals(Object other)
        {
            if (other instanceof MovePoint) return equals((MovePoint)other);
            return false;
        }

        public boolean equals(MovePoint other)
        {
            return point().equals(other.point());
        }

        public String toString()
        {
            String value = "";
            if (move() != null) value += move().toString() + " ";
            if (point() != null) value += "X: " + point().getX() + " Y: " + point.getY() + " ";
            if (initialMove() != null) value += initialMove().toString() + " ";
            return value;
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

    protected Move findPath(Point point, Point currentPoint)
    {
        LinkedList<MovePoint> points = new LinkedList<MovePoint>();
        ArrayList<MovePoint> list = new ArrayList<MovePoint>();

        MovePoint loopPoint = new MovePoint(null, currentPoint, null);
        points.add(loopPoint);
        list.add(loopPoint);
        while (!points.isEmpty())
        {
            loopPoint = points.pollFirst();
            if (loopPoint.point().equals(point))
            {
                return loopPoint.initialMove();
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

    private List<MovePoint> getPossibleMoves(MovePoint point)
    {
        ArrayList<MovePoint> list = new ArrayList<MovePoint>();
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

    public boolean isFilled(Point point)
    {
        if (point.getX() > width() - 1) return true;
        if (point.getY() > height() - 1) return true;
        return board[point.getX()][point.getY()] != EMPTY &&
               board[point.getX()][point.getY()] != FOOD;
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
        return food.get(0);
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
