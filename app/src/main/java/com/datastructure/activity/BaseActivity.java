package com.datastructure.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.datastructure.Adapter.MenuAdapter;
import com.datastructure.DataClass.ListData;
import com.datastructure.R;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.util.Log.e;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;


    private DrawerLayout mDrawerLayout;
    private ListView expandableList;

    Context mContext;
    private FrameLayout frameLayout;
    private Handler mHandler;
    private Intent mIntent = null;
    private TextView mLogout, mVersion;
    @BindView(R.id.mListView)ListView mListView;
    @BindView(R.id.frame_container)FrameLayout frame_container;
    ArrayList<ListData> myList ;
    String[] title = new String[]{
           "Profile","Badges","Challenges","Messages","Credits","Settings","Help"
    };
    int[] img = new int[]{
            R.mipmap.ic_user, R.mipmap.ic_badge, R.mipmap.ic_challenges, R.mipmap.ic_msg,R.mipmap.ic_credit,
            R.mipmap.ic_settings, R.mipmap.ic_support
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        mContext = BaseActivity.this;
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        frameLayout = (FrameLayout) findViewById(R.id.frame_container);
        myList = new ArrayList();
        getDataInList();

        // get the listview

        // preparing list data

        getLayoutInflater().inflate(Layout(), frameLayout);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // insert data into the list before setting the adapter
        // otherwise it will generate NullPointerException  - Obviously
        mListView.setAdapter(new MenuAdapter(mContext, myList));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mDrawerLayout.setDrawerListener(toggle);

mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        displayView(i);
    }
});



        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                } else {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
            }
        });
 /*       findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMenu();
            }
        });*/



        Initialize();


    }


    public void OpenMenu() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        } else {
            mDrawerLayout.openDrawer(Gravity.START);
        }

    }


    public abstract int Layout();

    public abstract void Initialize();


    /*
    * Load data
     * Create a new object for each list item
     * */
    private void getDataInList() {
        for (int i = 0; i < title.length; i++) {
            ListData ld = new ListData();
            ld.setTitle(title[i]);
            ld.setImgResId(img[i]);
            // Add this object into the ArrayList myList
            myList.add(ld);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Displaying fragment view for selected nav drawer list item
     */
    private void displayView(final int position) {
        // update the main content by replacing fragments
        e("-------->>", "*******Response*******" + position);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (position) {
                    case 0:
                        mIntent = new Intent(BaseActivity.this,
                                ProfileAcitvity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mIntent);

                        break;
                    case 1:
                        mIntent = new Intent(BaseActivity.this,
                                MessagesActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //finish();
                        startActivity(mIntent);

                        break;


                }

                if (position != 1) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                }
            }
        }, 500);

    }
}