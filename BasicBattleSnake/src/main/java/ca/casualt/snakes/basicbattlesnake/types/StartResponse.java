package ca.casualt.snakes.basicbattlesnake.types;

import com.google.gson.annotations.SerializedName;

/**
 * A standard response to a start request.
 *
 * @author Tony
 *
 */
public class StartResponse {

	private String color;
	private String name;
	@SerializedName("head_url")
	private String headUrl;
	private String taunt;

	@SerializedName("tail_type")
	private TailType tailType;
	@SerializedName("head_type")
	private HeadType headType;
	@SerializedName("secondary_color")
	private String secondaryColor;

	/**
	 * Default constructor.
	 */
	public StartResponse() {
	}

	/**
	 * @return the color
	 */
	public final String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public final void setColor(final String color) {
		this.color = color;
	}

	/**
	 * @return the headUrl
	 */
	public final String getHeadUrl() {
		return headUrl;
	}

	/**
	 * @param headUrl
	 *            the headUrl to set
	 */
	public final void setHeadUrl(final String headUrl) {
		this.headUrl = headUrl;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(final String name) {
		this.name = name;
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

	/**
	 * @return the tailType
	 */
	public final TailType getTailType() {
		return tailType;
	}

	/**
	 * @param tailType
	 *            the tailType to set
	 */
	public final void setTailType(final TailType tailType) {
		this.tailType = tailType;
	}

	/**
	 * @return the headType
	 */
	public final HeadType getHeadType() {
		return headType;
	}

	/**
	 * @param headType
	 *            the headType to set
	 */
	public final void setHeadType(final HeadType headType) {
		this.headType = headType;
	}

	/**
	 * @return the secondaryColor
	 */
	public final String getSecondaryColor() {
		return secondaryColor;
	}

	/**
	 * @param secondaryColor
	 *            the secondaryColor to set
	 */
	public final void setSecondaryColor(final String secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StartResponse [color=" + color + ", name=" + name + ", headUrl=" + headUrl + ", taunt=" + taunt
				+ ", tailType=" + tailType + ", headType=" + headType + ", secondaryColor=" + secondaryColor + "]";
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
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((headType == null) ? 0 : headType.hashCode());
		result = prime * result + ((headUrl == null) ? 0 : headUrl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((secondaryColor == null) ? 0 : secondaryColor.hashCode());
		result = prime * result + ((tailType == null) ? 0 : tailType.hashCode());
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
		StartResponse other = (StartResponse) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (headType != other.headType)
			return false;
		if (headUrl == null) {
			if (other.headUrl != null)
				return false;
		} else if (!headUrl.equals(other.headUrl))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (secondaryColor == null) {
			if (other.secondaryColor != null)
				return false;
		} else if (!secondaryColor.equals(other.secondaryColor))
			return false;
		if (tailType != other.tailType)
			return false;
		if (taunt == null) {
			if (other.taunt != null)
				return false;
		} else if (!taunt.equals(other.taunt))
			return false;
		return true;
	}

}
