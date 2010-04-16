package org.nicerobot.javascript;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JSBlock extends JSArray<Object> {

	/**
	 * 
	 */
	public JSBlock () {
		super();
	}

	/**
	 * 
	 */
	@SuppressWarnings ("unchecked")
	protected JSBlock (final JSBlock toclone) {
		super((ArrayList<Object>) toclone.getValue().clone());
	}

	/**
	 * @param index
	 * @param element
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	@Override
	public void add (final int index, final Object element) {
		getValue().add(index, js(element));
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public boolean add (final Object e) {
		return getValue().add(js(e));
	}

	/**
	 * 
	 * @see java.util.ArrayList#clear()
	 */
	@Override
	public void clear () {
		getValue().clear();
	}

	/**
	 * @return
	 * @see java.util.ArrayList#clone()
	 */
	@Override
	public Object clone () {
		return new JSBlock(this);
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
		if (!(obj instanceof JSBlock)) {
			return false;
		}
		return true;
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	@Override
	public Object get (final int index) {
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
	 * @return
	 * @see java.util.AbstractList#iterator()
	 */
	@Override
	public Iterator<Object> iterator () {
		return getValue().iterator();
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public Object js (final Object e) {
		return super.js(e);
	}

	/**
	 * @return
	 * @see java.util.AbstractList#listIterator()
	 */
	@Override
	public ListIterator<Object> listIterator () {
		return super.listIterator();
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.AbstractList#listIterator(int)
	 */
	@Override
	public ListIterator<Object> listIterator (final int index) {
		return super.listIterator(index);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public JSBlock put (final Object... elements) {
		for (final Object e : elements) {
			add(e);
		}
		return this;
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#remove(int)
	 */
	@Override
	public Object remove (final int index) {
		return getValue().remove(index);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	@Override
	public boolean remove (final Object o) {
		return getValue().remove(o);
	}

	/**
	 * @param index
	 * @param element
	 * @return
	 * @see java.util.ArrayList#set(int, java.lang.Object)
	 */
	public Object set (final int index, final String element) {
		return super.set(index, element);
	}

	/**
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 * @see java.util.AbstractList#subList(int, int)
	 */
	@Override
	public List<Object> subList (final int fromIndex, final int toIndex) {
		return super.subList(fromIndex, toIndex);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#toArray()
	 */
	@Override
	public Object[] toArray () {
		return super.toArray();
	}

	/**
	 * @param <T>
	 * @param a
	 * @return
	 * @see java.util.ArrayList#toArray(T[])
	 */
	@Override
	@SuppressWarnings ("hiding")
	public <Object> Object[] toArray (final Object[] a) {
		return super.toArray(a);
	}

}
