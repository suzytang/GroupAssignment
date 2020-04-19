package com.example.groupassignment.ui.pet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.MainActivity;
import com.example.groupassignment.R;
import com.example.groupassignment.SQLiteHelper;

public class AccessoriesFragment extends Fragment {

    public RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private InvAccessoriesAdapter adapter;
    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inventory_list, container, false);

        final SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
        db = sqLiteHelper.getWritableDatabase();

        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*try{
                    switch(name){
                        case "Glasses":
                            sqLiteHelper.applyGlasses("'Sunglasses'", "'"+name+"'");
                            System.out.println("glasses switch");
                            break;
                        case "Sunglasses":
                            sqLiteHelper.applyGlasses("'Glasses'", "'"+name+"'");
                            System.out.println("sunglasses switch");
                            break;
                        case "Wig":
                            sqLiteHelper.applyGlasses("'Wig'", "'"+name+"'");
                            System.out.println("wig switch");
                            break;
                        case "Top Hat":
                            sqLiteHelper.applyHats("'Pirate Hat'", "'Cap'", "'"+name+"'");
                            System.out.println("tophat switch");
                            break;
                        case "Pirate Hat":
                            sqLiteHelper.applyHats("'Cap'", "'Top Hat'", "'"+name+"'");
                            System.out.println("pirate switch");
                            break;
                        case "Cap":
                            sqLiteHelper.applyHats("'Pirate Hat'", "'Top Hat'", "'"+name+"'");
                            System.out.println("cap switch");
                            break;
                    }
                } catch (Exception e){
                    System.out.println("not working");
                }*/


                //can apply more than one accessory at a time
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InvAccessoriesAdapter(getActivity(), getAllItems());

        recyclerView.setAdapter(adapter);

        adapter.swapCursor(getAllItems());

        return root;
    }

    public Cursor getAllItems() {
        return db.query(
                SQLiteHelper.TABLE_NAME,
                new String[] {SQLiteHelper.COL_2},
                SQLiteHelper.COL_3 + " = 'Accessories' AND " + SQLiteHelper.COL_4 + " = 1",
                null,
                null,
                null,
                null
        );
    }
}

