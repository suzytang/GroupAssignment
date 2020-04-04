package com.example.groupassignment.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groupassignment.R;



public class ShopPopup extends AppCompatActivity {
    private Button cancelButton, buyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_popup);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button buyButton = (Button) findViewById(R.id.buyButton);



        Intent intent = getIntent();
        final String shop = intent.getStringExtra("shop");

    }

}
