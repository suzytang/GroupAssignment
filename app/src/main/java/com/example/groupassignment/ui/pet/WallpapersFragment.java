package com.example.groupassignment.ui.pet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;
import com.example.groupassignment.ui.learn.LearnCategories;
import com.example.groupassignment.ui.learn.LearnRecyclerAdapter;
import com.example.groupassignment.ui.shop.Shop;

import java.util.ArrayList;

public class WallpapersFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inventory_list, container, false);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        db = sqLiteHelper.getWritableDatabase();

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        final Shop shop = new Shop();


        adapter = new InvWallpapersAdapter(getActivity(), getAllItems());

        recyclerView.setAdapter(adapter);

        return root;
    }

    public Cursor getAllItems() {
        return db.query(
                SQLiteHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}

