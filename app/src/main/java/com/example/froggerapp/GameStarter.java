package com.example.froggerapp;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class GameStarter extends AppCompatActivity {
    private Player player;
    private Game game;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle newB = getIntent().getExtras();
        Bundle b = getIntent().getExtras();
        String pName = b.getString("pName");
        String difficulty = b.getString("diff");
        String image = b.getString("sprite");


        game = new Game(this, newB);
        this.player = game.getPlayer();
        setContentView(game);


        player.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer lives) {
                // Perform operation in response to change in LiveData object
                if (lives == 0) {
                    Intent intent = new Intent(GameStarter.this, GameOverScreen.class);
                    intent.putExtra("pName", pName);
                    intent.putExtra("diff", difficulty);
                    intent.putExtra("sprite", image);
                    intent.putExtra("highScore", game.getHighScore());
                    startActivity(intent);
                } else if (lives == 4) {
                    Intent intent = new Intent(GameStarter.this, GameWinScreen.class);
                    intent.putExtra("pName", pName);
                    intent.putExtra("diff", difficulty);
                    intent.putExtra("sprite", image);
                    intent.putExtra("highScore", game.getHighScore());
                    startActivity(intent);
                }
            }
        });



    }

    protected void onStart() {
        super.onStart();
    }

    public void onDestroy() {
        super.onDestroy();
        game.endGame();
    }

    public void onStop() {
        super.onStop();
        game.endGame();
    }

    public void onPause() {
        super.onPause();
        game.endGame();
    }


}
