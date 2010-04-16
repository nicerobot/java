package org.nicerobot.javascript;

public class JSAssignment {

	/**
	 * 
	 */
	private final boolean declare = false;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private JSValue<?> value;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof JSAssignment)) {
			return false;
		}
		final JSAssignment other = (JSAssignment) obj;
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!this.value.equals(other.value)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the name
	 */
	public String getName () {
		return this.name;
	}

	/**
	 * @return the value
	 */
	public JSValue<?> getValue () {
		return this.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.name == null ? 0 : this.name.hashCode());
		result = prime * result + (this.value == null ? 0 : this.value.hashCode());
		return result;
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public JSAssignment setName (final String name) {
		this.name = name;
		return this;
	}

	/**
	 * @param value
	 *          the value to set
	 */
	public void setValue (final JSValue<?> value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		final StringBuilder builder = new StringBuilder();
		if (this.declare) {
			builder.append("var ");
		}
		if (this.name != null) {
			builder.append(this.name);
		}
		if (this.name != null && this.value != null) {
			builder.append("=");
		}
		if (this.value != null) {
			builder.append(this.value);
		}
		return builder.toString();
	}
}
