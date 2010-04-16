package org.nicerobot.util.logging;

import java.util.logging.Logger;

public interface LogLevels {
	LevelLogger.ExitLogger entering (Class<?> class_, String sourceMethod_, Object... params_);

	LevelLogger.ExitLogger entering (Object inst_, String sourceMethod_, Object... params_);

	public Logger config (Object args);

	public Logger config (String format, Object... args);

	public Logger fine (Object args);

	public Logger fine (String format, Object... args);

	public Logger finer (Object args);

	public Logger finer (String format, Object... args);

	public Logger finest (Object args);

	public Logger finest (String format, Object... args);

	public Logger info (Object args);

	public Logger info (String format, Object... args);

	public Logger severe (Object args);

	public Logger severe (String format, Object... args);

	public Logger warning (Object args);

	public Logger warning (String format, Object... args);

}
