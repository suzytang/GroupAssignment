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

    private LearnViewModel learnViewModel;
    ArrayList<String> myArray;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        learnViewModel =
                ViewModelProviders.of(this).get(LearnViewModel.class);
        View root = inflater.inflate(R.layout.fragment_learn, container, false);
        /*final TextView textView = root.findViewById(R.id.text_learn);
        learnViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        myArray = new ArrayList<String>();

        int i = 1;
        for (int x = 1; x < 10; x++) {
            myArray.add(String.valueOf(i));
            i++;
        }

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);



        LearnRecyclerAdapter.RecyclerViewClickListener listener = new LearnRecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                clickResponse(position);
            }
        };


        adapter = new LearnRecyclerAdapter(myArray, listener);

        //Attach the adapter to the recyclerView
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void clickResponse (int position){
        Intent intent = new Intent(getActivity(), LearnQuiz.class);
        intent.putExtra("level", myArray.get(position));
        startActivity(intent);

    }

}
