package ca.casualt.snakes.basicbattlesnake.types;

/**
 * A response to a move request.
 *
 * @author Tony
 *
 */
public final class MoveResponse {

	private Move move;
	private String taunt;

	/**
	 * Default constructor.
	 */
	public MoveResponse() {
	}

	/**
	 * @return the move
	 */
	public final Move getMove() {
		return move;
	}

	/**
	 * @param move
	 *            the move to set
	 */
	public final void setMove(final Move move) {
		this.move = move;
	}

	/**
	 * @return the taunt
	 */
	public final String getTaunt() {
		return taunt;
	}

	/**
	 * @param taunt
	 *            the taunt to set
	 */
	public final void setTaunt(final String taunt) {
		this.taunt = taunt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MoveResponse [move=" + move + ", taunt=" + taunt + "]";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((move == null) ? 0 : move.hashCode());
		result = prime * result + ((taunt == null) ? 0 : taunt.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoveResponse other = (MoveResponse) obj;
		if (move == null) {
			if (other.move != null)
				return false;
		} else if (!move.equals(other.move))
			return false;
		if (taunt == null) {
			if (other.taunt != null)
				return false;
		} else if (!taunt.equals(other.taunt))
			return false;
		return true;
	}

}
