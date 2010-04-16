package org.nicerobot.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.CharBuffer;

/**
 * @author nicerobot
 * 
 */
public class StringBufferReader extends Reader implements Readable {

	/**
	 * 
	 */
	private final StringBuffer buffer;

	/**
	 * 
	 */
	private long loc;

	/**
	 * 
	 */
	private long mark = 0;

	/**
	 * @param buffer
	 */
	public StringBufferReader (final StringBuffer buffer) {
		this.buffer = buffer;
	}

	/**
	 * @param writer
	 */
	public StringBufferReader (final StringWriter writer) {
		this(writer.getBuffer());
	}

	@Override
	public void close () throws IOException {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#mark(int)
	 */
	@Override
	public void mark (final int readAheadLimit) throws IOException {
		this.mark = this.loc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#markSupported()
	 */
	@Override
	public boolean markSupported () {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#read()
	 */
	@Override
	public int read () throws IOException {
		if (this.buffer.length() == this.loc) {
			return -1;
		}
		return this.buffer.charAt((int) this.loc++);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#read(char[])
	 */
	@Override
	public int read (final char[] cbuf) throws IOException {
		throw new RuntimeException("4");
	}

	/* (non-Javadoc)
	 * @see java.io.Reader#read(char[], int, int)
	 */
	@Override
	public int read (final char[] cbuf, final int off, int len) throws IOException {
		if (0 == len) {
			return 0;
		}
		if (-1 == this.loc) {
			return -1;
		}
		final int n = this.buffer.length();
		if (n == this.loc) {
			return -1;
		}
		if (off > n) {
			return -1;
		}
		if (off + len > n) {
			len = n - off;
		}
		this.buffer.getChars(off, (int) (this.loc = off + len), cbuf, 0);
		return len;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#read(java.nio.CharBuffer)
	 */
	@Override
	public int read (final CharBuffer target) throws IOException {
		throw new RuntimeException("3");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#ready()
	 */
	@Override
	public boolean ready () throws IOException {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#reset()
	 */
	@Override
	public void reset () throws IOException {
		this.loc = this.mark;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#skip(long)
	 */
	@Override
	public long skip (final long n) throws IOException {
		final long orig = this.loc;
		this.loc += n;
		if (0 < n) {
			if (this.loc > this.buffer.length()) {
				this.loc = this.buffer.length();
			}
			return this.loc - orig;
		} else if (0 > n) {
			if (0 > this.loc) {
				this.loc = 0;
			}
			return orig - this.loc;
		}
		return 0;
	}
}
