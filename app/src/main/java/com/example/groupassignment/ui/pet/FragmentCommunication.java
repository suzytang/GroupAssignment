package com.example.groupassignment.ui.pet;

import android.os.Bundle;

import com.example.groupassignment.SQLiteHelper;

public class FragmentCommunication {

    public String name;


    public void respond(int position,String name) {
        this.name = name;
        PetFragment fragment = new PetFragment();
        Bundle bundle = new Bundle();
        bundle.putString("wallpaperName",name);
        fragment.setArguments(bundle);

        if(bundle != null){
            System.out.println(name);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
