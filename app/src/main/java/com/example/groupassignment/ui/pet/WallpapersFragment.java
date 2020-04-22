package com.example.groupassignment.ui.pet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.MainActivity;
import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

public class WallpapersFragment extends Fragment {

    public RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private InvWallpapersAdapter adapter;
    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inventory_list, container, false);

        final SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        db = sqLiteHelper.getWritableDatabase();

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InvWallpapersAdapter(getActivity(), getAllItems());

        recyclerView.setAdapter(adapter);

        // getAllItems() parsed into the adapter
        adapter.swapCursor(getAllItems());

        return root;
    }

    // The following code is modified from: Coding in Flow (2017)
    // 'SQLite + RecyclerView - Part 2 - CURSOR AND RECYCLERVIEW ADAPTER - Android Studio Tutorial'
    // https://www.youtube.com/watch?v=_m-Ve-BAYe0&t=
    // getAllItems displays NAME column from inventory_table from SQLiteHelper where CATEGORY = 'Wallpapers' AND AMOUNT = 1
    // translation: getAllItems gets the names of all wallpapers which have been bought so they can be displayed in inventory
    public Cursor getAllItems() {
        return db.query(
                SQLiteHelper.TABLE_NAME,
                new String[] {SQLiteHelper.COL_2},
                SQLiteHelper.COL_3 + " = 'Wallpapers' AND " + SQLiteHelper.COL_4 + " = 1",
                null,
                null,
                null,
                null
        );
    }
}

