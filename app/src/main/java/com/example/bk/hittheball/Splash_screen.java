package com.example.bk.hittheball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splash_screen extends AppCompatActivity {

    LinearLayout layUp,layDown;
    ImageView img1;
    Animation up,down;
    //Button entering;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        layUp=(LinearLayout)findViewById(R.id.upLayout);
        layDown=(LinearLayout)findViewById(R.id.downLayout);
        img1 = (ImageView)findViewById(R.id.splash_logo);
       // entering=(Button)findViewById(R.id.entering);

        up= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.upaniamtion);
        down=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.downanimation);
        layUp.setAnimation(up);
        layDown.setAnimation(down);
//        entering.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               // entering.setBackground(R.drawable.btn_ng);
//                startActivity(new Intent(getApplicationContext(),Start.class));
//            }
//        });
        up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(),Start.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

      }

}
