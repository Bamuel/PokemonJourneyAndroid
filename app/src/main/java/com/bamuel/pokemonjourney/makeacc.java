package com.bamuel.pokemonjourney;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.*;

import java.util.Objects;


public class makeacc extends AppCompatActivity {

    MyDBHandler dbHandler;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide(); //it hides the title bar thing.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeacc);
        backgroundimagescroll();
        submitbutton();
        dbHandler = new MyDBHandler(this, null, null, 0);
        String dBtoStringGENDER = dbHandler.DBtoStringGender();
        ImageView potagonist = (ImageView) findViewById(R.id.Potagonistloadup);
        if (Objects.equals(dBtoStringGENDER, "Girl")){
            potagonist.setImageResource(R.drawable.a5);
        }
    }

    private void submitbutton() {
        Button back = (Button) findViewById(R.id.back);
        dbHandler = new MyDBHandler(this, null,null, 0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.editText_name);
                RadioGroup Gender = (RadioGroup) findViewById(R.id.RadioGroup);
                int radiobuttonid = Gender.getCheckedRadioButtonId();
                RadioButton Sex = (RadioButton) findViewById(radiobuttonid);
                SaveFile saveFile = new SaveFile(username.getText().toString(),Sex.getText().toString(),0);
                dbHandler.addUSER(saveFile);
                Toast toast = Toast.makeText(getApplicationContext(), "User has been registered successfully", Toast.LENGTH_SHORT);
                toast.show();

                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
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
        if (Objects.equals(dBtoStringGENDER, "Girl")){
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
        }
        else {
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
}

