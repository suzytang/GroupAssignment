package com.example.groupassignment.ui.shop;

import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.R;
import com.example.groupassignment.ui.DataHolder;
import com.example.groupassignment.ui.Inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShopFragment extends Fragment implements View.OnClickListener{

    private ShopViewModel shopViewModel;
    private Button foodButton, accessoriesButton, wallpapersButton, cancelButton;
    private Dialog dialog;
    //final Inventory inventory = new Inventory();




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        Button foodButton = (Button) root.findViewById(R.id.foodButton);
        Button accessoriesButton = (Button) root.findViewById(R.id.accessoriesButton);
        Button wallpapersButton = (Button) root.findViewById(R.id.wallpapersButton);
        TextView coins = (TextView) root.findViewById(R.id.coins);


        //coins.setText(DataHolder.getInstance().inventory.getCoins() + " coins");

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:infs3634.db");
            Statement st = conn.createStatement();

            String selectQuery = "SELECT coins FROM Pet WHERE id = 1;";

            ResultSet rs = st.executeQuery(selectQuery);
            while(rs.next()){
                int getCoins = rs.getInt(1);
                coins.setText(getCoins + " coins");
            }


        } catch(Exception e) {
            System.out.println("Could not get coins");
        }

        dialog = new Dialog(getActivity());

        foodButton.setOnClickListener(this);
        accessoriesButton.setOnClickListener(this);
        wallpapersButton.setOnClickListener(this);


        return root;
    }


    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.foodButton:

                dialog.setContentView(R.layout.shop_popup);

                TextView shopItem = (TextView) dialog.findViewById(R.id.shopItem);
                TextView shopPrice = (TextView) dialog.findViewById(R.id.shopPrice);

                shopItem.setText("Food");
                shopPrice.setText("20 coins");

                Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.wallpapersButton:
                Intent intent2 = new Intent(getActivity(), WallpapersList.class);
                startActivity(intent2);
                break;
            case R.id.accessoriesButton:
                Intent intent = new Intent(getActivity(), AccessoriesList.class);

                startActivity(intent);
                break;

        }


    }


}
