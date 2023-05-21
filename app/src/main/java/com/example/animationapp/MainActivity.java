package com.example.animationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    ObjectAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        ImageView imgAji = findViewById(R.id.imgAji);
        ImageView imgAjiKO = findViewById(R.id.imgAjiKO);
        Button button = findViewById(R.id.button);

        imgAjiKO.setVisibility(View.GONE);

//        mAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        mAnimator = ObjectAnimator.ofFloat(imageView, "x", 500);

//        mAnimator = ObjectAnimator.ofFloat(imageView, "y", 300);
//        mAnimator.setRepeatCount(10);
        //0f, 360f
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAnimator.setDuration(1000);
                mAnimator.start();
                mAnimator.addListener(new AnimatorListenerAdapter(){
                    @Override
                    public void onAnimationEnd(Animator animation) {

                        imgAji.setVisibility(View.GONE);
                        imgAjiKO.setVisibility(View.VISIBLE);
//                        mAnimator = Object Animator.ofFloat(imgAjiKO, "scaleY", -10);
                        mAnimator = ObjectAnimator.ofFloat(imgAjiKO, "rotation", 0f,360f);
                        mAnimator = ObjectAnimator.ofFloat(imgAjiKO, "x", 1000);
//                        mAnimator = ObjectAnimator.ofFloat(imgAjiKO, "scaleX", -10);
                        mAnimator.start();

                        super.onAnimationEnd(animation);
                    }
                });
            }
        });


    }
}