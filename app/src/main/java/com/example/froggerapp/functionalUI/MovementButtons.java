package com.example.froggerapp.functionalUI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MovementButtons {

    private int forwardCircleCenterPosX;
    private int forwardCircleCenterPosY;
    private int backwardCircleCenterPosX;
    private int backwardCircleCenterPosY;
    private int leftCircleCenterPosX;
    private int leftCircleCenterPosY;
    private int rightCircleCenterPosX;
    private int rightCircleCenterPosY;
    protected final int radius = 64;

    protected Paint paint;

    public MovementButtons() {

        forwardCircleCenterPosX = 13 * 64;
        forwardCircleCenterPosY = 24 * 64;

        backwardCircleCenterPosX = 13 * 64;
        backwardCircleCenterPosY = 28 * 64;

        leftCircleCenterPosX = 11 * 64;
        leftCircleCenterPosY = 26 * 64;

        rightCircleCenterPosX = 15 * 64;
        rightCircleCenterPosY = 26 * 64;

        paint = new Paint();
        paint.setColor(Color.parseColor("#66000000"));
        paint.setStyle(Paint.Style.FILL);

    }

    public void draw(Canvas canvas) {
        // Draw forward circle
        canvas.drawCircle(
                forwardCircleCenterPosX,
                forwardCircleCenterPosY,
                radius,
                paint
        );

        // Draw backward circle
        canvas.drawCircle(
                backwardCircleCenterPosX,
                backwardCircleCenterPosY,
                radius,
                paint
        );

        // Draw left circle
        canvas.drawCircle(
                leftCircleCenterPosX,
                leftCircleCenterPosY,
                radius,
                paint
        );

        // Draw right circle
        canvas.drawCircle(
                rightCircleCenterPosX,
                rightCircleCenterPosY,
                radius,
                paint
        );
    }
}
