package com.example.froggerapp;

import android.content.Context;
import android.graphics.Canvas;

import com.example.froggerapp.characters.SpriteSheet;

import java.util.Random;

public class SpawnManager {
    private Car[] cars;
    private Log[] logs;
    private Context context;
    private double[] rPositions;
    private SpriteSheet spriteSheet;


    public SpawnManager(Context context, double[] roadPositions, SpriteSheet spriteSheet,
                        String spawnType) {
        this.context = context;
        this.rPositions = roadPositions;
        this.spriteSheet = spriteSheet;
        switch (spawnType) {
        case "car":
            initCars();
            break;
        case "log":
            initLogs();
            break;
        default:
            break;
        }
    }

    private void initCars() {
        cars = new Car[rPositions.length];
        Random randomizer = new Random();
        for (int i = 0; i < rPositions.length; i++) {
            cars[i] = new Car(context, rPositions[i], spriteSheet, i % 4);
        }
    }

    public void initLogs() {
        logs = new Log[rPositions.length];
        Random randomizer = new Random();
        for (int i = 0; i < rPositions.length; i++) {
            logs[i] = new Log(context, rPositions[i], spriteSheet, i % 3);
        }
    }

    public void draw(Canvas canvas, String spawnType) {
        for (int i = 0; i < rPositions.length; i++) {
            if (spawnType == "car") {
                cars[i].draw(canvas);
            } else {
                logs[i].draw(canvas);
            }
        }
    }

    public void update(String spawnType) {
        for (int i = 0; i < rPositions.length; i++) {
            if (spawnType == "car") {
                cars[i].update();
            } else {
                logs[i].update();
            }
        }
    }

    public Car[] getCars() {

        return cars;
    }

    public Log[] getLogs() {
        return logs;
    }

    public boolean detectCollisions(double playerX, double playerY) {
        for (int i = 0; i < cars.length; i++) {
            Car temp = cars[i];
            if (Math.abs(temp.getPositionY() - playerY) <= 32
                    && Math.abs(temp.getPositionX() - playerX) <= 32) {
                return true;
            }
        }
        return false;
    }

    public boolean logCollision(Player player) {
        for (int i = 0; i < logs.length; i++) {
            Log temp = logs[i];
            if ((temp.getPositionY() == player.getPosition()[1])
                    && (temp.getPositionX() - player.getPosition()[0]) <= 0 && (temp.getPositionX()
                    - player.getPosition()[0]) > -256) {
                movePlayer(player, logs[i]);
                return true;
            }
        }
        return false;
    }

    public void movePlayer(Player player, Log log) {
        player.setPosition(player.getPosition()[0] + log.getMoveDistance(),
                player.getPosition()[1]);
        if (player.getPosition()[0] > 1024 || player.getPosition()[0] < 0) {
            player.loseLife();
        }
    }


}
