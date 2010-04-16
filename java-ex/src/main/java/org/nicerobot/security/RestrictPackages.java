package org.nicerobot.security;

import org.nicerobot.io.Sync;

public class RestrictPackages extends SecurityManager {

  public static void main (final String[] args) {
    System.setSecurityManager(new RestrictPackages());
    Sync.out.format("%s\n", org.nicerobot.io.Find.class);
    Sync.out.format("%s\n", javax.script.AbstractScriptEngine.class);
  }

  @Override
  public void checkPackageAccess (final String name) {
    this.checkPackageDefinition(name);
  }

  @Override
  public void checkPackageDefinition (final String name) {
    if (name.startsWith("java.")) {
      // permission granted
      return;
    }
    if (name.startsWith("sun.")) {
      // permission granted
      return;
    }
    if (name.startsWith("org.nicerobot.")) {
      // permission granted
      return;
    }
    // permission denied
    throw new SecurityException("Permission denied for " + name);
  }

}
