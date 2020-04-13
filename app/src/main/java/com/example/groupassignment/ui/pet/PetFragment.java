package com.example.groupassignment.ui.pet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;
import com.example.groupassignment.ui.shop.AccessoriesList;

import org.w3c.dom.Text;

public class PetFragment extends Fragment implements View.OnClickListener{

    private PetViewModel petViewModel;

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

        coins.setText(sqLiteHelper.getData(SQLiteHelper.COL_4, 1)+ " coins");
        level.setText(sqLiteHelper.getPetData(SQLiteHelper.LVL));
        status.setText(sqLiteHelper.getPetData(SQLiteHelper.STATUS));

        feedButton.setOnClickListener(this);
        inventoryButton.setOnClickListener(this);

        return root;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        TextView status = (TextView) getActivity().findViewById(R.id.status);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        status.setText(sqLiteHelper.getPetData(SQLiteHelper.STATUS));

        Runnable delayedTask = new Runnable() {
            @Override
            public void run() {
                TextView status = (TextView) getActivity().findViewById(R.id.status);
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                sqLiteHelper.updatePetData(SQLiteHelper.STATUS, "Hungry",1);
                status.setText(sqLiteHelper.getPetData(SQLiteHelper.STATUS));
            }
        };
        view.postDelayed(delayedTask, 5*60*60*1000);

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