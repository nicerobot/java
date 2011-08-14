package org.nicerobot.util;

public class CommandLine {

  /**
   * @param args
   */
  public static void main (final String... args) {
    new CommandLine(args);
  }

  @SuppressWarnings ("unused")
  private final String[] args;

  public CommandLine (final String... _args) {
    this.args = _args;
  }

}
