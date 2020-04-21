package com.example.groupassignment.ui.learn;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import java.util.ArrayList;


public class LearnFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate view with learn fragment
        View root = inflater.inflate(R.layout.fragment_learn, container, false);

        // Initialise recyclerView
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Initialise layoutManager for recyclerView
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Get categories from LearnCategories class
        ArrayList<LearnCategories> categories = LearnCategories.getCategories();

        // Create adapter object
        adapter = new LearnRecyclerAdapter(this.getContext(), categories);

        // Attach adapter to recycler
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
