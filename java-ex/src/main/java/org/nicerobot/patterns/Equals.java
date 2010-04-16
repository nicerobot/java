package org.nicerobot.patterns;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

/**
 * Introduces @SymmetricEquality TYPE annotation to indicate that .equals performs symmetrically
 * with respect to super types.
 * 
 * http://www.artima.com/lejava/articles/equality.html
 * http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html#equals%28java.lang.Object%29
 * 
 * It is reflexive: for any non-null reference value x, x.equals(x) should return true.
 * 
 * It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and
 * only if y.equals(x) returns true.
 * 
 * It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and
 * y.equals(z) returns true, then x.equals(z) should return true.
 * 
 * It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y)
 * consistently return true or consistently return false, provided no information used in equals
 * comparisons on the objects is modified.
 * 
 * For any non-null reference value x, x.equals(null) should return false.
 */

enum Color {
  RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;
}

@SymmetricEquality
class ColoredPoint extends Point {

  private final Color color;

  public ColoredPoint (final int x, final int y, final Color color) {
    super(x, y);
    this.color = color;
  }

  @Override
  public boolean equals (final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      if (this.getClass().isAnnotationPresent(SymmetricEquality.class)) {
        if (obj.getClass().isAssignableFrom(this.getClass())) {
          return obj.equals(this);
        }
        if (!this.getClass().isAssignableFrom(obj.getClass())) {
          return false;
        }
      } else {
        return false;
      }
    }
    final ColoredPoint other = (ColoredPoint) obj;
    if (this.color == null) {
      if (other.color != null) {
        return false;
      }
    } else if (!this.color.equals(other.color)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.color == null) ? 0 : this.color.hashCode());
    return result;
  }

}

public class Equals {

