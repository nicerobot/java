package org.nicerobot.javascript;

public class JSValue<T> {

	/**
	 * 
	 */
	public static final JSValue<String> undefined = new JSValue<String>("undefined");

	/**
	 * 
	 */
	private T value = null;

	/**
	 * 
	 */
	public JSValue () {}

	/**
	 * 
	 */
	public JSValue (final JSValue<T> toclone) {
		this(toclone.value);
	}

	/**
	 * @param value
	 */
	public JSValue (final T value) {
		this.value = value;
	}

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
		if (!JSValue.isJS(obj)) {
			return false;
		}
		final JSValue<?> other = (JSValue<?>) obj;
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
	 * @return the value
	 */
	public T getValue () {
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
		result = prime * result + (this.value == null ? 0 : this.value.hashCode());
		return result;
	}

	/**
	 * @return
	 */
	public boolean isNull () {
		return null == this.value;
	}

	/**
	 * @return
	 */
	public boolean isUndefined () {
		return JSValue.undefined == this.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		final StringBuilder builder = new StringBuilder();
		if (isUndefined()) {
			builder.append(JSValue.undefined);
		} else {
			builder.append(String.valueOf(this.value));
		}
		return builder.toString();
	}

	/**
	 * @return
	 */
	public static final boolean isJS (final Object obj) {
		return obj instanceof JSValue<?>;
	}

	/**
	 * @return
	 */
	public static final boolean isNull (final Object obj) {
		if (!JSValue.isJS(obj)) {
			return false;
		}
		final JSValue<?> j = (JSValue<?>) obj;
		return null == j.value;
	}

	/**
	 * @return
	 */
	public static final boolean isUndefined (final Object o) {
		if (!JSValue.isJS(o)) {
			return false;
		}
		final JSValue<?> j = (JSValue<?>) o;
		return JSValue.undefined == j.value;
	}

}
