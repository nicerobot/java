package org.nicerobot.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Value<T> implements Encodable {
	private final T value;

	private boolean encode = false;

	public static final Value<String> NULL = new Value<String>();

	public Value () {
		this.value = null;
	}

	public Value (final T value) {
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
		if (!(obj instanceof Value<?>)) {
			return false;
		}
		final Value<?> other = (Value<?>) obj;
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

	public boolean isDefined () {
		return this.value != null;
	}

	@Override
	public void setEncode (final boolean encode_) {
		this.encode = encode_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		if (!this.isDefined()) {
			return "";
		}
		String r = String.valueOf(this.value);
		if (this.encode) {
			try {
				r = URLEncoder.encode(r, "UTF-8");
			} catch (final UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

}
