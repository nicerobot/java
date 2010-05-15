package org.nicerobot.util.prefs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ClassPreferences {

  public static void process (final Class<?> class_) {
    for (final Field f : class_.getFields()) {
      @SuppressWarnings ("unused")
      final Annotation p = f.getAnnotation(Preference.class);
    }
  }

}
