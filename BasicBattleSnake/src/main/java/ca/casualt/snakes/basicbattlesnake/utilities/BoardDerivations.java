package ca.casualt.snakes.basicbattlesnake.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ca.casualt.snakes.basicbattlesnake.types.BoardSpec;
import ca.casualt.snakes.basicbattlesnake.types.MoveRequest;
import ca.casualt.snakes.basicbattlesnake.types.Point;
import ca.casualt.snakes.basicbattlesnake.types.Snake;

/**
 * For deriving pieces of handy information about the board from the base data
 * available.
 *
 * @author Tony
 *
 */
public class BoardDerivations {

	/**
	 * Utility class.
	 */
	private BoardDerivations() {
	}

	/**
	 * Generates points for the entire border of the board.<br>
	 * (0,0) is in the top-left.
	 *
	 * @param boardSpec
	 * @return
	 */
	public static List<Point> generateBorderPoints(final BoardSpec boardSpec) {
		List<Point> toReturn = new ArrayList<>();
		int x = -1;
		int y = -1;
		// LHS
		for (; y < boardSpec.getHeight(); y++) {
			toReturn.add(new Point(x, y));
			// System.out.println("(" + x + "," + y + ")");
		}
		// BOTTOM
		for (; x < boardSpec.getWidth(); x++) {
			toReturn.add(new Point(x, y));
			// System.out.println("(" + x + "," + y + ")");
		}
		// RHS
		for (; y > -1; y--) {
			toReturn.add(new Point(x, y));
			// System.out.println("(" + x + "," + y + ")");
		}
		// TOP
		for (; x > -1; x--) {
			toReturn.add(new Point(x, y));
			// System.out.println("(" + x + "," + y + ")");
		}
		return toReturn;
	}

	/**
	 * This will generate the list of points that make up the board.
	 *
	 * @param boardSpec
	 * @return
	 */
	public static List<Point> generateBoardPoints(final BoardSpec boardSpec) {
		List<Point> toReturn = new ArrayList<>();
		for (int x = 0; x < boardSpec.getWidth(); x++) {
			for (int y = 0; y < boardSpec.getHeight(); y++) {
				toReturn.add(new Point(x, y));
			}
		}
		return toReturn;
	}

	/**
	 * This will generate the list of points that are occupied by snakes.
	 *
	 * @param moveRequest
	 * @return
	 */
	public static List<Point> generateOccupiedPoints(final MoveRequest moveRequest) {
		return moveRequest.getSnakes().stream().map(Snake::getBody).flatMap(List::stream).collect(Collectors.toList());
	}

	/**
	 *
	 * @param moveRequest
	 * @return
	 */
	public static List<Point> generateUnoccupiedPoints(final MoveRequest moveRequest) {
		List<Point> totalPoints = generateBoardPoints(moveRequest);
		List<Point> occupiedPoints = generateOccupiedPoints(moveRequest);
		totalPoints.removeAll(occupiedPoints);
		return totalPoints;
	}

	/**
	 * Calculate the average/open-center. Relative to current board state.
	 *
	 * @param moveRequest
	 * @return
	 */
	public static Point calculateOpenCenter(final MoveRequest moveRequest) {
		List<Point> unoccupiedPoints = generateUnoccupiedPoints(moveRequest);
		final double averageX = unoccupiedPoints.stream().map(Point::getX).collect(Collectors.averagingInt(x -> x));
		final double averageY = unoccupiedPoints.stream().map(Point::getY).collect(Collectors.averagingInt(y -> y));
		System.out.println("(" + averageX + "," + averageY + ")");
		return new Point((int) averageX, (int) averageY);
	}

}
