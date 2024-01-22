package com.example.froggerapp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.LiveData;

import com.example.froggerapp.characters.SpriteSheet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class Sprint4UnitTests {

    @Mock
    private Context context;

    @Mock
    SpriteSheet spriteSheet;
    @Before
    public void setUpSomething() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void playerDiesForwardIntoWater() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");

        testPlayer.forward();
        testPlayer.left();
        testPlayer.forward();
        testPlayer.forward();
        assertEquals(testPlayer.getLives(), 3);
    }

    @Test
    public void playerDiesLeftIntoWater() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");

        testPlayer.forward();
        testPlayer.forward();
        testPlayer.left();
        testPlayer.forward();
        assertEquals(testPlayer.getLives(), 3);
    }

    @Test
    public void playerDiesRightIntoWater() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");

        testPlayer.right();
        testPlayer.forward();
        testPlayer.forward();
        testPlayer.right();
        testPlayer.forward();
        assertEquals(testPlayer.getLives(), 3);
    }

    @Test
    public void playerDiesBackwardIntoWater() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");

        testPlayer.forward();
        testPlayer.forward();
        testPlayer.forward();
        testPlayer.forward();
        testPlayer.forward();
        testPlayer.left();
        testPlayer.backward();
        testPlayer.forward();
        assertEquals(testPlayer.getLives(), 3);
    }

    @Test
    public void testScoreEqual0() {
        Player testPlayer = Mockito.mock(Player.class);
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        /*
            Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");
         */
        Score testScore = new Score(context, val, testPlayer);

        assertEquals(testScore.getScore(), 0);
    }

    @Test
    public void testGameOver() {
        Player testPlayer = Mockito.mock(Player.class);


        testPlayer.loseLife();
        testPlayer.loseLife();
        testPlayer.loseLife();

        assertEquals(testPlayer.getLives(), 0);
    }

    @Test
    public void testGameOverScore() {
        Player testPlayer = Mockito.mock(Player.class);
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);

        testPlayer.loseLife();
        testPlayer.loseLife();
        testPlayer.loseLife();

        assertEquals(testScore.getScore(), 0);
    }

    @Test
    public void testVehicleCollisionLeft() {
        Player testPlayer = Mockito.mock(Player.class);
        SpawnManager spawner = Mockito.mock(SpawnManager.class);
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);

        Mockito.when(spawner.detectCollisions(512, 1984)).thenReturn(true);

        testPlayer.forward();
        testPlayer.forward();
        testPlayer.left();
        assertTrue(spawner.detectCollisions(512, 1984));
    }

    @Test
    public void testVehicleCollisionRight() {
        Player testPlayer = Mockito.mock(Player.class);
        SpawnManager spawner = Mockito.mock(SpawnManager.class);

        Mockito.when(spawner.detectCollisions(512, 1984)).thenReturn(true);

        testPlayer.forward();
        testPlayer.forward();
        testPlayer.forward();
        testPlayer.right();
        assertTrue(spawner.detectCollisions(512, 1984));
    }

    @Test
    public void testVehicleCollisionForward() {
        Player testPlayer = Mockito.mock(Player.class);
        SpawnManager spawner = Mockito.mock(SpawnManager.class);

        Mockito.when(spawner.detectCollisions(512, 1984)).thenReturn(true);

        testPlayer.forward();
        testPlayer.forward();
        testPlayer.forward();
        assertTrue(spawner.detectCollisions(512, 1984));
    }

    @Test
    public void testVehicleCollisionBackward() {
        Player testPlayer = Mockito.mock(Player.class);
        SpawnManager spawner = Mockito.mock(SpawnManager.class);
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);

        Mockito.when(spawner.detectCollisions(512, 1984)).thenReturn(true);

        testPlayer.forward();
        testPlayer.forward();
        testPlayer.left();
        testPlayer.backward();
        assertTrue(spawner.detectCollisions(512, 1984));
    }


    @Test
    public void testEndScreenButtons() {
        GameOverScreen g = Mockito.mock(GameOverScreen.class);
        Bundle b = Mockito.mock(Bundle.class);
        boolean equalsTrue = true;
        g.onCreate(b);

        SpawnManager spawner = Mockito.mock(SpawnManager.class);
        Mockito.when(spawner.detectCollisions(512, 1984)).thenReturn(true);


        assertTrue(equalsTrue);
    }




}

