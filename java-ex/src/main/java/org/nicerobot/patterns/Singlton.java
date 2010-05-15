package org.nicerobot.patterns;

enum Singleton {
  INSTANCE("A.txt");

  private Singleton (@SuppressWarnings ("unused") final String string) {}

  @Override
  public String toString () {
    return "Singleton [getClass()=" + this.getClass() + ", hashCode()=" + this.hashCode()
        + ", toString()=" + super.toString() + "]";
  }

}
