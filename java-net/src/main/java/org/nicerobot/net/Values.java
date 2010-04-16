package org.nicerobot.net;

import java.util.ArrayList;

@SuppressWarnings ("serial")
public class Values extends ArrayList<Value<?>> implements Encodable {

	private boolean encode = false;

	public Values () {
		super();
	}

	public Values (final int i) {
		super(i);
	}

	/**
	 * @param index
	 * @param element
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	public void add (final int index, final String element) {
		add(index, element, this.encode);
	}

	/**
	 * @param index
	 * @param element
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	public void add (final int index, final String element, final boolean encode) {
		final Value<String> v = new Value<String>(element);
		v.setEncode(encode);
		add(index, v);
	}

	/**
	 * @param element
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add (final String element) {
		return add(element, this.encode);
	}

	/**
	 * @param element
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add (final String element, final boolean encode) {
		final Value<String> v = new Value<String>(element);
		v.setEncode(encode);
		return add(v);
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
		if (!(obj instanceof Values)) {
			return false;
		}
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode () {
		return 31 * super.hashCode();
	}

	/**
	 * @return the encode
	 */
	public boolean isEncoding () {
		return this.encode;
	}

	/**
	 * @return Elements joined with a comma
	 */
	public String join () {
		return join(",");
	}

	/**
	 * @param delimiter
	 * @return
	 */
	public String join (final String delimiter) {
		final StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (final Value<?> value : this) {
			if (!first) {
				builder.append(delimiter);
			} else {
				first = false;
			}
			builder.append(value.toString());
		}
		return builder.toString();
	}

	/**
	 * @param encode_
	 *          the encode to set
	 */
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
		return join();
	}

}
