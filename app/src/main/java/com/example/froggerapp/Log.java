package com.example.froggerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.froggerapp.characters.Sprite;
import com.example.froggerapp.characters.SpriteSheet;

import java.util.Random;

public class Log {
    //Units of pixels
    private double positionX;
    //units of pixels
    private double positionY;
    private SpriteSheet spriteSheet;
    private Bitmap logBitmap;
    private Sprite sprite;
    //pixels / ms
    private double velocityX;
    //pixels / ms

    private double velocityY;
    //Previous timestamp
    private double timeStamp;
    private int logType;
    private int velocitySign;

    private double moveDistance;

    private double prevX;

    public Log(Context context, double positionY, SpriteSheet spriteSheet, int logType) {
        this.positionX = 0;
        this.positionY = positionY;
        this.logType = logType;
        Random random = new Random();
        velocitySign = (random.nextBoolean()) ? (1) : (-1);

        this.prevX = 0;
        this.moveDistance = 0;

        switch (logType) {
        case 0:
            velocityX = (Math.random() * .1 + .1) * velocitySign;
            sprite = spriteSheet.getLogSprite();
            break;
        case 1:
            velocityX = (Math.random() * .1 + .1) * velocitySign;
            sprite = spriteSheet.getLogSprite();
            break;
        case 2:
            velocityX = (Math.random() * 0.1 + .1) * velocitySign;
            sprite = spriteSheet.getLogSprite();
            break;
        default:
            break;
        }
        this.velocityY = 0;

        this.spriteSheet = spriteSheet;
        timeStamp = System.currentTimeMillis();


    }

    public void draw(Canvas canvas) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        logBitmap = Bitmap.createBitmap(64, 64, config);
        sprite.draw(canvas, (int) positionX, (int) positionY);
    }

    public void changeCarType() {
        switch ((int) (Math.random() * 4)) {
        case 0:
            velocityX = Math.random() * .4 + 0.1;
            sprite = spriteSheet.getPacManSprite();
            break;
        case 1:
            velocityX = Math.random() * .3 + 0.5;
            sprite = spriteSheet.getMsPacManSprite();
            break;
        case 2:
            velocityX = Math.random() * .2 + 0.1;
            sprite = spriteSheet.getPacManSprite();
            break;
        case 3:
            velocityX = Math.random() * .5 + 0.7;
            sprite = spriteSheet.getFastManSprite();
            break;
        default:
            break;
        }
    }
    public void update() {
        if (velocitySign > 0) {
            positionX = (positionX + velocityX * (System.currentTimeMillis() - timeStamp)) % 1064;
        } else {
            if (positionX < 0) {
                positionX = 1064;
            } else {
                positionX = (positionX + velocityX * (System.currentTimeMillis() - timeStamp));
            }

        }

        timeStamp = System.currentTimeMillis();
        moveDistance = positionX - prevX;
        prevX = positionX;

    }

    public double getVelocityX() {
        return velocityX;
    }

    public int getCarType() {
        return logType;
    }

    public double getPositionY() {
        return positionY;
    }
    public double getPositionX() {
        return positionX;
    }

    public double getMoveDistance() {
        return moveDistance;
    }

}
