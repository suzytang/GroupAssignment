package com.example.groupassignment.ui.learn;

import java.util.ArrayList;

public class LearnCategories {
    private int level;
    private String categoryName;

    public LearnCategories() {
    }

    public LearnCategories(int level, String categoryName) {
        this.level = level;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public static ArrayList<LearnCategories> getCategories() {
        ArrayList<LearnCategories> categories = new ArrayList<>();
        categories.add(new LearnCategories(1, "General"));
        categories.add(new LearnCategories(2, "Conversation"));
        categories.add(new LearnCategories(3, "Understanding"));
        categories.add(new LearnCategories(4, "Purchase"));
        categories.add(new LearnCategories(5, "Transport"));
        categories.add(new LearnCategories(6, "Food & Drinks"));
        categories.add(new LearnCategories(7, "Directions"));
        categories.add(new LearnCategories(8, "Accommodation"));
        categories.add(new LearnCategories(9, "Emergency"));
        return categories;
    }
}
