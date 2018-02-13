package ca.casualt.snakes.basicbattlesnake.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Predicate;

import ca.casualt.snakes.basicbattlesnake.types.Move;
import ca.casualt.snakes.basicbattlesnake.types.MoveRequest;
import ca.casualt.snakes.basicbattlesnake.types.Point;
import ca.casualt.snakes.basicbattlesnake.utilities.movers.RandomMover;

/**
 * For deriving directional and path related intelligence.
 *
 * @author Tony
 *
 */
public final class PathingDerivations {

	/**
	 * Utility Class.
	 */
	private PathingDerivations() {
	}

	/**
	 *
	 * @param reference
	 * @return true, if point is adjacent.
	 */
	public static Predicate<? super Point> isAdjacent(final Point reference) {
		return point -> {
			final int deltaX = reference.getX() - point.getX();
			final int deltaY = reference.getY() - point.getY();
			if ((Math.abs(deltaX) + Math.abs(deltaY)) <= 1) {
				return true;
			} else {
				return false;
			}
		};
	}

	/**
	 * Diagnols are not supported. Mostly meant for adjacent spaces. But it will
	 * always give you a direction unless you are right on top.
	 *
	 * @param reference
	 * @param point
	 * @return
	 */
	public static Move directionTo(final Point reference, final Point point) {
		final int deltaX = reference.getX() - point.getX();
		final int deltaY = reference.getY() - point.getY();
		if (deltaX > 0) {
			return Move.left;
		} else if (deltaX < 0) {
			return Move.right;
		} else {
			if (deltaY > 0) {
				return Move.up;
			} else if (deltaY < 0) {
				return Move.down;
			} else {
				return null;
			}
		}
	}

	// TODO: make one that supports diagnols

	/**
	 * The distance is the total number of points needed to traverse in a
	 * "direct line" to the other point.
	 *
	 * @param reference
	 *            start location.
	 * @param point
	 *            destination location,.
	 * @return the distance.
	 */
	public static int distanceTo(final Point reference, final Point point) {
		final int xDistance = Math.abs(reference.getX() - point.getX());
		final int yDistance = Math.abs(reference.getY() - point.getY());
		return xDistance + yDistance;
	}

	// TODO: make one that actually breadth-first-searches an open path
	// TODO: make max-length version with multiple options/best current
	// option/path.

	/**
	 * Based on current board state traces out shortest path, and returns first
	 * move along it.
	 *
	 * @param initialState
	 * @param end
	 * @return the path of points to the end point. If no path available,
	 *         returns empty list.
	 */
	public static List<Point> moveToViaShortestOpenPath(final MoveRequest initialState, final Point end) {
		final int[][] boardDistances = infiniteDistanceBoard(initialState);
		final Point myHead = initialState.getYou().getHead();
		boardDistances[myHead.getX()][myHead.getY()] = 0;
		Queue<Point> pointsToEvaluate = new LinkedList<>();
		pointsToEvaluate.add(myHead);
		// Loop until we've filled the board that we can reach.
		while (!pointsToEvaluate.isEmpty()) {
			final Point currentPoint = pointsToEvaluate.remove();
			final int currentScore = getScore(boardDistances, currentPoint);
			final List<Move> possibleMoves = RandomMover.getPossibleMoves(initialState, currentPoint);
			final int potentialScore = currentScore + 1;
			for (Move m : possibleMoves) {
				final Point newPoint = pointIfMoveApplied(currentPoint, m);
				final int newPointCurrentScore = getScore(boardDistances, newPoint);
				if (potentialScore < newPointCurrentScore) {
					boardDistances[newPoint.getX()][newPoint.getY()] = currentScore + 1;
					pointsToEvaluate.add(newPoint);
				}
			}

		}
		// Now analyze numbered board for best move to destination.
		// Work way backwards to 0
		List<Point> pathHome = new ArrayList<>();
		final int endScore = getScore(boardDistances, end);
		// System.out.println("Distance to end point is: " + endScore);
		// If we can't reach the end point then its score will be Integer.Max
		// still
		if (endScore != Integer.MAX_VALUE) {
			pointsToEvaluate.add(end);
			pathHome.add(end);
			while (!pointsToEvaluate.isEmpty()) {
				final Point currentPoint = pointsToEvaluate.remove();
				final Point wayHome = findLowestNeighbour(boardDistances, currentPoint);
				if (getScore(boardDistances, wayHome) != 0) {
					pointsToEvaluate.add(wayHome);
					pathHome.add(wayHome);
				}
			}
			Collections.reverse(pathHome);
		}
		// System.out.println("My head = " + myHead);
		// System.out.println("Path to point = " + pathHome);
		return pathHome;
	}

	/**
	 * Try all 4 neighbouring points, return the one with the lowest score.<br>
	 * (things like snakes and other unreachable points should be
	 *
	 * @param boardDistances
	 * @param currentPoint
	 * @return
	 */
	private static Point findLowestNeighbour(final int[][] boardDistances, final Point currentPoint) {
		Point best = null;
		int score = Integer.MAX_VALUE;
		for (Move m : Move.values()) {
			Point newPoint = pointIfMoveApplied(currentPoint, m);
			int newScore = getScore(boardDistances, newPoint);
			if (newScore < score) {
				best = newPoint;
				score = newScore;
			}
		}
		return best;
	}

