package com.example.groupassignment.ui.pet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.groupassignment.R;

public class PetFragment extends Fragment implements View.OnClickListener{

    private PetViewModel petViewModel;
    private ImageView inventoryButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        petViewModel =
                ViewModelProviders.of(this).get(PetViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pet, container, false);

        ImageView inventoryButton = (ImageView) root.findViewById(R.id.inventoryButton);
        inventoryButton.setOnClickListener(this);

        return root;



    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(getContext(), PetInventory.class);
        startActivity(intent);
    }
}