package org.nicerobot.javascript;

public class JSAnonymous extends JSFunction {

	/**
	 * Construct a JSFunction.
	 */
	public JSAnonymous () {
		this(null);
	}

	/**
	 * Construct a JSFunction.
	 */
	public JSAnonymous (final JSArray<String> parameterNames, final String body) {
		super(null, parameterNames, body);
	}

	/**
	 * Construct a JSFunction.
	 */
	public JSAnonymous (final String body) {
		super(null, body);
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName () {
		final StringBuilder sb = new StringBuilder();
		sb.append("(function(){").append(getValue()).append("})");
		return sb.toString();
	}

}