	/**
	 * Read the score from the board. If out-of-bounds, returns max integer
	 * value.
	 *
	 * @param boardDistances
	 * @param currentPoint
	 * @return
	 */
	private static int getScore(final int[][] boardDistances, final Point currentPoint) {
		if (currentPoint.getX() < 0 || currentPoint.getX() >= boardDistances.length || currentPoint.getY() < 0
				|| currentPoint.getY() >= boardDistances[0].length) {
			return Integer.MAX_VALUE;
		}
		return boardDistances[currentPoint.getX()][currentPoint.getY()];
	}

	/**
	 * Setup a board with max/infinite distances filled in.
	 *
	 * @param initialState
	 * @return
	 */
	private static int[][] infiniteDistanceBoard(final MoveRequest initialState) {
		final int[][] toReturn = new int[initialState.getWidth()][initialState.getHeight()];
		for (int x = 0; x < initialState.getWidth(); x++) {
			for (int y = 0; y < initialState.getHeight(); y++) {
				toReturn[x][y] = Integer.MAX_VALUE;
			}
		}
		return toReturn;
	}

	/**
	 * Makes no assumptions about other snakes moving. Finds shortest currently
	 * open path to destination. <br>
	 * // FIXME: too slow/doesn't avoid loops during search.
	 *
	 * @param currentState
	 * @param end
	 * @param maxLength
	 * @return
	 */
	public static List<Point> moveToViaOpenPath(final MoveRequest initialState, final Point end) {
		Queue<MoveRequestWithPathHistory> toEvaluate = new LinkedList<>();
		// Seed initial list.
		toEvaluate.add(new MoveRequestWithPathHistory(initialState));

		// execute next move-option in queue
		while (!toEvaluate.isEmpty()) {
			final MoveRequestWithPathHistory currentState = toEvaluate.remove();
			// Get all possible moves
			List<Move> possibleMoves = RandomMover.getPossibleMoves(currentState.getMoveRequest(),
					currentState.getMoveRequest().getYou().getHead());
			// For-each move execute on new copy of board, and queue new
			// board-state for evaluation.
			for (Move move : possibleMoves) {
				final MoveRequest newBoard = deriveNewBoardState(currentState.getMoveRequest(), move);
				final Point latestNewPoint = newBoard.getYou().getHead();
				final List<Point> newList = new ArrayList<>(currentState.getMoveSequence());
				newList.add(latestNewPoint);
				if (latestNewPoint.equals(end)) {
					// In this case, return current path.
					return newList;
				} else {
					MoveRequestWithPathHistory nextToEvaluate = new MoveRequestWithPathHistory(newBoard, newList);
					toEvaluate.add(nextToEvaluate);
				}
			}
		}
		return new ArrayList<>(); // No available moves found.
	}

	public static class MoveRequestWithPathHistory {
		private final MoveRequest moveRequest;
		private final List<Point> moveSequence;

		public MoveRequestWithPathHistory(final MoveRequest moveRequestIn, final List<Point> moveSequenceIn) {
			this.moveRequest = moveRequestIn;
			this.moveSequence = moveSequenceIn;
		}

		public MoveRequestWithPathHistory(final MoveRequest moveRequestIn) {
			this.moveRequest = moveRequestIn;
			this.moveSequence = new ArrayList<Point>();
		}

		public MoveRequest getMoveRequest() {
			return moveRequest;
		}

		public List<Point> getMoveSequence() {
			return moveSequence;
		}
	}

	/**
	 * Follows a copy-then-modify pattern.
	 *
	 * @param initialState
	 * @param myMove
	 * @return
	 */
	private static MoveRequest deriveNewBoardState(final MoveRequest initialState, final Move myMove) {
		MoveRequest toReturn = new MoveRequest(initialState);

		// coords returned by reference, any modifications will be reflected in
		// board state.
		List<Point> coordsToMove = toReturn.getYou().getBody();
		Point newHeadPoint = pointIfMoveApplied(toReturn.getYou().getHead(), myMove);
		coordsToMove.add(0, newHeadPoint);
		coordsToMove.remove(coordsToMove.size() - 1);

		return toReturn;
	}

	/**
	 * Blindly applies move.
	 *
	 * @param start
	 * @param move
	 * @return the resulting point.
	 */
	private static Point pointIfMoveApplied(final Point start, final Move move) {
		switch (move) {
		case down:
			return new Point(start.getX(), start.getY() + 1);
		case left:
			return new Point(start.getX() - 1, start.getY());
		case right:
			return new Point(start.getX() + 1, start.getY());
		case up:
			return new Point(start.getX(), start.getY() - 1);
		}
		throw new RuntimeException("Invalid move passed in? " + move);
	}

}
