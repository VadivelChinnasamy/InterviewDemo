package com.datastructure.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.datastructure.Adapter.HomeAdapter;
import com.datastructure.R;
import com.datastructure.activity.BaseActivity;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    @Override
    public int Layout() {
        return R.layout.activity_home;
    }

    @Override
    public void Initialize() {

        ((TextView)findViewById(R.id.mTitleTxt)).setText(R.string.home);
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        // set a GridLayoutManager with 2 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);
        HomeAdapter    mAdapter = new HomeAdapter(HomeActivity.this);
        recyclerView.setAdapter(mAdapter);

    }

}
