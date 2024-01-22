package com.example.froggerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;


import androidx.lifecycle.LiveData;

import com.example.froggerapp.characters.Sprite;
import com.example.froggerapp.characters.SpriteSheet;
import com.example.froggerapp.functionalUI.MovementButtons;



public class Player extends LiveData<Integer> {
    private double positionX;
    private double positionY;
    private SpriteSheet spriteSheet;
    private Bitmap playerBitmap;
    private Sprite sprite;
    private MovementButtons movementButtons;
    private int lives;

    public Player(Context context, double positionX, double positionY, SpriteSheet spriteSheet,
                  String appearance, String difficulty) {
        this.positionX = positionX;
        this.positionY = positionY;

        this.spriteSheet = spriteSheet;
        sprite = spriteSheet.getPlayerSprite(appearance);

        switch (difficulty) {
        case "easy":
            lives = 3;
            break;
        case "medium":
            lives = 2;
            break;
        case "hard":
            lives = 1;
            break;
        default:
            break;
        }

    }

    protected void onActive() {
        super.onActive();
        setValue(lives);
    }
    public void draw(Canvas canvas) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        playerBitmap = Bitmap.createBitmap(64, 64, config);
        sprite.draw(canvas, (int) positionX, (int) positionY);
    }

    public void update() {

    }

    public void forward() {
        if (!(this.positionY - 64 < 256)) {
            this.positionY -= 64;
        }
    }

    public void backward() {
        if (!(this.positionY + 64 > 1984)) {
            this.positionY += 64;
        }
    }

    public void left() {
        if (!(this.positionX - 64 < 0)) {
            this.positionX -= 64;
        }
    }

    public void right() {
        if (!(this.positionX + 64 > 1024)) {
            this.positionX += 64;
        }
    }


    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public double[] getPosition() {
        double[] positions = {
            positionX,
            positionY
        };
        return positions;
    }

    public int getLives() {
        return lives;
    }
    public void loseLife() {
        lives -= 1;
        postValue(lives);
        positionX = 512;
        positionY = 1984;
    }

    public void winGame() {
        lives = 4;
        postValue(lives);
    }

}