  /**
   * @param args
   */
  public static void main (final String[] args) {

    System.out.format("Point : Point\n\n");

    // It is reflexive: for any non-null reference value x, x.equals(x) should return true.
    {
      final Point p1 = new Point(1, 2);
      final Point p2 = new Point(2, 3);
      System.out.format("%-20s = %-5b\n", "p1.equals(p2)", (p1.equals(p2)));
      System.out.format("%-20s = %-5b\n", "p2.equals(p1)", (p2.equals(p1)));
      System.out.format("%-20s = %-5b\n\n", "reflexive", (p1.equals(p1)));
    }

    // It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if
    // and only if y.equals(x) returns true.
    {
      final Point p1 = new Point(1, 2);
      final Point p2 = new Point(1, 2);
      System.out.format("%-20s = %-5b\n", "p1.equals(p2)", p1.equals(p2));
      System.out.format("%-20s = %-5b\n", "p2.equals(p1)", p2.equals(p1));
      System.out.format("%-20s = %-5b\n\n", "symmetric", (p1.equals(p2) && (p2.equals(p1))));

      final Set<Point> h = new HashSet<Point>();
      h.add(p1);
      System.out.format("%-20s = %-5s\n", "h.size()", h);
      System.out.format("%-20s = %-5b\n\n", "h.contains(p2)", h.contains(p2));
    }
    // It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true
    // and y.equals(z) returns true, then x.equals(z) should return true.
    {
      final Point p1 = new Point(1, 2);
      final Point p2 = new Point(1, 2);
      final Point p3 = new Point(1, 2);
      System.out.format("%-20s = %-5b\n", "p1.equals(p2)", p1.equals(p2));
      System.out.format("%-20s = %-5b\n", "p2.equals(p3)", p2.equals(p3));
      System.out.format("%-20s = %-5b\n", "p3.equals(p2)", p3.equals(p2));
      System.out.format("%-20s = %-5b\n", "p3.equals(p1)", p3.equals(p1));
      System.out.format("%-20s = %-5b\n\n", "transitive", p1.equals(p3));

      final Set<Point> h = new HashSet<Point>();
      h.add(p1);
      System.out.format("%-20s = %-5s\n", "h.size()", h);
      System.out.format("%-20s = %-5b\n\n", "h.contains(p3)", h.contains(p3));
    }
    // It is consistent: for any non-null reference values x and y, multiple invocations of
    // x.equals(y) consistently return true or consistently return false, provided no information
    // used in equals comparisons on the objects is modified.
    {}
    // For any non-null reference value x, x.equals(null) should return false.
    {
      System.out.format("%-20s = %-5b\n\n", "non-null", new Point(1, 2).equals(null));
    }

    System.out.format("Point : ColorPoint\n\n");

    // It is reflexive: for any non-null reference value x, x.equals(x) should return true.
    {
      final Point p1 = new Point(1, 2);
      System.out.format("%-20s = %-5b\n\n", "reflexive", (p1.equals(p1)));
    }

    // It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if
    // and only if y.equals(x) returns true.
    {
      final Point p1 = new Point(1, 2);
      final Point cp1 = new ColoredPoint(1, 2, Color.RED);
      System.out.format("%-20s = %-5b\n", "p1.equals(cp1)", p1.equals(cp1));
      System.out.format("%-20s = %-5b\n", "cp1.equals(p1)", cp1.equals(p1));
      System.out.format("%-20s = %-5b\n\n", "symmetric", (p1.equals(cp1) && (cp1.equals(p1))));

      final Set<Point> h = new HashSet<Point>();
      h.add(p1);
      System.out.format("%-20s = %-5s\n", "h.size()", h);
      System.out.format("%-20s = %-5b\n\n", "h.contains(cp1)", h.contains(cp1));
      h.clear();
      h.add(cp1);
      System.out.format("%-20s = %-5s\n", "h.size()", h);
      System.out.format("%-20s = %-5b\n\n", "h.contains(p1)", h.contains(p1));
    }
    // It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true
    // and y.equals(z) returns true, then x.equals(z) should return true.
    {
      final Point p1 = new Point(1, 2);
      final Point cp1 = new ColoredPoint(1, 2, Color.RED);
      final Point cp2 = new ColoredPoint(1, 2, Color.BLUE);
      System.out.format("%-20s = %-5b\n", "p1.equals(cp1)", p1.equals(cp1));
      System.out.format("%-20s = %-5b\n", "p1.equals(cp2)", p1.equals(cp2));
      System.out.format("%-20s = %-5b\n", "cp1.equals(p1)", cp1.equals(p1));
      System.out.format("%-20s = %-5b\n", "cp2.equals(p1)", cp2.equals(p1));
      System.out.format("%-20s = %-5b\n\n", "transitive", cp1.equals(cp2));

      final Set<Point> h = new HashSet<Point>();
      h.add(p1);
      h.add(cp2);
      System.out.format("%-20s = %-5s\n", "h.size()", h);
      System.out.format("%-20s = %-5b\n\n", "h.contains(cp1)", h.contains(cp1));
    }
    // It is consistent: for any non-null reference values x and y, multiple invocations of
    // x.equals(y) consistently return true or consistently return false, provided no information
    // used in equals comparisons on the objects is modified.
    {}
    // For any non-null reference value x, x.equals(null) should return false.
    {
      System.out.format("%-20s = %-5b\n\n", "non-null", new Point(1, 2).equals(null));
    }
  }

}

// @SymmetricEquality
class Point {

  private int x;
  private int y;

  public Point (final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals (final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      if (this.getClass().isAnnotationPresent(SymmetricEquality.class)) {
        if (obj.getClass().isAssignableFrom(this.getClass())) {
          return obj.equals(this);
        }
        if (!this.getClass().isAssignableFrom(obj.getClass())) {
          return false;
        }
      } else {
        return false;
      }
    }
    final Point other = (Point) obj;
    if (this.x != other.x) {
      return false;
    }
    if (this.y != other.y) {
      return false;
    }
    return true;
  }

  public int getX () {
    return this.x;
  }

  public int getY () {
    return this.y;
  }

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.x;
    result = prime * result + this.y;
    return result;
  }

  public void setX (final int x) { // Problematic
    this.x = x;
  }

  public void setY (final int y) {
    this.y = y;
  }
}

@Target (ElementType.TYPE)
@Retention (RetentionPolicy.RUNTIME)
@interface SymmetricEquality {}
