package com.datastructure.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.datastructure.DataClass.DataStruClass;
import com.datastructure.R;

import java.util.ArrayList;

public class SwipeDeckAdapter extends BaseAdapter {

    private ArrayList<DataStruClass> data;
    private Context context;
    private RadioGroup radioGroup;
    private RadioButton option3;
    private RadioButton radioSexButton;

    public SwipeDeckAdapter(ArrayList<DataStruClass> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // normally use a viewholder
            v = inflater.inflate(R.layout.content, parent, false);
        }
        ((TextView) v.findViewById(R.id.textView)).setText(data.get(position).getQuestion());
        ((RadioButton) v.findViewById(R.id.option3)).setText(data.get(position).getAnswer());
        radioGroup = v.findViewById(R.id.radioGroup);

        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    //Toast.makeText(context, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });



        return v;
    }

}