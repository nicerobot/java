package org.nicerobot.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiMap<K, V> implements Map<K, List<V>> {

	/**
	 * 
	 */
	private final Map<K, List<V>> p = new LinkedHashMap<K, List<V>>();

	public MultiMap () {
		super();
	}

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear () {
		this.p.clear();
	}

	/**
	 * @param key_
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey (final Object key_) {
		return this.p.containsKey(key_);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue (final Object value) {
		for (final K key : this.p.keySet()) {
			final List<V> v = this.p.get(key);
			if (v.contains(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return
	 * @see java.util.Map#entrySet()
	 */
	public Set<java.util.Map.Entry<K, List<V>>> entrySet () {
		return this.p.entrySet();
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
		if (!(obj instanceof MultiMap<?, ?>)) {
			return false;
		}
		final MultiMap<?, ?> other = (MultiMap<?, ?>) obj;
		if (this.p == null) {
			if (other.p != null) {
				return false;
			}
		} else if (!this.p.equals(other.p)) {
			return false;
		}
		return true;
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@SuppressWarnings ("unchecked")
	public List<V> get (final Object key) {
		List<V> v = this.p.get(key);
		if (null == v) {
			put((K) key, v = createList());
		}
		return v;
	}

	/**
	 * @return
	 * @see java.util.Map#hashCode()
	 */
	@Override
	public int hashCode () {
		return this.p.hashCode();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty () {
		return this.p.isEmpty();
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	public Set<K> keySet () {
		return this.p.keySet();
	}

	/**
	 * @param key_
	 * @param value_
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public List<V> put (final K key_, final List<V> value_) {
		return this.p.put(key_, value_);
	}

	/**
	 * @param key_
	 * @param value_
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public List<V> put (final K key_, final V value_) {
		final List<V> v = get(key_);
		v.add(value_);
		return v;
	}

	/**
	 * @param m_
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll (final Map<? extends K, ? extends List<V>> m_) {
		this.p.putAll(m_);
	}

	/**
	 * @param key_
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public List<V> remove (final Object key_) {
		return this.p.remove(key_);
	}

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	public int size () {
		return this.p.size();
	}

	/**
	 * @return
	 * @see java.util.Map#values()
	 */
	public Collection<List<V>> values () {
		return this.p.values();
	}

	/**
	 * @return
	 */
	protected List<V> createList () {
		return new ArrayList<V>();
	}

}
