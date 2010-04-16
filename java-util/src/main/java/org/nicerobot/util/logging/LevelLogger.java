package org.nicerobot.util.logging;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author nicerobot
 * 
 */
public final class LevelLogger implements LogLevels {

	/**
	 * 
	 */
	private final Logger logger;

	/**
	 * @author nicerobot
	 * 
	 */
	public class ExitLogger {

		/**
		 * 
		 */
		private final String _name;

		/**
		 * 
		 */
		private final String _method;

		/**
		 * @param name_
		 * @param method_
		 */
		public ExitLogger (final String name_, final String method_) {
			this._name = name_;
			this._method = method_;
		}

		/**
		 * @param result_
		 */
		public void error (final Object... result_) {
			final List<Object> result = Arrays.asList(result_);
			LevelLogger.this.severe("RETURN %s", String.valueOf(result));
		}

		/**
		 * @param string_
		 * @param string2_
		 */
		public void info (final Object... result_) {
			log(result_);
		}

		/**
		 * @param result_
		 */
		public void log (final Object... result_) {
			final List<Object> result = Arrays.asList(result_);
			LevelLogger.this.logger.exiting(this._name, this._method, String.valueOf(result));
		}

		/**
		 * @param string_
		 * @param string2_
		 */
		public void ok (final Object... result_) {
			successfully(result_);
		}

		/**
		 * @param string_
		 * @param string2_
		 */
		public void successfully (final Object... result_) {
			log("SUCCESS", result_);
		}

		/**
		 * @param result_
		 */
		public void warning (final Object... result_) {
			final List<Object> result = Arrays.asList(result_);
			LevelLogger.this.warning("RETURN %s", String.valueOf(result));
		}
	}

	/**
	 * 
	 */
	public LevelLogger () {
		this(Logger.getAnonymousLogger());
	}

	/**
	 * @param logger
	 */
	public LevelLogger (Logger logger) {
		if (null == logger) {
			logger = Logger.getAnonymousLogger();
		}
		this.logger = logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#config(java.lang.Object)
	 */
	@Override
	public Logger config (final Object args_) {
		this.logger.config(String.valueOf(args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#config(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Logger config (final String format_, final Object... args_) {
		this.logger.config(String.format(format_, args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#entering(java.lang.Class, java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public ExitLogger entering (final Class<?> class_, final String sourceMethod_,
			final Object... params_) {
		final List<Object> params = Arrays.asList(params_);
		this.logger.entering(class_.getName(), sourceMethod_, String.valueOf(params));
		return new ExitLogger(class_.getName(), sourceMethod_);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#entering(java.lang.Object, java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public ExitLogger entering (final Object inst_, final String sourceMethod_,
			final Object... params_) {
		return entering(inst_.getClass(), sourceMethod_, params_);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#fine(java.lang.Object)
	 */
	@Override
	public Logger fine (final Object args_) {
		this.logger.fine(String.valueOf(args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#fine(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Logger fine (final String format_, final Object... args_) {
		this.logger.fine(String.format(format_, args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#finer(java.lang.Object)
	 */
	@Override
	public Logger finer (final Object args_) {
		this.logger.finer(String.valueOf(args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#finer(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Logger finer (final String format_, final Object... args_) {
		this.logger.finer(String.format(format_, args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#finest(java.lang.Object)
	 */
	@Override
	public Logger finest (final Object args_) {
		this.logger.finest(String.valueOf(args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#finest(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Logger finest (final String format_, final Object... args_) {
		this.logger.finest(String.format(format_, args_));
		return this.logger;
	}

	/**
	 * @return the logger
	 */
	/**
	 * @return
	 */
	public Logger getLogger () {
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#info(java.lang.Object)
	 */
	@Override
	public Logger info (final Object args_) {
		this.logger.info(String.valueOf(args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#info(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Logger info (final String format_, final Object... args_) {
		this.logger.info(String.format(format_, args_));
		return this.logger;
	}

	/**
	 * @param finer_
	 * @return
	 */
	public boolean isLoggable (final Level level_) {
		return this.logger.isLoggable(level_);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#severe(java.lang.Object)
	 */
	@Override
	public Logger severe (final Object args_) {
		this.logger.severe(String.valueOf(args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#severe(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Logger severe (final String format_, final Object... args_) {
		this.logger.severe(String.format(format_, args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#warning(java.lang.Object)
	 */
	@Override
	public Logger warning (final Object args_) {
		this.logger.warning(String.valueOf(args_));
		return this.logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.nicerobot.util.logging.LogLevels#warning(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Logger warning (final String format_, final Object... args_) {
		this.logger.warning(String.format(format_, args_));
		return this.logger;
	}

}
