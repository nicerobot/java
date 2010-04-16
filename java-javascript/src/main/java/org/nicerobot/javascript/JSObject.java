package org.nicerobot.javascript;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JSObject extends JSValue<HashMap<Object, Object>> implements Map<Object, Object> {

	/**
	 * 
	 */
	public JSObject () {
		super(new HashMap<Object, Object>());
	}

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear () {
		getValue().clear();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey (final Object key) {
		return getValue().containsKey(key);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue (final Object value) {
		return getValue().containsValue(value);
	}

	/**
	 * @return
	 * @see java.util.Map#entrySet()
	 */
	public Set<java.util.Map.Entry<Object, Object>> entrySet () {
		return getValue().entrySet();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Map#equals(java.lang.Object)
	 */
	@Override
	public boolean equals (final Object o) {
		return getValue().equals(o);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Object get (final Object key) {
		return getValue().get(key);
	}

	/**
	 * @return
	 * @see java.util.Map#hashCode()
	 */
	@Override
	public int hashCode () {
		return getValue().hashCode();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty () {
		return getValue().isEmpty();
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public Object js (final Object e) {
		if (null == e) {
			return null;
		}
		Object o = e;
		if (!(e instanceof JSValue<?>)) {
			if (e instanceof String) {
				o = new JSString((String) e);
			} else if (e instanceof Number) {
				o = new JSNumber((Number) e);
			} else if (e instanceof Boolean) {
				o = new JSBoolean((Boolean) e);
			}
		}
		return o;
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	public Set<Object> keySet () {
		return getValue().keySet();
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public JSObject put (final Object key, final Object value) {
		getValue().put(new JSString(String.valueOf(key)), js(value));
		return this;
	}

	/**
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll (final Map<? extends Object, ? extends Object> m) {
		getValue().putAll(m);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public Object remove (final Object key) {
		return getValue().remove(key);
	}

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	public int size () {
		return getValue().size();
	}

	/**
	 * @return
	 * @see java.util.Map#values()
	 */
	public Collection<Object> values () {
		return getValue().values();
	}

}
