package org.nicerobot.net;

import java.net.URL;

import org.nicerobot.util.MultiMap;

public class Query extends MultiMap<String, Value<?>> implements Encodable {

	private boolean encode = false;

	public Query () {
		super();
	}

	public Query (String parameters) {
		if (null == parameters) {
			return;
		}
		final int q = parameters.indexOf('?');
		if (-1 != q) {
			parameters = parameters.substring(q + 1);
		}
		for (final String p : parameters.split("&")) {
			final String[] kv = p.split("=");
			if (1 == kv.length) {
				put(kv[0], Value.NULL);
			} else {
				put(kv[0], kv[1]);
			}
		}
	}

	/**
	 * @param url
	 */
	public Query (final URL url) {
		this(url.getQuery());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.MultiMap#get(java.lang.Object)
	 */
	@Override
	public Values get (final Object key_) {
		return (Values) super.get(key_);
	}

	/**
	 * @param key
	 * @param value
	 */
	public void put (final String key, final String value) {
		final Value<String> v = new Value<String>(value);
		v.setEncode(this.encode);
		put(key, v);
	}

	/**
	 * @param encode_
	 *          the default for URL encode.
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
		final StringBuilder builder = new StringBuilder();
		StringBuilder pkey = null;
		for (final String key : keySet()) {
			if (null != pkey) {
				builder.append("&");
			}
			final Values values = get(key);
			final int vl = values.size();
			if (0 == vl) {
				continue;
			}
			pkey = new StringBuilder(key);
			if (1 < vl) {
				pkey.append("[]");
			}
			pkey.append("=");
			// This builds a list of values in the form:
			// - key1=value&key2=value&key3[]=value1&key3[]=value2&...
			// by using the Values.join with a delimiter of ",key[]"
			builder.append(pkey.toString()).append(values.join(pkey.insert(0, "&").toString()));
		}
		return builder.toString();
	}

	/**
	 * @return
	 */
	@Override
	protected Values createList () {
		final Values v = new Values();
		v.setEncode(this.encode);
		return v;
	}

}
