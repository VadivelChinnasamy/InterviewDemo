package com.datastructure.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datastructure.R;
import com.datastructure.activity.QuizActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private  String[] title = new String[]{
            "Java","Data Structure","C","C++","Android","iOS","PHP"
    };
    int[] img = new int[]{
            R.mipmap.java, R.mipmap.ds, R.mipmap.c, R.mipmap.cc,R.mipmap.android_logo,
            R.mipmap.ios, R.mipmap.php
    };

    private Context _mContext;


    public HomeAdapter(Context _mContext) {
        this._mContext=_mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_home, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        viewHolder.txt_title.setText(""+title[i]);
        viewHolder.img.setImageResource(img[i]);
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mContext.startActivity(new Intent(_mContext, QuizActivity.class));

            }
        });

    }


    @Override
    public int getItemCount() {
        return img.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txt_title;

        @BindView(R.id.img)
        ImageView img;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}