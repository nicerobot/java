package org.nicerobot.text;

public abstract class Indenter implements Indentable {
	/*
	 * (non-Javadoc)
	 * 
	 * @see javascript.util.Formattable#toFormattedString()
	 */
	@Override
	public String toFormattedString () {
		return toString(2);
	}

	/**
	 * @param indent
	 * @return
	 */
	public String toString (final int indent) {
		return toString(indent, 0);
	}

	/**
	 * @param indent
	 * @return
	 */
	public abstract String toString (final int indent, final int pad);

}
