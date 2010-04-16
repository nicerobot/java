package org.nicerobot.info;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Provider;
import java.security.Security;
import java.security.Provider.Service;
import java.util.Map;
import java.util.TreeMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Info {

  private static int depth = 1;
  private static PrintWriter err = null;
  static {
    try {
      Info.err = null != System.console() ? System.console().writer() : new PrintWriter(System.err);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @param args
   * @throws BackingStoreException
   * @throws UnknownHostException
   */
  public static void main (final String... args) throws BackingStoreException, UnknownHostException {
    final StringBuilder ipAddress = new StringBuilder();
    for (final byte b : InetAddress.getLocalHost().getAddress()) {
      if (0 != ipAddress.length()) {
        ipAddress.append(".");
      }
      ipAddress.append(b & 0xFF);
    }
    System.out.println(ipAddress);
    Info.printEnvironvment();
    Info.printProperties();
    Info.printPreferences();
    Info.printSecurity();
    Info.err.flush();
  }

  public static void print (final Preferences p) throws BackingStoreException {
    if (null == p) {
      return;
    }
    final String in = String.format("%-" + String.format("%d", Info.depth * 2) + "s", " ");
    Info.err.format("\n%s--\n%sNode: %s\n", in, in, p);
    Info.err.format("%sPath: %s\n", in, p.absolutePath());
    Info.err.format("%sName: %s\n", in, p.name());
    Info.err.format("%sParent: %s\n", in, p.parent());
    Info.err.format("%sValues:\n", in);
    for (final String s : p.keys()) {
      Info.err.format("%s%s%-" + String.format("%d", Info.depth * 2) + "s = %s\n", in, in, s,
                      p.get(s, null));
    }
    Info.err.format("%sChildren:\n", in);
    for (final String s : p.childrenNames()) {
      Info.print(p.node(s));
    }

  }

  /**
   * @param args
   * @throws BackingStoreException
   */
  public static void printEnvironvment () {
    Info.err.format("Environment Variables\n-------\n");
    final Map<String, String> e = new TreeMap<String, String>(System.getenv());
    for (final Map.Entry<String, String> s : e.entrySet()) {
      Info.err.format("%s\n", s);
    }
  }

  /**
   * @throws BackingStoreException
   * 
   */
  public static void printPreferences () throws BackingStoreException {
    Info.print(Preferences.systemRoot());
    Info.print(Preferences.userRoot());
    Info.print(Preferences.systemNodeForPackage(Info.class));
    Info.print(Preferences.userNodeForPackage(Info.class));
  }

  /**
   * @param args
   * @throws BackingStoreException
   */
  public static void printProperties () {
    Info.err.format("\nSystem Properties\n-------\n");
    final Map<Object, Object> p = new TreeMap<Object, Object>(System.getProperties());
    for (final Map.Entry<Object, Object> s : p.entrySet()) {
      Info.err.format("%s\n", s);
    }
    if ("Mac OS X".equals(p.get("os.name"))) {
      Info.err.format("\nPreferences Store\n-------\n\t%s/%s", p.get("user.home"),
                      "Library/Preferences/com.apple.java.util.prefs.plist");
    }
  }

  /**
   * 
   */
  public static void printSecurity () {
    Info.err.format("\nSecurity Providers\n-------\n");
    for (final Provider p : Security.getProviders()) {
      for (final Service s : p.getServices()) {
        Info.err.format("%s %s\n", p.getName(), s.getAlgorithm());
      }
    }
  }
}
