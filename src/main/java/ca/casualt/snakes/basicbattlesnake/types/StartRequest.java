package ca.casualt.snakes.basicbattlesnake.types;

import com.google.gson.annotations.SerializedName;

/**
 * A start request.
 *
 * @author Tony
 *
 */
public final class StartRequest {

	@SerializedName("game_id")
	private String gameId;

	/**
	 * Default constructor.
	 */
	public StartRequest() {
	}

	/**
	 * @return the gameId
	 */
	public final String getId() {
		return gameId;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public final void setGameId(final String gameId) {
		this.gameId = gameId;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StartRequest [gameId=" + gameId + "]";
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
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
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
		StartRequest other = (StartRequest) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		return true;
	}

}
