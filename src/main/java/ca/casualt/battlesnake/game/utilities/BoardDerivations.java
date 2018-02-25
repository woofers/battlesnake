package ca.casualt.battlesnake.game.utilities;

import java.util.ArrayList;
import java.util.List;

import ca.casualt.battlesnake.game.data.BoardSpec;
import ca.casualt.battlesnake.game.math.Point;

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

}
