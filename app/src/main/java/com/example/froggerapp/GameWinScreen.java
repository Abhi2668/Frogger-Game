package com.example.froggerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameWinScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);
        Bundle newB = getIntent().getExtras();
        Bundle b = getIntent().getExtras();
        String pName = b.getString("pName");
        String difficulty = b.getString("diff");
        String image = b.getString("sprite");
        int highScore = b.getInt("highScore") + 4000;

        String message = "CONGRATULATIONS!! You won, " + pName + ". Your final score was "
                + highScore + " at game difficulty " + difficulty + ".";

        TextView endGameMessage = findViewById(R.id.endGameMessageTxt);
        endGameMessage.setText(message);

        Button c = findViewById(R.id.button4);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameWinScreen.this, InitialConfigurationScreen.class);
                intent.putExtra("pName", pName);
                intent.putExtra("diff", difficulty);
                intent.putExtra("sprite", image);
                startActivity(intent);
            }
        });

        Button d = findViewById(R.id.button5);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });



    }
}