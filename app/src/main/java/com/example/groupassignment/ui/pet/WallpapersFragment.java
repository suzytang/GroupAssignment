package com.example.groupassignment.ui.pet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTableLockedException;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.MainActivity;
import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;
import com.example.groupassignment.ui.learn.LearnCategories;
import com.example.groupassignment.ui.learn.LearnRecyclerAdapter;
import com.example.groupassignment.ui.shop.Shop;

import java.util.ArrayList;

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

        final FragmentCommunication communication = new FragmentCommunication();
        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = communication.getName();
                System.out.println(name);
                //implement method to set the other applied values to 0
                sqLiteHelper.applyInventory("'"+name+"'", "'Wallpapers'");
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InvWallpapersAdapter(getActivity(), getAllItems(), communication);

        recyclerView.setAdapter(adapter);

        adapter.swapCursor(getAllItems());

        return root;
    }

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

