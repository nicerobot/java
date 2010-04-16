package org.nicerobot.javascript;

public class JSFunction extends JSValue<String> {

	/**
	 * 
	 */
	private final String name;

	/**
	 * 
	 */
	private JSArray<String> parameterNames;

	/**
	 * Construct an empty JSFunction.
	 */
	public JSFunction (final String name) {
		this.name = name;
	}

	/**
	 * Construct a JSFunction.
	 */
	public JSFunction (final String name, final JSArray<String> parameterNames, final String body) {
		this(name, body);
		this.parameterNames = parameterNames;
	}

	/**
	 * Construct a JSFunction.
	 */
	public JSFunction (final String name, final String body) {
		super(null == body ? "" : body);
		this.name = name;
	}

	/**
	 * @return
	 */
	public String apply (final JSArray<Object> arguments) {
		final StringBuilder sb = new StringBuilder();
		return invoke(sb.append(getName()).append(".apply"), arguments).toString();
	}

	/**
	 * @return
	 */
	public String call (final JSArray<Object> arguments) {
		final StringBuilder sb = new StringBuilder();
		return invoke(sb.append(getName()).append(".call"), arguments).toString();
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof JSFunction)) {
			return false;
		}
		final JSFunction other = (JSFunction) obj;
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.parameterNames == null) {
			if (other.parameterNames != null) {
				return false;
			}
		} else if (!this.parameterNames.equals(other.parameterNames)) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode () {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.name == null ? 0 : this.name.hashCode());
		result = prime * result + (this.parameterNames == null ? 0 : this.parameterNames.hashCode());
		return result;
	}

	/**
	 * @return
	 */
	public String instantiate (final JSArray<Object> arguments) {
		final StringBuilder sb = new StringBuilder();
		return invoke(sb.append("new ").append(getName()), arguments).toString();
	}

	/**
	 * @return
	 */
	private StringBuilder invoke (final StringBuilder sb, final JSArray<Object> arguments) {
		return sb.append("(").append(arguments).append(")");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.json.JSONObject#toString()
	 */
	@Override
	public String toString () {
		final StringBuilder sb = new StringBuilder();
		sb.append("function");
		if (null != this.name) {
			sb.append(" ").append(this.name);
		}
		sb.append("(){").append(getValue()).append("}");
		return sb.toString();
	}
}