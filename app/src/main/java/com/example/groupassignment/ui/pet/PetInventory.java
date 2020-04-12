package com.example.groupassignment.ui.pet;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupassignment.R;

import java.util.ArrayList;

public class PetInventory extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_inventory);


        TextView coins = (TextView) findViewById(R.id.coins);
        TextView wallpapers = (TextView) findViewById(R.id.wallpapers);
        TextView accessories = (TextView) findViewById(R.id.accessories);



    }
}
