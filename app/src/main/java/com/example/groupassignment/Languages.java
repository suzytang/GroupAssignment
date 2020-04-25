package com.example.groupassignment;

import java.util.ArrayList;
import java.util.Locale;

// Languages class for RecyclerView and setting Locale for TTS
public class Languages {
    private String language;
    private Locale locale;
    private int image;
    private String difficulty;

    public Languages(String language, Locale localeLanguage, int image, String difficulty) {
        this.language = language;
        this.locale = localeLanguage;
        this.image = image;
        this.difficulty = difficulty;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static ArrayList<Languages> getLanguages() {
        ArrayList<Languages> languages = new ArrayList<>();
        languages.add(new Languages("English", Locale.ENGLISH, R.drawable.uk, "For Testing"));
        languages.add(new Languages("Chinese", Locale.CHINESE, R.drawable.china, "Category V"));
        languages.add(new Languages("Korean", Locale.KOREAN, R.drawable.southkorea, "Category V"));
        languages.add(new Languages("Japanese", Locale.JAPANESE, R.drawable.japan, "Category V"));
        languages.add(new Languages("German", Locale.GERMAN, R.drawable.germany, "Category II"));
        languages.add(new Languages("French", Locale.FRENCH, R.drawable.france, "Category I"));
        languages.add(new Languages("Italian", Locale.ITALIAN, R.drawable.italy, "Category I"));
        return languages;
    }
}
