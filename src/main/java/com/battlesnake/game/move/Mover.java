package com.battlesnake.game.move;

import com.battlesnake.game.data.Move;
import com.battlesnake.http.request.MoveRequest;

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
