package ca.casualt.snakes.basicbattlesnake.utilities.movers;

import ca.casualt.snakes.basicbattlesnake.types.Move;
import ca.casualt.snakes.basicbattlesnake.types.MoveRequest;

/**
 * A thing that'll move a snake. <br>
 * Currently assumed to be stateless.
 *
 * @author Tony
 *
 */
public interface Mover {

	/**
	 * Generate a move from a move request.
	 *
	 * @param moveRequest
	 *            to consider.
	 * @return what was decided.
	 */
	Move getMove(final MoveRequest moveRequest);
}
