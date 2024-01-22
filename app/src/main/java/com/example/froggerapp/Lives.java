package com.example.froggerapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Lives {
    private Context context;
    private Player player;

    public Lives(Context context, Player player) {
        this.context = context;
        this.player = player;
    }

    public void draw(Canvas canvas) {
        String lifeString = "Lives: " + player.getLives();
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText(lifeString, 900, 200, paint);
    }
}
