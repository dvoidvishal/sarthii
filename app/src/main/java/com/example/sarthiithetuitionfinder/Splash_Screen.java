package com.example.sarthiithetuitionfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Screen extends AppCompatActivity {

    Animation fade;

    ImageView logo,title,tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.splash_screen);

      this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       requestWindowFeature(Window.FEATURE_NO_TITLE);


       setContentView(R.layout.splash_screen);

        logo=  findViewById(R.id.logo);
        title= findViewById(R.id.title);
        tagline= findViewById(R.id.tagline);

        fade= AnimationUtils.loadAnimation(this,R.anim.fadein);

        logo.startAnimation(fade);
        title.startAnimation(fade);
        tagline.startAnimation(fade);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Splash_Screen.this, walk_through.class);
                startActivity(i);
                finish();
            }
        },2500);

    }
}
