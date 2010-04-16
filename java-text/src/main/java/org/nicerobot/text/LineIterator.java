package org.nicerobot.text;

import java.util.Iterator;

public abstract class LineIterator {

	protected final Iterator<?> i;

	public LineIterator (final Iterator<?> i) {
		this.i = i;
	}

	public boolean hasNext () {
		return null != this.i && this.i.hasNext();
	}

	public abstract String next (int indent, int pad);

}
