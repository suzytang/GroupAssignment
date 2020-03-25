package com.example.groupassignment.ui.learn;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupassignment.R;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LearnViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public LearnViewModel() {
        /*mText = new MutableLiveData<>();
        mText.setValue("This is learn fragment");*/


    }

    /*public LiveData<String> getText() {
        return mText;
    }*/
}