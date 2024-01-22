package com.example.froggerapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import android.content.Context;

import com.example.froggerapp.characters.Sprite;
import com.example.froggerapp.characters.SpriteSheet;
import com.example.froggerapp.map.Tilemap;

public class Sprint5UnitTests {

    @Mock
    private Context context;

    @Mock
    SpriteSheet spriteSheet;
    @Before
    public void setUpSomething() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTest() {
        assertEquals(0,0);
    }

    @Test
    public void logType0v1DiffSpeeds() {
        Log log1;
        Log log2;
        int[] iValues = {0};
        int[] jValues = {1};
        for (int i : iValues) {
            log1 = new Log(context, 448, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    log2= new Log(context, 448, spriteSheet, j);
                    assertNotEquals(log1.getVelocityX(), log2.getVelocityX());
                }
            }
        }
    }

    @Test
    public void logType0v2DiffSpeeds() {
        Log log1;
        Log log2;
        int[] iValues = {0};
        int[] jValues = {2};
        for (int i : iValues) {
            log1 = new Log(context, 448, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    log2= new Log(context, 448, spriteSheet, j);
                    assertNotEquals(log1.getVelocityX(), log2.getVelocityX());
                }
            }
        }
    }

    @Test
    public void logType1v2DiffSpeeds() {
        Log log1;
        Log log2;
        int[] iValues = {1};
        int[] jValues = {2};
        for (int i : iValues) {
            log1 = new Log(context, 448, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    log2= new Log(context, 448, spriteSheet, j);
                    assertNotEquals(log1.getVelocityX(), log2.getVelocityX());
                }
            }
        }
    }

    @Test
    public void playerIsOnLog() throws InterruptedException {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");

        Thread.sleep(3000);
        testPlayer.forward();
        testPlayer.forward();

        assertNotEquals(512, testPlayer.getPosition()[0]);
    }

    @Test
    public void logDifferentDirection() {
        Log log1;
        Log log2;
        int[] iValues = {0,1,2};
        int[] jValues = {2,0,1};
        boolean diffDirection = false;
        for (int i : iValues) {
            log1 = new Log(context, 448, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    log2 = new Log(context, 512, spriteSheet, j);
                    if (log1.getVelocityX() < 0 && log2.getVelocityX() > 0){
                        diffDirection = true;
                        break;
                    }
                    assertNotEquals(log1.getPositionY(), log2.getPositionY());
                }
            }
        }
    }

    @Test
    public void LogSpeedNotZero() {
        Log log1;
        int[] iValues = {0, 1, 2};
        for (int i : iValues) {
            log1 = new Log(context, 448, spriteSheet, i);
            assertNotEquals(log1.getVelocityX(), 0);
        }
    }

    @Test
    public void logEqualsRiver() {
        double[] riverPositions = {448, 512, 576, 640};
        SpawnManager spawner = new SpawnManager(context, riverPositions, spriteSheet, "log");
        Log[] logs = spawner.getLogs();
        for (int i = 0; i < logs.length; i++) {
            assertEquals(logs[i].getPositionY(), riverPositions[i], 0.1);
        }
    }

    @Test
    public void CarType0DiffThanLog() {
        Car car1;
        Log log;
        int[] iValues = {0};
        int[] jValues = {((int) (Math.random() * 2))};
        for (int i : iValues) {
            car1 = new Car(context, 0, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    log= new Log(context, 448, spriteSheet, j);
                    assertNotEquals(car1.getVelocityX(), log.getVelocityX());
                }
            }
        }
    }

    @Test
    public void CarType1DiffThanLog() {
        Car car2;
        Log log;
        int[] iValues = {1};
        int[] jValues = {((int) (Math.random() * 2))};
        for (int i : iValues) {
            car2 = new Car(context, 0, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    log= new Log(context, 448, spriteSheet, j);
                    assertNotEquals(car2.getVelocityX(), log.getVelocityX());
                }
            }
        }
    }

    @Test
    public void CarType2DiffThanLog() {
        Car car2;
        Log log;
        int[] iValues = {2};
        int[] jValues = {((int) (Math.random() * 2))};
        for (int i : iValues) {
            car2 = new Car(context, 0, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    log= new Log(context, 448, spriteSheet, j);
                    assertNotEquals(car2.getVelocityX(), log.getVelocityX());
                }
            }
        }
    }

    @Test
    public void CarType3DiffThanLog() {
        Car car2;
        Log log;
        int[] iValues = {3};
        int[] jValues = {((int) (Math.random() * 2))};
        for (int i : iValues) {
            car2 = new Car(context, 0, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    log= new Log(context, 448, spriteSheet, j);
                    assertNotEquals(car2.getVelocityX(), log.getVelocityX());
                }
            }
        }
    }

    @Test
    public void LogCarCollision() {
        double[] roadPositions = {768, 832, 896, 960};
        double[] riverPositions = {448, 512, 576, 640};
        SpawnManager carSpawner = new SpawnManager(context, roadPositions, spriteSheet, "car");
        SpawnManager logSpawner = new SpawnManager(context, riverPositions, spriteSheet, "log");
        Log[] logs = logSpawner.getLogs();
        Car[] cars = carSpawner.getCars();
        for (int i = 0; i < logs.length; i++) {
            assertNotEquals(logs[i].getPositionY(), cars[i].getPositionY());
        }
    }
}
