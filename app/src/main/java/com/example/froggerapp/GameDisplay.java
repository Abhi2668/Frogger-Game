package com.example.froggerapp;

import android.graphics.Rect;

public class GameDisplay {
    private final Rect displayRECT;
    private final int widthPixels;
    private final int heightPixels;

    /*EVENTUALLY ADD IN GAME OBJECT FOR LOGS

    private final GameObject centerObject;

     */
    private final double displayCenterX;
    private final double displayCenterY;
    private double gameToDisplayCoordinatesOffsetX;
    private double gameToDisplayCoordinatesOffsetY;

    public GameDisplay(int widthPixels, int heightPixels) {
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        displayRECT = new Rect(0, 0, widthPixels, heightPixels);


        displayCenterX = widthPixels / 2.0;
        displayCenterY = heightPixels / 2.0;

        update();
    }

    public void update() {
        gameToDisplayCoordinatesOffsetX = displayCenterX;
        gameToDisplayCoordinatesOffsetY = displayCenterY;
    }

    public double gameToDisplayCoordinatesX(double x) {
        return x + gameToDisplayCoordinatesOffsetX;
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinatesOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) (widthPixels / 2),
                (int) (heightPixels / 2),
                (int) (widthPixels / 2),
                (int) (heightPixels / 2)
        );
    }
}
