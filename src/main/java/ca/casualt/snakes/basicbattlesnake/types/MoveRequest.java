package ca.casualt.snakes.basicbattlesnake.types;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A move request.
 *
 * @author Tony
 *
 */
public final class MoveRequest implements BoardSpec {

	private String id;
	private int turn;

	private Snake you;

	private List<Snake> snakes;

	private int width;
	private int height;

	private List<Point> food;

	/**
	 * Default constructor.
	 */
	public MoveRequest() {
	}

	/**
	 * Copy constructor. <br>
	 * (deep copy).
	 *
	 * @param toCopy
	 */
	public MoveRequest(final MoveRequest toCopy) {
		this.id = toCopy.id;
		this.turn = toCopy.turn;
		this.you = new Snake(toCopy.you);

		this.width = toCopy.width;
		this.height = toCopy.height;

		this.snakes = toCopy.snakes.stream().map(snake -> {
			return new Snake(snake);
		}).collect(Collectors.toList());
		this.food = toCopy.food.stream().map(aFood -> {
			return new Point(aFood);
		}).collect(Collectors.toList());
	}

	/**
	 * @return the gameId
	 */
	@Override
	public final String getId() {
		return id;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public final void setId(final String gameId) {
		this.id = gameId;
	}

	/**
	 * @return the width
	 */
	@Override
	public final int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public final void setWidth(final int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	@Override
	public final int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public final void setHeight(final int height) {
		this.height = height;
	}

	/**
	 * @return your snake.
	 */
	public final Snake getYou() {
		return you;
	}

	/**
	 * @param you
	 *            the you to set
	 */
	public final void setYou(final Snake you) {
		this.you = you;
	}

	/**
	 * @return the turn
	 */
	public final int getTurn() {
		return turn;
	}

	/**
	 * @param turn
	 *            the turn to set
	 */
	public final void setTurn(final int turn) {
		this.turn = turn;
	}

	/**
	 * @return the snakes
	 */
	public final List<Snake> getSnakes() {
		return snakes;
	}

	/**
	 * @param snakes
	 *            the snakes to set
	 */
	public final void setSnakes(final List<Snake> snakes) {
		this.snakes = snakes;
	}

	/**
	 * @return the food
	 */
	public final List<Point> getFood() {
		return food;
	}

	/**
	 * @param food
	 *            the food to set
	 */
	public final void setFood(final List<Point> food) {
		this.food = food;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MoveRequest [gameId=" + id + ", width=" + width + ", height=" + height + ", you=" + you + ", turn="
				+ turn + ", snakes=" + snakes + ", food=" + food + "]";
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
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + height;
		result = prime * result + ((snakes == null) ? 0 : snakes.hashCode());
		result = prime * result + turn;
		result = prime * result + width;
		result = prime * result + ((you == null) ? 0 : you.hashCode());
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
		MoveRequest other = (MoveRequest) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (height != other.height)
			return false;
		if (snakes == null) {
			if (other.snakes != null)
				return false;
		} else if (!snakes.equals(other.snakes))
			return false;
		if (turn != other.turn)
			return false;
		if (width != other.width)
			return false;
		if (you == null) {
			if (other.you != null)
				return false;
		} else if (!you.equals(other.you))
			return false;
		return true;
	}

}
