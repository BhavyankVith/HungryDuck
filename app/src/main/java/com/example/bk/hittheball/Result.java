package com.example.bk.hittheball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.varunest.sparkbutton.SparkButton;
import com.yasic.library.particletextview.MovingStrategy.RandomMovingStrategy;
import com.yasic.library.particletextview.Object.ParticleTextViewConfig;
import com.yasic.library.particletextview.View.ParticleTextView;

import org.w3c.dom.Text;

public class Result extends AppCompatActivity {

    Typeface myfont;
    SparkButton restart;
    //private SoundPlayer sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView scoreLabel=(TextView)findViewById(R.id.scoreLabel);
        TextView highScoreLabel=(TextView)findViewById(R.id.highScoreLabel);
        ParticleTextView overhead=(ParticleTextView)findViewById(R.id.overLabel);
        RandomMovingStrategy randomMovingStrategy = new RandomMovingStrategy();
        ParticleTextViewConfig p1=new ParticleTextViewConfig.Builder()
                .setTargetText("GAME OVER")
                .setReleasing(0.4)
                .setParticleRadius(4)
                .setMovingStrategy(randomMovingStrategy)
                .setMiniDistance(1)
                .setTextSize(170)
                .setRowStep(9)
                .setColumnStep(9)
                .instance();
        overhead.setConfig(p1);
        overhead.startAnimation();


       // TextView overhead = (TextView)findViewById(R.id.overLabel);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/KaushanScript-Regular.ttf");
        scoreLabel.setTypeface(myfont);
        highScoreLabel.setTypeface(myfont);

        restart = (SparkButton)findViewById(R.id.tryAgain);

        int score=getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText(score+"");

        SharedPreferences settings=getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore=settings.getInt("HIGH_SCORE",0);

        if(score>highScore){
            highScoreLabel.setText("HIGH SCORE : "+score );

            SharedPreferences.Editor editor=settings.edit();
            editor.putInt(" HIGH_SCORE ",score);
            editor.commit();
        }else
        {
            highScoreLabel.setText("HIGH SCORE : "+highScore);
        }

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }




    //    public void tryAgain(View view)
//    {
//        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//    }
public void quitGame(View v)
{

    NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(this);

    dialogBuilder
            .withTitle("Exit Game")                                  //.withTitle(null)  no title
            .withTitleColor("#FFFFFF")                                  //def
            .withDividerColor("#11000000")                              //def
            .withMessage("Are you sure want to quit the game ?")                     //.withMessage(null)  no Msg
            .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
            .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)
            .withDuration(700)                                          //def
            .withEffect(Effectstype.RotateBottom)                                         //def Effectstype.Slidetop
            .withButton1Text("Yes")                                      //def gone
            .withButton2Text("No")
            .setCustomView(R.layout.custom_view,v.getContext())//def gone
            .isCancelableOnTouchOutside(true)//def    | isCancelable(true)
            .setButton1Click(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                    //finish();

                    finishAffinity();
                    System.exit(0);
                }
            })
            .setButton2Click(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //sound.playQuitSound();
                    Intent i = new Intent(Result.this,MainActivity.class);
                    startActivity(i);
                    //Toast.makeText(v.getContext(),"i'm btn2",Toast.LENGTH_SHORT).show();
                }
            })
            .show();


}


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
