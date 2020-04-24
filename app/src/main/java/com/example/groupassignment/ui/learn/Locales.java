package com.example.groupassignment.ui.learn;

import java.util.ArrayList;
import java.util.Locale;

public class Locales {
    public static ArrayList<Locale> setLocale() {
        ArrayList<Locale> locales = new ArrayList<>();
        locales.add(Locale.CHINESE);
        locales.add(Locale.KOREAN);
        locales.add(Locale.JAPANESE);
        locales.add(Locale.GERMAN);
        locales.add(Locale.FRENCH);
        locales.add(Locale.ITALIAN);
        return locales;
    }

    public static Locale getLocale(int i) {
        ArrayList<Locale> locales = setLocale();
        for (int j = 0; j < locales.size(); j++) {
          if (j==i-1) {
              return locales.get(j);
          }
        }
        return null;
    }
}
