package com.example.groupassignment.ui.learn;

import com.example.groupassignment.R;

import java.util.ArrayList;

// LearnCategories class for RecyclerView
public class LearnCategories {
    private int level;
    private String categoryName;
    private int image;

    public LearnCategories() {
    }

    public LearnCategories(int level, String categoryName, int image) {
        this.level = level;
        this.categoryName = categoryName;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static ArrayList<LearnCategories> getCategories() {
        ArrayList<LearnCategories> categories = new ArrayList<>();
        categories.add(new LearnCategories(1, "General", R.drawable.general));
        categories.add(new LearnCategories(2, "Conversation", R.drawable.conversation));
        categories.add(new LearnCategories(3, "Understanding", R.drawable.understand));
        categories.add(new LearnCategories(4, "Purchase", R.drawable.purchase));
        categories.add(new LearnCategories(5, "Transport", R.drawable.transport));
        categories.add(new LearnCategories(6, "Food & Drinks", R.drawable.burger));
        categories.add(new LearnCategories(7, "Directions", R.drawable.directions));
        categories.add(new LearnCategories(8, "Accommodation", R.drawable.accomodation));
        categories.add(new LearnCategories(9, "Emergency", R.drawable.emergency));
        return categories;
    }
}
