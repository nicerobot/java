package org.nicerobot.io;


import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

public interface PipeableStringBuffer {

	public abstract StringWriter process (final Reader reader, final StringWriter writer)
			throws IOException;

}