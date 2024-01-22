package com.example.froggerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.froggerapp.characters.Sprite;
import com.example.froggerapp.characters.SpriteSheet;

import java.util.Random;

public class Car {
    //Units of pixels
    private double positionX;
    //units of pixels
    private double positionY;
    private SpriteSheet spriteSheet;
    private Bitmap carBitmap;
    private Sprite sprite;
    //pixels / ms
    private double velocityX;
    //pixels / ms

    private double velocityY;
    //Previous timestamp
    private double timeStamp;
    private int carType;
    private int velocitySign;

    public Car(Context context, double positionY, SpriteSheet spriteSheet, int carType) {
        this.positionX = 0;
        this.positionY = positionY;
        this.carType = carType;
        Random random = new Random();
        velocitySign = (random.nextBoolean()) ? (1) : (-1);
        if (carType == 3) {
            velocitySign = 1;
        }
        switch (carType) {
        case 0:
            velocityX = (Math.random() * .3 + 0.2) * velocitySign;
            sprite = spriteSheet.getGhostSprite();
            break;
        case 1:
            velocityX = (Math.random() * .3 + 0.5) * velocitySign;
            sprite = spriteSheet.getMsPacManSprite();
            break;
        case 2:
            velocityX = (Math.random() * .6 + 0.2) * velocitySign;
            sprite = spriteSheet.getPacManSprite();
            break;
        case 3:
            velocityX = (Math.random() * .5 + 0.7) * velocitySign;
            sprite = spriteSheet.getFastManSprite();
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
        carBitmap = Bitmap.createBitmap(64, 64, config);
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
        if (carType == 3) {
            velocityX = Math.sin(System.currentTimeMillis()) / 3 + 0.35;
        }



        /*
        if (positionX >= 1050) {
            this.changeCarType();
        }

        */

        //positionY = (positionY + velocityY*(System.currentTimeMillis() - timeStamp)) % 1728;
        timeStamp = System.currentTimeMillis();

        /*if (positionX > 1024) {
            velocityX *= -1;
            positionX = 960;
        } else if (positionX < 0) {
            positionX = 0;
            velocityX *= -1;
        }

        if (positionY > 1984) {
            velocityY *= -1;
            positionY = 1984;
        } else if (positionY < 256) {
            positionY = 256;
            velocityY *= -1;
        }*/
    }

    public double getVelocityX() {
        return velocityX;
    }

    public int getCarType() {
        return carType;
    }

    public double getPositionY() {
        return positionY;
    }
    public double getPositionX() {
        return positionX;
    }

}
