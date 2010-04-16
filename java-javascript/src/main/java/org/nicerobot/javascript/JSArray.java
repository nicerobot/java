package org.nicerobot.javascript;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JSArray<T extends Object> extends JSValue<ArrayList<T>> implements List<T> {

	/**
	 * 
	 */
	public JSArray () {
		super(new ArrayList<T>());
	}

	/**
	 * 
	 */
	@SuppressWarnings ( { "unchecked" })
	private JSArray (final JSArray<T> toclone) {
		this((ArrayList<T>) toclone.getValue().clone());
	}

	/**
	 * 
	 */
	@SuppressWarnings ("unchecked")
	protected JSArray (final ArrayList<T> toclone) {
		super((ArrayList<T>) toclone.clone());
	}

	/**
	 * @param index
	 * @param element
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	public void add (final int index, final T element) {
		getValue().add(index, js(element));
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add (final T e) {
		return getValue().add(js(e));
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.ArrayList#addAll(java.util.Collection)
	 */
	public boolean addAll (final Collection<? extends T> c) {
		return getValue().addAll(c);
	}

	/**
	 * @param index
	 * @param c
	 * @return
	 * @see java.util.ArrayList#addAll(int, java.util.Collection)
	 */
	public boolean addAll (final int index, final Collection<? extends T> c) {
		return getValue().addAll(index, c);
	}

	/**
	 * 
	 * @see java.util.ArrayList#clear()
	 */
	public void clear () {
		getValue().clear();
	}

	/**
	 * @return
	 * @see java.util.ArrayList#clone()
	 */
	@Override
	public Object clone () {
		return new JSArray<T>(this);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#contains(java.lang.Object)
	 */
	public boolean contains (final Object o) {
		return getValue().contains(o);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.AbstractCollection#containsAll(java.util.Collection)
	 */
	public boolean containsAll (final Collection<?> c) {
		return getValue().containsAll(c);
	}

	/**
	 * @param minCapacity
	 * @see java.util.ArrayList#ensureCapacity(int)
	 */
	public void ensureCapacity (final int minCapacity) {
		getValue().ensureCapacity(minCapacity);
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
		if (!(obj instanceof JSArray<?>)) {
			return false;
		}
		return true;
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public T get (final int index) {
		return getValue().get(index);
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
		result = prime * result;
		return result;
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#indexOf(java.lang.Object)
	 */
	public int indexOf (final Object o) {
		return getValue().indexOf(o);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#isEmpty()
	 */
	public boolean isEmpty () {
		return getValue().isEmpty();
	}

	/**
	 * @return
	 * @see java.util.AbstractList#iterator()
	 */
	public Iterator<T> iterator () {
		return getValue().iterator();
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@SuppressWarnings ("unchecked")
	public T js (final T e) {
		if (null == e) {
			return null;
		}
		Object o = e;
		if (!(e instanceof JSValue)) {
			if (e instanceof String) {
				o = new JSString((String) e);
			} else if (e instanceof Number) {
				o = new JSNumber((Number) e);
			} else if (e instanceof Boolean) {
				o = new JSBoolean((Boolean) e);
			}
		}
		if (!o.getClass().isAssignableFrom(e.getClass())) {
			return e;
		}
		return (T) o;
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf (final Object o) {
		return getValue().lastIndexOf(o);
	}

	/**
	 * @return
	 * @see java.util.AbstractList#listIterator()
	 */
	public ListIterator<T> listIterator () {
		return getValue().listIterator();
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.AbstractList#listIterator(int)
	 */
	public ListIterator<T> listIterator (final int index) {
		return getValue().listIterator(index);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public JSArray<T> put (final T... elements) {
		for (final T e : elements) {
			add(e);
		}
		return this;
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#remove(int)
	 */
	public T remove (final int index) {
		return getValue().remove(index);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	public boolean remove (final Object o) {
		return getValue().remove(o);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.AbstractCollection#removeAll(java.util.Collection)
	 */
	public boolean removeAll (final Collection<?> c) {
		return getValue().removeAll(c);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.AbstractCollection#retainAll(java.util.Collection)
	 */
	public boolean retainAll (final Collection<?> c) {
		return getValue().retainAll(c);
	}

	/**
	 * @param index
	 * @param element
	 * @return
	 * @see java.util.ArrayList#set(int, java.lang.Object)
	 */
	public T set (final int index, final T element) {
		return getValue().set(index, element);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int size () {
		return getValue().size();
	}

	/**
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 * @see java.util.AbstractList#subList(int, int)
	 */
	public List<T> subList (final int fromIndex, final int toIndex) {
		return getValue().subList(fromIndex, toIndex);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#toArray()
	 */
	public Object[] toArray () {
		return getValue().toArray();
	}

	/**
	 * @param <T>
	 * @param a
	 * @return
	 * @see java.util.ArrayList#toArray(T[])
	 */
	@SuppressWarnings ("hiding")
	public <T> T[] toArray (final T[] a) {
		return getValue().toArray(a);
	}

	/**
	 * 
	 * @see java.util.ArrayList#trimToSize()
	 */
	public void trimToSize () {
		getValue().trimToSize();
	}

}
