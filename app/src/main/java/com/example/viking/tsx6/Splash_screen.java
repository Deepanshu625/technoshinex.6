package com.example.viking.tsx6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by viking on 31/7/16.
 */
public class Splash_screen extends Activity implements Animation.AnimationListener {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    Animation animation,ani;
    TextView textView;
    int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        textView = (TextView) findViewById(R.id.splash_text);
        ImageView imageView = (ImageView) findViewById(R.id.splash_image);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(Splash_screen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);


        animation= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
        ani= AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);

        // set animation listener
        animation.setAnimationListener(this);
        ani.setAnimationListener(this);
        imageView.startAnimation(animation);
    }
    @Override
    public void onAnimationEnd(Animation anim) {

        // Take any action after completing the animation
        if(flag==1)
        {
            textView.startAnimation(ani);
            flag=0;
        }




    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }


}
