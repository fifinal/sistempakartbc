package com.example.fifin.sistem_pakar_tbc;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        ImageView logoSplashScreen=(ImageView)findViewById(R.id.logo_splash_screen);
        TextView tvApp=(TextView) findViewById(R.id.tv_app);
        Animation animation=AnimationUtils.loadAnimation(this,R.anim.fadeout);

        logoSplashScreen.startAnimation(animation);
        tvApp.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                Intent mainIntent = new Intent(Screen.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
              }
            }, 4000);
    }
}
