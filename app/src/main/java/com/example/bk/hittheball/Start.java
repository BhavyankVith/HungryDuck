package com.example.bk.hittheball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.varunest.sparkbutton.SparkButton;

public class Start extends AppCompatActivity {

    SparkButton startBtn;



    //    private SoundPlayer sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startBtn = (SparkButton)findViewById(R.id.startGame);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sound.playBackmusic();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
    @Override
    public void onBackPressed() {
        System.exit(0);
        finishAffinity();
        super.onBackPressed();
    }


//    public void startGame(View view)
//    {
//        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if(event.getAction()==KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    return true;

            }
        }

        return super.dispatchKeyEvent(event);
    }
}
