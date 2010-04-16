/**
 * 
 */
package org.nicerobot.text;

/**
 * @author nicerobot
 * 
 */
public class Indent {

	/**
	 * @param indent
	 * @param pad
	 * @param padWith
	 * @return
	 */
	public static final String padding (final int pad) {
		if (0 >= pad) {
			return "";
		}
		final String padFormat = new StringBuffer("%1$-").append(pad).append("s").toString();
		return String.format(padFormat, " ");
	}

	/**
	 * 
	 */
	private LineIterator iterator = null;

	/**
	 * 
	 */
	private String startString = "";

	/**
	 * 
	 */
	private String endString = "";

	/**
	 * 
	 */
	private String delimiter = ",";

	/**
	 * 
	 */
	private final boolean finalDelimiter;

	/**
	 * 
	 */
	private final String numberFormat = "%04d ";

	/**
	 * 
	 */
	private final boolean lineNumbering = true;

	/**
	 * @param object
	 */
	public Indent (final Indentable toformat) {
		this(toformat, ",");
	}

	/**
	 * @param object
	 */
	public Indent (final Indentable toformat, final boolean finalDelimiter) {
		this(toformat, finalDelimiter, ",");
	}

	/**
	 * @param object
	 */
	public Indent (final LineIterable toformat, final boolean finalDelimiter, final String... strings) {
		this.iterator = toformat.lineIterator();
		this.finalDelimiter = finalDelimiter;

		switch (strings.length) {
			default:
			case 3:
				this.endString = strings[2];
			case 2:
				this.startString = strings[1];
			case 1:
				this.delimiter = strings[0];
			case 0:
				break;
		}
		if (2 == strings.length) {
			this.endString = this.startString;
		}
	}

	/**
	 * @param object
	 */
	public Indent (final LineIterable toformat, final String... strings) {
		this(toformat, false, strings);
	}

	/**
	 * @return the delimiter
	 */
	public String getDelimiter () {
		return this.delimiter;
	}

	/**
	 * @return the endString
	 */
	public String getEndString () {
		return this.endString;
	}

	/**
	 * @return the numberFormat
	 */
	public String getNumberFormat () {
		return this.numberFormat;
	}

	/**
	 * @return the startString
	 */
	public String getStartString () {
		return this.startString;
	}

	/**
	 * @return the lineNumbering
	 */
	public boolean isLineNumbering () {
		return this.lineNumbering;
	}

	/**
	 * @param delimiter
	 *          the delimiter to set
	 */
	public void setDelimiter (final String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * @param endString
	 *          the endString to set
	 */
	public void setEndString (final String endString) {
		this.endString = endString;
	}

	/**
	 * @param startString
	 *          the startString to set
	 */
	public void setStartString (final String startString) {
		this.startString = startString;
	}

	/**
	 * @param indent
	 * @param pad
	 * @param padWith
	 * @param lineno
	 * @return
	 */
	public String toString (final int indent, final int pad) {
		final String padding = Indent.padding(pad);

		final StringBuffer sb = new StringBuffer();

		int c = 0;
		while (this.iterator.hasNext()) {
			++c;

			if (1 < c) {
				sb.append(this.delimiter).append("\n").append(padding);
			}
			if (1 == c) {
				sb.append(padding).append(this.startString).append(" ");
			}
			sb.append(this.iterator.next(indent, pad + this.startString.length() + 1));
		}
		if (this.finalDelimiter) {
			sb.append(this.delimiter);
		}
		return sb.append(padding).append(this.endString).toString();
	}

}
