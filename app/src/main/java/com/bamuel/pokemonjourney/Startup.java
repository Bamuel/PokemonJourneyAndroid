package com.bamuel.pokemonjourney;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;


public class Startup extends AppCompatActivity {
    MyDBHandler dbHandler;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //it hides the title bar thing.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        Continue();
        NewGame();
        backgroundimagescroll();
        dbHandler = new MyDBHandler(this, null, null, 0);
        String dBtoStringGENDER = dbHandler.DBtoStringGender();
        ImageView potagonist = (ImageView) findViewById(R.id.Potagonistloadup);
        if (Objects.equals(dBtoStringGENDER, "Girl")) {
            potagonist.setImageResource(R.drawable.a5);
        }
        Toast.makeText(this, "This uses images from Game Freak Inc. (Pokemon) and 3rd party sources. This game is only for educational and entertainment purposes",Toast.LENGTH_LONG).show();
    }

    private void NewGame() {
        Button NewGame = (Button) findViewById(R.id.NewGame);
        NewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), makeacc.class);
                startActivity(intent);
            }
        });
    }

    private void Continue() {
        dbHandler = new MyDBHandler(this, null, null, 0);
        String dBtoStringUSERNAME = dbHandler.DBtoStringUSERNAME();
        String dBtoStringSTEPS = dbHandler.DBtoStringSteps();
        String dBtoStringGENDER = dbHandler.DBtoStringGender();
        Button Continue = (Button) findViewById(R.id.ContinueGame);
        if (dBtoStringUSERNAME != "A FATAL ERROR HAS OCCURRED") {
            Continue.setVisibility(View.VISIBLE);
            Continue.setText("Continue\n\t\tPlayer = " + dBtoStringUSERNAME + "\n\t\tSteps = " + dBtoStringSTEPS + "\n\t\tGENDER = " + dBtoStringGENDER);
            Continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent2);
                }
            });
        } else {
            Continue.setVisibility(View.GONE);
        }
    }

    private void backgroundimagescroll() {
        final ImageView backgroundOne = (ImageView) findViewById(R.id.Backgroundimage);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.Backgroundimage2);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, -1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX + width);
                moveSprite();
            }
        });
        animator.start();
    }


    private int swap = 0;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void moveSprite() {
        dbHandler = new MyDBHandler(this, null, null, 0);
        String dBtoStringGENDER = dbHandler.DBtoStringGender();
        ImageView potagonist = (ImageView) findViewById(R.id.Potagonistloadup);
        int speed = 8;
        if (Objects.equals(dBtoStringGENDER, "Girl")) {
            if (swap == 1 * speed) {
                potagonist.setImageResource(R.drawable.a5);
            } else if (swap == 2 * speed) {
                potagonist.setImageResource(R.drawable.a6);
            } else if (swap == 3 * speed) {
                potagonist.setImageResource(R.drawable.a7);
            } else if (swap == 4 * speed) {
                potagonist.setImageResource(R.drawable.a8);
            }
            swap++;
            if (swap > 4 * speed) {
                swap = 0;
            }
        } else {
            if (swap == 1 * speed) {
                potagonist.setImageResource(R.drawable.a1);
            } else if (swap == 2 * speed) {
                potagonist.setImageResource(R.drawable.a2);
            } else if (swap == 3 * speed) {
                potagonist.setImageResource(R.drawable.a3);
            } else if (swap == 4 * speed) {
                potagonist.setImageResource(R.drawable.a4);
            }
            swap++;
            if (swap > 4 * speed) {
                swap = 0;
            }
        }
    }

    //when the back arrow is press refreshes the activity/Intent
    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
