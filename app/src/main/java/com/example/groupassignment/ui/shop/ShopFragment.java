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

import com.example.groupassignment.R;

public class ShopFragment extends Fragment implements View.OnClickListener{

    private ShopViewModel shopViewModel;
    private Button foodButton, accessoriesButton, wallpapersButton, cancelButton;
    private Dialog dialog;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        Button foodButton = (Button) root.findViewById(R.id.foodButton);
        Button accessoriesButton = (Button) root.findViewById(R.id.accessoriesButton);
        Button wallpapersButton = (Button) root.findViewById(R.id.wallpapersButton);

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
