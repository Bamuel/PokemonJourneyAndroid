package com.bamuel.pokemonjourney;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    MyDBHandler dbHandler;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //it hides the title bar thing.

        String username = getIntent().getStringExtra("username");
        TextView textusername = (TextView) findViewById(R.id.txtviewUsername);
        textusername.setText(username);
        dbHandler = new MyDBHandler(this, null, null, 0);
        String dBtoStringGENDER = dbHandler.DBtoStringGender();
        ImageView potagonist = (ImageView) findViewById(R.id.Potagonist);
        if (Objects.equals(dBtoStringGENDER, "Girl")){
            potagonist.setImageResource(R.drawable.a5);
        }

        //TODO POKEDEX
        btnMove();

        Toast.makeText(this, "This uses images from Game Freak Inc. (Pokemon) and 3rd party sources. This game is only for educational and entertainment purposes",Toast.LENGTH_LONG).show();

    }

    private int counter = 0;
    private double meetPokemon = Math.floor(Math.random() * 11) + 20;
    //private double meetPokemon = Math.floor(Math.random() * 1) + 2;

    private void btnMove() {
        dbHandler = new MyDBHandler(this, null, null, 0);
        final String dBtoStringUSERNAME = dbHandler.DBtoStringUSERNAME();
        final String dBtoStringGENDER = dbHandler.DBtoStringGender();
        final String dbtoStringSteps = dbHandler.DBtoStringSteps();
        final Button btnMove = (Button) findViewById(R.id.btnClick);
        final Button step = (Button) findViewById(R.id.btnStep);
        counter = Integer.parseInt(dbtoStringSteps);
        step.setText("STEPS : " + counter);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                counter++;
                step.setText("STEPS : " + counter);
                moveSprite();
                movebg();
                //auto save on each click
                SaveFile saveFile = new SaveFile(dBtoStringUSERNAME,dBtoStringGENDER,counter);
                dbHandler.addUSER(saveFile);
                if (btnMove.getText().toString() == "A Pokemon has appeared"){
                    Intent intent = new Intent(getApplicationContext(), Battle.class);
                    startActivity(intent);
                    btnMove.setText("Click to Move");
                }
                if(counter % meetPokemon == 0) {
                    meetPokemon += Math.floor(Math.random() * 11 + 20);
                    btnMove.setText("A Pokemon has appeared");
                }
            }
        });
    }
    private int swap = 0;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void moveSprite(){
        dbHandler = new MyDBHandler(this, null, null, 0);
        String dBtoStringGENDER = dbHandler.DBtoStringGender();
        ImageView potagonist = (ImageView) findViewById(R.id.Potagonist);
        if (Objects.equals(dBtoStringGENDER, "Girl")){
            if (swap == 0){
                potagonist.setImageResource(R.drawable.a5);
                swap = 1;
            }
            else if (swap ==1){
                potagonist.setImageResource(R.drawable.a6);
                swap = 2;
            }
            else if (swap ==2){
                potagonist.setImageResource(R.drawable.a7);
                swap = 3;
            }
            else if (swap ==3){
                potagonist.setImageResource(R.drawable.a8);
                swap = 0;
            }
        }
        else {
            if (swap == 0){
                potagonist.setImageResource(R.drawable.a1);
                swap = 1;
            }
            else if (swap ==1){
                potagonist.setImageResource(R.drawable.a2);
                swap = 2;
            }
            else if (swap ==2){
                potagonist.setImageResource(R.drawable.a3);
                swap = 3;
            }
            else if (swap ==3){
                potagonist.setImageResource(R.drawable.a4);
                swap = 0;
            }
        }
    }

    private int test = 1;

    private void movebg() {
        int move = -15;
        final ImageView backgroundOne = (ImageView) findViewById(R.id.imageBG);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.imageBG2);
        if (test == 1){
            backgroundOne.setX(0);
            backgroundTwo.setX(backgroundOne.getWidth());
            test = 0;
        }

        final float width = backgroundOne.getWidth();

        if(backgroundOne.getX() < -width) {
            backgroundOne.setX(backgroundOne.getWidth());

        } else if(backgroundTwo.getX() < -width) {
            backgroundTwo.setX(backgroundTwo.getWidth());

        }

        backgroundOne.setTranslationX(backgroundOne.getX() + move);
        backgroundTwo.setTranslationX(backgroundTwo.getX() + move);

    }
}
