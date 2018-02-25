package ca.casualt.battlesnake.game.move;

import ca.casualt.battlesnake.game.data.Move;
import ca.casualt.battlesnake.http.request.MoveRequest;

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
