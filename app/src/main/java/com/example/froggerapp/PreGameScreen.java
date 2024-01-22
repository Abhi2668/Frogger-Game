package com.example.froggerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class PreGameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game_screen);
        //RETRIEVE DATA FROM INITIAL CONFIG SCREEN
        Bundle b = getIntent().getExtras();
        String pName = b.getString("pName");
        String difficulty = b.getString("diff");
        String image = b.getString("sprite");

        //DISPLAY DATA
        TextView playerNameDisplay = findViewById(R.id.personNameTxt);
        playerNameDisplay.setText("player: " + pName);

        ImageView im = findViewById(R.id.characterImage);
        if (image.equals("0")) {
            im.setImageResource(R.drawable.blinky);
        } else if (image.equals("1")) {
            im.setImageResource(R.drawable.clyde);
        } else {
            im.setImageResource(R.drawable.inky);
        }

        TextView numLives = findViewById(R.id.livesTxt);
        TextView diff = findViewById(R.id.difficultyTxt);
        if (difficulty.equals("easy")) {
            diff.setText("Difficulty: easy");
            numLives.setText("Lives: 3");
        } else if (difficulty.equals("medium")) {
            diff.setText("Difficulty: medium");
            numLives.setText("Lives: 2");
        } else {
            diff.setText("Difficulty: hard");
            numLives.setText("Lives: 1");
        }

        Button c = findViewById(R.id.button4);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreGameScreen.this, GameStarter.class);
                intent.putExtra("pName", pName);
                intent.putExtra("diff", difficulty);
                intent.putExtra("sprite", image);
                startActivity(intent);
            }
        });
    }
}