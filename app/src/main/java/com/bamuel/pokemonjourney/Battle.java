package com.bamuel.pokemonjourney;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Battle extends AppCompatActivity {

    private static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //it hides the title bar thing.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        ImageView opp = (ImageView) findViewById(R.id.oppoentpokemon);
        opp.setImageResource(getImageId(this, "pkmn" + PokemonRareity()));

        Button run = (Button) findViewById(R.id.run);
        Button fight = (Button) findViewById(R.id.fight);
        Button pokeball = (Button) findViewById(R.id.pokeball);
        Button bait = (Button) findViewById(R.id.bait);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pokeball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //TODO interactive battles
    }

    private int PokemonRareity() {
        Pokemon pokemon = new Pokemon();
        double randomnumber = Math.random();
        int[] StrongPokemon = {3, 6, 9, 12, 15, 18, 20, 22, 24, 26, 28, 31, 34, 36, 38, 40, 42, 45, 47, 49, 51, 53, 55, 57, 59, 62, 65, 68, 71, 73, 76, 78, 80, 82, 83, 85, 87, 89, 91, 94, 95, 97, 99, 101, 103, 105, 106, 107, 108, 110, 112, 113, 114, 115, 117, 119, 121, 122, 123, 124, 125, 126, 127, 128, 130, 131, 132, 134, 135, 136, 137, 139, 140, 141};
        int[] LegendaryPokemon = {143, 144, 145, 146, 149, 150, 151};
        int[] Pokemon = {1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 19, 21, 23, 25, 27, 29, 30, 32, 33, 35, 37, 39, 41, 43, 44, 46, 48, 50, 52, 54, 56, 58, 60, 61, 63, 64, 66, 67, 69, 70, 72, 74, 75, 77, 79, 81, 84, 86, 88, 90, 92, 93, 96, 98, 100, 102, 104, 109, 111, 116, 118, 120, 129, 133, 138, 142};
        if (randomnumber < 0.1) {
            int rand = (int) (Math.random() * LegendaryPokemon.length);
            System.out.println("LEGEND = " + LegendaryPokemon[rand]);
            System.out.println(pokemon.IDtoName(Pokemon[rand]));
            return LegendaryPokemon[rand];
        } else if (randomnumber < 0.2) {
            int rand = (int) (Math.random() * StrongPokemon.length);
            System.out.println("STRONG = " + StrongPokemon[rand]);
            System.out.println(pokemon.IDtoName(Pokemon[rand]));
            return StrongPokemon[rand];
        } else {
            int rand = (int) (Math.random() * Pokemon.length);
            System.out.println("COMMON = " + Pokemon[rand]);
            System.out.println(pokemon.IDtoName(Pokemon[rand]));
            return Pokemon[rand];
        }
    }
}
