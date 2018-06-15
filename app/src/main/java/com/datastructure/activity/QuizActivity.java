package com.datastructure.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.JsonElement;
import com.datastructure.APIServices.APIService;
import com.datastructure.Adapter.SwipeDeckAdapter;
import com.datastructure.DataClass.DataStruClass;
import com.datastructure.ProgressView.ProgressDialog;
import com.datastructure.R;
import com.datastructure.Utils.Constant;
import com.datastructure.Utils.CustomToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;

import static android.util.Log.e;

public class QuizActivity extends BaseActivity {
    private static final String TAG = "QuizActivity";

    SwipeDeck cardStack;
    ImageView img_no_data;
    private SwipeDeckAdapter adapter;
    private ArrayList<DataStruClass> mDataList;
    private Context mContex;


    @Override
    public int Layout() {
        return R.layout.activity_main;
    }

    @Override
    public void Initialize() {
        ((TextView) findViewById(R.id.mTitleTxt)).setText(R.string.quiz);
        cardStack = findViewById(R.id.swipe_deck);
        img_no_data = findViewById(R.id.no_data);
        mContex = QuizActivity.this;
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        AdView adView = findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);

        if (Constant.isNetworkAvailable(QuizActivity.this)) {
            getDataStructureDetail();
        } else {
            CustomToast.ErrorToast(mContex, getString(R.string.no_internet));
        }


        cardStack.setHardwareAccelerationEnabled(true);
        adapter = new SwipeDeckAdapter(mDataList, mContex);
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback()

        {
            @Override
            public void cardSwipedLeft(int position) {
                CustomToast.ErrorToast(mContex, getString(R.string.skipped));
                Log.i("QuizActivity", "card was swiped left, position in adapter: " + position);
                if (mDataList.size() - 1 == position) {
                    startActivity(new Intent(QuizActivity.this, SuccessActivity.class));
                    finish();
                }
            }

            @Override
            public void cardSwipedRight(int position) {
                CustomToast.ErrorToast(mContex, getString(R.string.skipped));
                Log.i("QuizActivity", "card was swiped right, position in adapter: " + position);

                e("-------->>", "*******Response*******" + mDataList.size() + "=" + position);
                if (mDataList.size() - 1 == position) {
                    startActivity(new Intent(QuizActivity.this, SuccessActivity.class));
                    finish();
                }
            }

            @Override
            public void cardsDepleted() {
                Log.i("QuizActivity", "no more cards");
            }

            @Override
            public void cardActionDown() {
                Log.i(TAG, "cardActionDown");
            }

            @Override
            public void cardActionUp() {
                Log.i(TAG, "cardActionUp");
            }

        });
        adView.setAdListener(new AdListener(){
            @Override
            public void onAdClicked() {
                super.onAdClicked();
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://learncodeonline.in"));
                startActivity(viewIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }



   /***
    * Get Api response - Data structure
    *
    * */ public void getDataStructureDetail() {
        mDataList=new ArrayList<>();
        final ProgressDialog mDialog;

        mDialog = ProgressDialog.show(this, true);
        APIService inf = MyApplication.getRequestQueue(mContex).create(APIService.class);
        final Call<JsonElement> res = inf.getDataStructure();

        res.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    mDialog.dismiss();
                    JSONObject mJsonobj = new JSONObject(response.body().toString());
                    JSONArray mArr = mJsonobj.getJSONArray("questions");
                    if (mArr.length() != 0) {
                        for (int i = 0; i < mArr.length(); i++) {
                            mDataList.add(new DataStruClass(
                                    mArr.getJSONObject(i).getString("question"),
                                     mArr.getJSONObject(i).getString("Answer"),
                                   false)
                                    );
                        }
                        cardStack.setAdapter(adapter);


                    } else {
                        img_no_data.setVisibility(View.VISIBLE);

                        CustomToast.ErrorToast(mContex, getString(R.string.no_result_found));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });


    }
public  void onNext(View V){
    cardStack.swipeTopCardLeft(180);


}

}
