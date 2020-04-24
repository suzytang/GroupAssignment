package com.example.groupassignment.ui.pet;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;
import com.example.groupassignment.ui.shop.Shop;

public class PetFragment extends Fragment implements View.OnClickListener{

    private Shop shop = new Shop();
    private Dialog dialog;
    ImageButton feedButton, inventoryButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pet_test, container, false);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());

        inventoryButton = (ImageButton) root.findViewById(R.id.inventoryButton);
        feedButton = (ImageButton) root.findViewById(R.id.feedButton);
        ImageView info = (ImageView) root.findViewById(R.id.info);

        ImageView wallpaper = (ImageView) root.findViewById(R.id.wallpaper);

        TextView coins = (TextView) root.findViewById(R.id.coins);
        TextView level = (TextView) root.findViewById(R.id.level);
        TextView status = (TextView) root.findViewById(R.id.status);
        TextView foodQty = (TextView) root.findViewById(R.id.foodQty);

        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");
        level.setText(sqLiteHelper.getPetData(SQLiteHelper.LVL));
        status.setText(sqLiteHelper.getPetData(SQLiteHelper.STATUS));
        foodQty.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 2)+ " can(s)");

        // Gets the sum of AMOUNT column where CATEGORY = 'Wallpapers'
        int wallpaperAmount = sqLiteHelper.getSum("'Wallpapers'");
        // if statement checks if any wallpapers have been purchased before running try-catch
        if(wallpaperAmount != 0){
            // try statement to get the name of the wallpaper that has been applied
            try{
                String wallpaperName = sqLiteHelper.getInventory(SQLiteHelper.COL_2, SQLiteHelper.COL_3, "'Wallpapers'");
                wallpaper.setImageResource(shop.searchWallpapers(wallpaperName).getImage());
            }
            // Catches the exception where a wallpaper hasn't been applied yet even if 1 or more have been purchased
            catch(Exception e){
                System.out.println("Wallpaper not yet applied");
            }
        }

        // Initialises all accessory ImageViews
        ImageView sunglasses = (ImageView) root.findViewById(R.id.sunglasses);
        ImageView cap = (ImageView) root.findViewById(R.id.cap);
        ImageView tophat = (ImageView) root.findViewById(R.id.tophat);
        ImageView glasses = (ImageView) root.findViewById(R.id.glasses);
        ImageView pirateHat = (ImageView) root.findViewById(R.id.pirateHat);
        ImageView wig = (ImageView) root.findViewById(R.id.wig);

        // Sets all accessories to invisible
        sunglasses.setVisibility(root.INVISIBLE);
        cap.setVisibility(root.INVISIBLE);
        tophat.setVisibility(root.INVISIBLE);
        glasses.setVisibility(root.INVISIBLE);
        pirateHat.setVisibility(root.INVISIBLE);
        wig.setVisibility(root.INVISIBLE);

        // Gets the sum of AMOUNT column where CATEGORY = 'Accessories'
        int accessoryAmount = sqLiteHelper.getSum("'Accessories'");
        // if statement checks if any accessories have been purchased before running try-catch
        if(accessoryAmount != 0) {
            // try-catch for Glasses subcategory
            // accessoryName gets the name of the item in the Glasses subcategory which has been applied
            try {
                String accessoryName = sqLiteHelper.getInventory(SQLiteHelper.COL_2, SQLiteHelper.SUBCATEGORY, "'Glasses'");
                switch (accessoryName) {
                    case "Sunglasses":
                        sunglasses.setVisibility(root.VISIBLE);
                        break;
                    case "Glasses":
                        glasses.setVisibility(root.VISIBLE);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Accessory not yet applied");
            }
            // try-catch for Hat subcategory
            // accessoryName gets the name of the item in the Hat subcategory which has been applied
            try {
                String accessoryName = sqLiteHelper.getInventory(SQLiteHelper.COL_2, SQLiteHelper.SUBCATEGORY, "'Hat'");
                switch (accessoryName) {
                    case "Cap":
                        cap.setVisibility(root.VISIBLE);
                        break;
                    case "Top Hat":
                        tophat.setVisibility(root.VISIBLE);
                        break;
                    case "Pirate Hat":
                        pirateHat.setVisibility(root.VISIBLE);
                        break;
                }
            }catch (Exception e) {
                System.out.println("Accessory not yet applied");
            }
            // try-catch for Wig subcategory
            // accessoryName gets the name of the item in the Wig subcategory which has been applied
            try {
                String accessoryName = sqLiteHelper.getInventory(SQLiteHelper.COL_2, SQLiteHelper.SUBCATEGORY, "'Wig'");
                switch (accessoryName) {
                    case "Wig":
                        wig.setVisibility(root.VISIBLE);
                        break;
                }
            }catch (Exception e) {
                System.out.println("Accessory not yet applied");
            }
        }else{
            System.out.println("if failed");
        }
        feedButton.setOnClickListener(this);
        inventoryButton.setOnClickListener(this);
        info.setOnClickListener(this);

        // Gets System time last assigned from the database
        long time = sqLiteHelper.getPetTime(SQLiteHelper.TIME);
        // Variable timeElapsed is calculated by subtracting current time from time assigned in the database
        long timeElapsed = System.currentTimeMillis() - time;

        // if statement checks if timeElapsed is greater than 5 hours
        // As the default time value is 0, timeElapsed wil never be greater than 5 hours before updateUI is called
        if(timeElapsed >= 5*60*60*1000){
            // if timeElapsed is greater than 5 hours, status will be changed to 'Hungry' in the database and UI
            sqLiteHelper.updatePetData(SQLiteHelper.STATUS, "Hungry",1);
            status.setText(sqLiteHelper.getPetData(SQLiteHelper.STATUS));
        }
        return root;
    }

    public void updateUI(){
        feedButton.setImageResource(R.drawable.feed);
        AnimationDrawable feed = (AnimationDrawable) feedButton.getDrawable();
        feed.start();


        TextView status = (TextView) getActivity().findViewById(R.id.status);
        TextView foodQty = (TextView) getActivity().findViewById(R.id.foodQty);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        // Sets status once pet has been fed to 'Satisfied'
        status.setText(sqLiteHelper.getPetData(SQLiteHelper.STATUS));
        // Decreases FoodQty text once pet has been fed
        foodQty.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 2)+ " can(s)");

        // Assigns current time of system to database to be used to calculate when the status will be changed to hungry
        long time = System.currentTimeMillis();
        sqLiteHelper.updatePetTime(SQLiteHelper.TIME, time, 1);
    }

    @Override
    public void onClick(View v){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        switch(v.getId()){
            // If inventoryButton is pressed it will take the user to PetInventory
            case R.id.inventoryButton:
                Intent intent = new Intent(getContext(), PetInventory.class);
                startActivity(intent);
                break;
            case R.id.feedButton:
                int foodQty = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 2));
                // if statement checks that food quantity isn't 0
                if(foodQty > 0){
                    // If the user has enough food to feed the pet, then status is updated to 'Satisfied' and 1 food unit is subtracted
                    sqLiteHelper.update(2, "'Food'", "'Food'", foodQty-1);
                    sqLiteHelper.updatePetData(SQLiteHelper.STATUS, "Satisfied",1);

                    // updateUi method is called
                    updateUI();
                }else{
                    Toast.makeText(getActivity(),
                            "You don't have any food left!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.info:
                // Dialog for credits
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.credits);

                // Link to XML
                TextView yandex = dialog.findViewById(R.id.yandex);
                TextView petSource = dialog.findViewById(R.id.petSource);
                TextView iconsSource = dialog.findViewById(R.id.iconsSource);

                // Link to credits
                yandex.setMovementMethod(LinkMovementMethod.getInstance());
                petSource.setMovementMethod(LinkMovementMethod.getInstance());
                iconsSource.setMovementMethod(LinkMovementMethod.getInstance());

                // Decreases the opacity of the background (fragment_pet_test) and displays dialog
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                // Button dismisses dialog when pressed
                Button closeButton = (Button) dialog.findViewById(R.id.closeButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                break;
        }

    }
}
