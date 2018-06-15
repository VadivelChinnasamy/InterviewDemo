package com.datastructure.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.datastructure.R;

public class SuccessActivity extends BaseActivity {

    @Override
    public int Layout() {
        return R.layout.activity_success;
    }

    @Override
    public void Initialize() {
        ((TextView)findViewById(R.id.mTitleTxt)).setText(R.string.success);


    }

    public void onHome(View view){
        startActivity(new Intent(SuccessActivity.this,QuizActivity.class));
        finish();
    }
}
