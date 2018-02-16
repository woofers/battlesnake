package ca.casualt.snakes.basicbattlesnake.utilities;

import java.util.function.Predicate;

import ca.casualt.snakes.basicbattlesnake.types.Move;
import ca.casualt.snakes.basicbattlesnake.types.Point;

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

}
