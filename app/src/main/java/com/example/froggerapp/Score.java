package com.example.froggerapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Score {
    private int score;
    private double maxY;
    private int[] scoreVals;
    private Context context;
    private Player player;
    private int highScore;
    public Score(Context context, int[] scoreVals, Player player) {
        score = 0;
        highScore = 0;
        this.context = context;
        this.scoreVals = scoreVals;
        maxY = 1984;
        this.player = player;
    }

    public void draw(Canvas canvas) {
        String scoreString = "SCORE " + Integer.toString(score);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText(scoreString, 100, 200, paint);
    }

    public void update() {
        double playerPos = player.getPosition()[1];
        if (playerPos < maxY) {
            maxY = playerPos;
            int tilePosition = (int) ((1984 - maxY) / 64);
            score += scoreVals[scoreVals.length - tilePosition];
            if (score > highScore) {
                highScore = score;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        score = 0;
        maxY = 1984;
    }
    public int getHighScore() {
        return highScore;
    }

}
