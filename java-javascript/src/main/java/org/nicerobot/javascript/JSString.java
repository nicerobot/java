package org.nicerobot.javascript;

public class JSString extends JSValue<String> {

	public JSString (final String value) {
		super(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		if (isUndefined()) {
			return super.toString();
		} else {
			final StringBuilder builder = new StringBuilder();
			builder.append("\"").append(getValue()).append("\"");
			return builder.toString();
		}
	}

}
