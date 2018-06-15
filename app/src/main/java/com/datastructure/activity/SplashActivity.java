package com.datastructure.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.datastructure.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.mRoot)
    LinearLayout mRoot_layout;
    @BindView(R.id.img_splash)
    ImageView img_splash;
    private ViewGroup container;
    @BindView(R.id.txt_lco)
    TextView txt_lco;
    @BindView(R.id.banner_img)
    ImageView banner_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img_splash.setVisibility(View.GONE);
            txt_lco.setVisibility(View.GONE);
            if (savedInstanceState == null) {
                mRoot_layout.setVisibility(View.INVISIBLE);
                ViewTreeObserver viewTreeObserver = mRoot_layout.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            circularRevealActivity();
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                                mRoot_layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            } else {
                                mRoot_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                }
            }
        } else {

            mRoot_layout.setVisibility(View.VISIBLE);

        }
    }

    /***
     *
     * MAKE CIRCULAR REVEAL EFFECT
     * */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void circularRevealActivity() {
        container = (ViewGroup) findViewById(R.id.mRoot);

        final ViewGroupOverlay overlay = container.getOverlay();

        final View revealView = new View(this);
        revealView.setBottom(container.getHeight());
        revealView.setRight(container.getWidth());
        revealView.setBackgroundColor(ContextCompat.getColor(this, R.color.theme_clr));

        overlay.add(revealView);

        mRoot_layout.setVisibility(View.VISIBLE);
        float radius = (float) Math.sqrt(Math.pow(container.getHeight(), 2) + Math.pow(container.getWidth(), 2));
        Animator revealAnimator =
                ViewAnimationUtils.createCircularReveal(revealView,
                        revealView.getWidth(), revealView.getHeight(), 0.0f, radius);

        revealAnimator.setDuration(1300);

        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mRoot_layout.setVisibility(View.VISIBLE);
                img_splash.setVisibility(View.VISIBLE);
                txt_lco.setVisibility(View.VISIBLE);
                banner_img.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRedirect();
                    }
                }, 2000);


            }
        });

        Animator alphaAnimator = ObjectAnimator.ofFloat(revealView, View.ALPHA, 0.0f);
        alphaAnimator.setDuration(
                getResources().getInteger(android.R.integer.config_mediumAnimTime));

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(revealAnimator).before(alphaAnimator);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                overlay.remove(revealView);
            }
        });

        animatorSet.start();

    }

    private void mRedirect() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();

    }

}
