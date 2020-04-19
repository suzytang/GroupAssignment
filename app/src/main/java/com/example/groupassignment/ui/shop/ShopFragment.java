package com.example.groupassignment.ui.shop;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;
import com.google.android.material.tabs.TabLayout;

public class ShopFragment extends Fragment{

    private ShopViewModel shopViewModel;
    private Button foodButton, accessoriesButton, wallpapersButton, cancelButton;
    private Dialog dialog;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shopViewModel =
                ViewModelProviders.of(this).get(ShopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), getChildFragmentManager());
        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        /*Button foodButton = (Button) root.findViewById(R.id.foodButton);
        Button accessoriesButton = (Button) root.findViewById(R.id.accessoriesButton);
        Button wallpapersButton = (Button) root.findViewById(R.id.wallpapersButton);*/

        /*TextView coins = (TextView) root.findViewById(R.id.coins);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");*/

        /*dialog = new Dialog(getActivity());

        foodButton.setOnClickListener(this);
        accessoriesButton.setOnClickListener(this);
        wallpapersButton.setOnClickListener(this);*/

        //doBackBtnPressedAction(root);

        return root;
    }

    /*@Override
    public void onViewCreated(View view, Bundle savedInstancestate){
        TextView coins = (TextView) getActivity().findViewById(R.id.coins);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");
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
                Button buyButton = (Button) dialog.findViewById(R.id.buyButton);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                buyButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                        int coinsCurrrent = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 1));
                        int itemPrice = 20;

                        if (coinsCurrrent - itemPrice >= 0){
                            int paid = (coinsCurrrent - itemPrice);
                            sqLiteHelper.update(1, "'Coins'", "'Coins'", paid);
                            int foodQty = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4,2));
                            sqLiteHelper.update(2, "'Food'", "'Food'", foodQty + 1);

                            onViewCreated(v,null);
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Food has been added to your inventory!", Toast.LENGTH_LONG).show();

                        } else{
                            Toast.makeText(getContext(),"You don't have enough coins to purchase this!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.wallpapersButton:
                Intent intentWallpaper = new Intent(getActivity(), WallpapersList.class);
                startActivity(intentWallpaper);
                break;
            case R.id.accessoriesButton:
                Intent intentAccessories = new Intent(getActivity(), AccessoriesList.class);
                startActivity(intentAccessories);
                break;

        }


    }
    public void doBackBtnPressedAction(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                        TextView coins = (TextView) getActivity().findViewById(R.id.coins);
                        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1) + " coins");
                        System.out.println("working");

                        return true;
                    }
                }
                return false;
            }
        });

    }*/

}
