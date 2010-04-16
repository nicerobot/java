package org.nicerobot.text;

/**
 * @author nicerobot
 * 
 */
public interface Indentable extends LineIterable {

	/**
	 * @return
	 */
	public String toFormattedString ();

	/**
	 * @param indent
	 * @param pad
	 * @param padWith
	 * @return
	 */
	public String toString (int indent, int pad);

}
