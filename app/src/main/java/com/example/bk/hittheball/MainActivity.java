package com.example.bk.hittheball;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    Typeface myfont;

    ImageView duckFace;
    ImageView blueFish;
    ImageView redShark;
    ImageView yellowFish;
    ImageView decreFish;
    int duckFaceY;
    int blueFishX;
    int blueFishY;
    int yellowFishX;
    int yellowFishY;
    int redSharkX;
    int redSharkY;
    int decreFishX;
    int decreFishY;

    int frameHeight;
    int duckFaceSize;
    int screenWidth;
    int screenHeight;

    int score = 0;


    Handler handler = new Handler();
    Timer timer = new Timer();
    private SoundPlayer sound;


    boolean action_flg = false;
    boolean start_flg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new SoundPlayer(this);
        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        myfont=Typeface.createFromAsset(this.getAssets(),"fonts/KaushanScript-Regular.ttf");
        scoreLabel.setTypeface(myfont);
        startLabel.setTypeface(myfont);

        duckFace = (ImageView) findViewById(R.id.duckFace);
        blueFish = (ImageView) findViewById(R.id.blueFish);
        redShark = (ImageView) findViewById(R.id.redSharkOver);
        yellowFish = (ImageView) findViewById(R.id.yellowFish);
        decreFish = (ImageView)findViewById(R.id.decrePoint);



        //GET SCREEN SIZE
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;


        blueFish.setX(-80);
        blueFish.setY(-80);
        redShark.setX(-80);
        redShark.setY(-80);
        yellowFish.setX(-80);
        yellowFish.setY(-80);
        decreFish.setX(-80);
        decreFish.setY(-80);


        scoreLabel.setText("Score : 0");
    }

    public void changePos() {

        hitCheck();


        //blueFish
        blueFishX -= 12;
        if (blueFishX < 0) {
            blueFishX = screenWidth + 20;
            blueFishY = (int) Math.floor(Math.random() * (frameHeight - blueFish.getHeight()));
        }
        blueFish.setX(blueFishX);
        blueFish.setY(blueFishY);

    //decrement_fish
        decreFishX -= 14;
        if (decreFishX < 0) {
            decreFishX = screenWidth + 40;
            decreFishY = (int) Math.floor(Math.random() * (frameHeight - decreFish.getHeight()));
        }
        decreFish.setX(decreFishX);
        decreFish.setY(decreFishY);


        //redShark
        redSharkX -= 16;
        if (redSharkX < 0) {
            redSharkX = screenWidth + 10;
            redSharkY = (int) Math.floor(Math.random() * (frameHeight - redShark.getHeight()));
        }
        redShark.setX(redSharkX);
        redShark.setY(redSharkY);

        //yellowFish
        yellowFishX -= 20;
        if (yellowFishX < 0) {
            yellowFishX = screenWidth + 5000;
            yellowFishY = (int) Math.floor(Math.random() * (frameHeight - yellowFish.getHeight()));
        }
        yellowFish.setX(yellowFishX);
        yellowFish.setY(yellowFishY);

        if (action_flg == true) {
            duckFaceY -= 20;
        } else {
            duckFaceY += 20;
        }

        if (duckFaceY < 0)
            duckFaceY = 0;
        if (duckFaceY > frameHeight - duckFaceSize) duckFaceY = frameHeight - duckFaceSize;
        duckFace.setY(duckFaceY);

        scoreLabel.setText("Score : " + score);
    }

    public void hitCheck() {

        //blueFish
        int blueFishCenterX = blueFishX + blueFish.getWidth() / 2;
        int blueFishCenterY = blueFishY + blueFish.getHeight() / 2;

        if (0 <= blueFishCenterX && blueFishCenterX <= duckFaceSize && duckFaceY <= blueFishCenterY && blueFishCenterY <= duckFaceY + duckFaceSize) {
            score += 10;
            blueFishX = -10;
            sound.playHitSound();

        }

        //yellowFish
        int yellowFishCenterX = yellowFishX + yellowFish.getWidth() / 2;
        int yellowFishCenterY = yellowFishY + yellowFish.getHeight() / 2;

        if (0 <= yellowFishCenterX && yellowFishCenterX <= duckFaceSize && duckFaceY <= yellowFishCenterY && yellowFishCenterY <= duckFaceY + duckFaceSize) {
            score += 30;
            yellowFishX = -10;
            sound.playHitSound();

        }

        //decrementFish
        int decreFishCenterX = decreFishX + decreFish.getWidth() / 2;
        int decreFishCenterY = decreFishY + decreFish.getHeight() / 2;

        if (0 <= decreFishCenterX && decreFishCenterX <= duckFaceSize && duckFaceY <= decreFishCenterY && decreFishCenterY <= duckFaceY + duckFaceSize) {
            score -= 20;
            decreFishX = -10;
            sound.playHitSound();

        }


        //redShark
        int redSharkCenterX = redSharkX + redShark.getWidth() / 2;
        int redSharkCenterY = redSharkY + redShark.getHeight() / 2;

        if (0 <= redSharkCenterX && redSharkCenterX <= duckFaceSize && duckFaceY <= redSharkCenterY && redSharkCenterY <= duckFaceY + duckFaceSize) {

            timer.cancel();
            timer = null;

            sound.playOverSound();

            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(start_flg==false){

            start_flg=true;

            FrameLayout frame=(FrameLayout)findViewById(R.id.frame);
            frameHeight=frame.getHeight();

            duckFaceY=(int)duckFace.getY();
            duckFaceSize=duckFace.getHeight();

            startLabel.setVisibility(View.GONE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            },0,20);
        }
        else{
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                action_flg=true;
            }
            else if(event.getAction()== MotionEvent.ACTION_UP)
            {
                action_flg=false;
            }
        }

        return true;
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

