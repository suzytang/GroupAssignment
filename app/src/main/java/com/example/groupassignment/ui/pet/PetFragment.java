package com.example.groupassignment.ui.pet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;
import com.example.groupassignment.ui.shop.Shop;

import java.sql.SQLInput;

public class PetFragment extends Fragment implements View.OnClickListener{

    private PetViewModel petViewModel;
    private Shop shop = new Shop();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        petViewModel =
                ViewModelProviders.of(this).get(PetViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pet_test, container, false);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());

        ImageButton inventoryButton = (ImageButton) root.findViewById(R.id.inventoryButton);
        ImageButton feedButton = (ImageButton) root.findViewById(R.id.feedButton);

        ImageView wallpaper = (ImageView) root.findViewById(R.id.wallpaper);

        TextView coins = (TextView) root.findViewById(R.id.coins);
        TextView level = (TextView) root.findViewById(R.id.level);
        TextView status = (TextView) root.findViewById(R.id.status);
        TextView foodQty = (TextView) root.findViewById(R.id.foodQty);

        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");
        level.setText(sqLiteHelper.getPetData(SQLiteHelper.LVL));
        status.setText(sqLiteHelper.getPetData(SQLiteHelper.STATUS));
        foodQty.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 2)+ " can(s)");

        int wallpaperAmount = sqLiteHelper.getSum("'Wallpapers'");
        if(wallpaperAmount != 0){
            try{
                String wallpaperName = sqLiteHelper.getInventory(SQLiteHelper.COL_2, SQLiteHelper.COL_3, "'Wallpapers'");
                wallpaper.setImageResource(shop.searchWallpapers(wallpaperName).getImage());
            }catch(Exception e){
                System.out.println("Wallpaper not yet applied");
            }
        }

        ImageView sunglasses = (ImageView) root.findViewById(R.id.sunglasses);
        ImageView cap = (ImageView) root.findViewById(R.id.cap);
        ImageView tophat = (ImageView) root.findViewById(R.id.tophat);
        ImageView glasses = (ImageView) root.findViewById(R.id.glasses);
        ImageView pirateHat = (ImageView) root.findViewById(R.id.pirateHat);
        ImageView wig = (ImageView) root.findViewById(R.id.wig);

        sunglasses.setVisibility(root.INVISIBLE);
        cap.setVisibility(root.INVISIBLE);
        tophat.setVisibility(root.INVISIBLE);
        glasses.setVisibility(root.INVISIBLE);
        pirateHat.setVisibility(root.INVISIBLE);
        wig.setVisibility(root.INVISIBLE);

        try{
           String accessoryName = sqLiteHelper.getInventory(SQLiteHelper.COL_2, SQLiteHelper.COL_3, "'Accessories'");
           switch(accessoryName){
               case "Sunglasses":
                   try{
                       String applied = sqLiteHelper.getInventory(SQLiteHelper.COL_2, SQLiteHelper.COL_2, "'Glasses'");
                       if(applied == "Glasses"){
                           glasses.setVisibility(root.INVISIBLE);
                       }
                   }catch (Exception e){
                       System.out.println("Glasses not set");
                   }
                   sunglasses.setVisibility(root.VISIBLE);
                   break;
               case "Cap":
                   cap.setVisibility(root.VISIBLE);
                   break;
               case "Top Hat":
                   tophat.setVisibility(root.VISIBLE);
                   break;
               case "Glasses":
                   try{
                       String applied = sqLiteHelper.getInventory(SQLiteHelper.COL_2, SQLiteHelper.COL_2, "'Sunglasses'");
                       if(applied == "Sunglasses"){
                           sunglasses.setVisibility(root.INVISIBLE);
                       }
                   }catch (Exception e){
                       System.out.println("Sunglasses not set");
                   }
                   glasses.setVisibility(root.VISIBLE);
                   break;
               case "Pirate Hat":
                   pirateHat.setVisibility(root.VISIBLE);
                   break;
               case "Wig":
                   wig.setVisibility(root.VISIBLE);
                   break;
            }

        }catch(Exception e){
           System.out.println("Accessory not yet applied");
        }

        feedButton.setOnClickListener(this);
        inventoryButton.setOnClickListener(this);

        long time = sqLiteHelper.getPetTime(SQLiteHelper.TIME);
        long timeElapsed = System.currentTimeMillis() - time;

        if(timeElapsed >= 5*60*60*1000){
            sqLiteHelper.updatePetData(SQLiteHelper.STATUS, "Hungry",1);
            status.setText(sqLiteHelper.getPetData(SQLiteHelper.STATUS));
        }

        return root;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        TextView status = (TextView) getActivity().findViewById(R.id.status);
        TextView foodQty = (TextView) getActivity().findViewById(R.id.foodQty);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        status.setText(sqLiteHelper.getPetData(SQLiteHelper.STATUS));
        foodQty.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 2)+ " can(s)");

        long time = System.currentTimeMillis();
        sqLiteHelper.updatePetTime(SQLiteHelper.TIME, time, 1);
    }

    @Override
    public void onClick(View v){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        switch(v.getId()){
            case R.id.inventoryButton:
                Intent intent = new Intent(getContext(), PetInventory.class);
                startActivity(intent);
                break;
            case R.id.feedButton:
                int foodQty = Integer.parseInt(sqLiteHelper.getData(SQLiteHelper.COL_4, 2));
                if(foodQty > 0){
                    sqLiteHelper.update(2, "'Food'", "'Food'", foodQty-1);
                    sqLiteHelper.updatePetData(SQLiteHelper.STATUS, "Satisfied",1);

                    onViewCreated(v,null);
                }else{
                    Toast.makeText(getActivity(),
                            "You don't have any food left!", Toast.LENGTH_LONG).show();
                }
        }

    }
}