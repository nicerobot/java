package org.nicerobot.cli;

enum Command {
  add("add an element"), remove("remove an element"), gc("request garbage collection"), quit("exit"), exit(
      "exit"), help("this help"),

  unknown(false);

  private static String helpText;
  static {
    final StringBuilder sb = new StringBuilder();
    int max = 0;
    for (final Command c : Command.values()) {
      if (!c.isCommand) {
        continue;
      }
      final int len = c.name().length();
      if (len > max) {
        max = len;
      }
    }
    for (final Command c : Command.values()) {
      if (!c.isCommand) {
        continue;
      }
      sb.append(String.format(String.format("%%%ds - %%s%%n", max + 2), c.name(), c.description()));
    }
    helpText = sb.toString();
  }

  public static String help () {
    return helpText;
  }

  public static Command readLine () {
    return readLine("> ");
  }

  public static Command readLine (final String prompt) {
    final String command = System.console().readLine("> ");
    return Command.valueOfSubstring(command);
  }

  public static Command valueOfSubstring (final String value) {
    return valueOfSubstring(value, 1);
  }

  public static Command valueOfSubstring (final String value, final Command def) {
    return valueOfSubstring(value, 1, def);
  }

  public static Command valueOfSubstring (final String value, final int min) {
    return valueOfSubstring(value, 1, unknown);
  }

  public static Command valueOfSubstring (final String value, final int min, final Command def) {
    int len = value.length();
    if (len < min) {
      len = min;
    }
    for (final Command c : Command.values()) {
      if (!c.isCommand) {
        continue;
      }
      final String n = c.name();
      final int l = n.length();
      if (n.substring(0, len > l ? l : len).equalsIgnoreCase(value)) {
        return c;
      }
    }
    return def;
  }

  private boolean isCommand = true;

  private String description = this.name();

  private Command () {}

  private Command (final boolean isCommand) {
    this.isCommand = isCommand;
  }

  private Command (final String description) {
    this.description = description;
  }

  private Object description () {
    return this.description;
  }
}

public class Prompt extends Thread {

  public static void main (final String[] args) {
    new Prompt().start();
  }

  @Override
  public void run () {

    while (true) {
      switch (Command.readLine()) {
        case quit:
        case exit:
          System.out.format("exiting%n");
          return;
        case add:
          System.out.format("added%n");
          break;
        case remove:
          System.out.format("remove what?%n");
          break;
        case unknown:
          System.out.format("unknown command%n");
        case help:
          System.out.format("help%n%s%n", Command.help());
          break;
        case gc:
          System.out.format("gc%n");
          System.gc(); // Ask for GC
          break;
      }
    }
  }
}