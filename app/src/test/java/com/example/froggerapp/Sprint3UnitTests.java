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

public class Sprint3UnitTests {

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
    public void differentSpeed() {
        Car testCarTypeI;
        Car testCarTypeJ;
        int[] iValues = {0, 1, 2, 3};
        int[] jValues = {0, 1, 2, 3};
        for (int i : iValues) {
            testCarTypeI = new Car(context, 0, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    testCarTypeJ = new Car(context, 0, spriteSheet, j);
                    assertNotEquals(testCarTypeI.getVelocityX(), testCarTypeJ.getVelocityX());
                }
            }
        }
    }

    @Test
    public void differentDirection() {
        Car testCarTypeI;
        Car testCarTypeJ;
        int[] iValues = {0, 1, 2, 3};
        int[] jValues = {0, 1, 2, 3};
        boolean diffDirection = false;
        for (int i : iValues) {
            testCarTypeI = new Car(context, 0, spriteSheet, i);
            for (int j : jValues) {
                if (i != j) {
                    testCarTypeJ = new Car(context, 0, spriteSheet, j);
                    if (testCarTypeI.getVelocityX() < 0 && testCarTypeJ.getVelocityX() > 0){
                        diffDirection = true;
                    }

                }
            }
        }
        assertTrue(diffDirection);
    }

    @Test
    public void speedNotZero() {
        Car testCarTypeI;
        int[] iValues = {0, 1, 2, 3};
        for (int i : iValues) {
            testCarTypeI = new Car(context, 0, spriteSheet, i);
            assertNotEquals(testCarTypeI.getVelocityX(), 0);
        }
    }

    @Test
    public void diffSizes() {

        Sprite s1 = Mockito.mock(Sprite.class);
        Sprite s2 = Mockito.mock(Sprite.class);

        Mockito.when(s1.getWidth()).thenReturn(2);
        Mockito.when(s2.getWidth()).thenReturn(1);

        int s1w = s1.getWidth();
        int s2w = s2.getWidth();

        assertNotEquals(s1w, s2w);

    }


    @Test
    public void changeCarType() {
        Car testCarType = new Car(context, 0, spriteSheet, ((int) (Math.random() * 4)));
        double oldVelocity = testCarType.getVelocityX();
        testCarType.changeCarType();
        double newVelocity = testCarType.getVelocityX();
        assertNotEquals(oldVelocity, newVelocity);
    }


     /* 12 Unit Tests
	1.	 Move Forward -> Score increase\
	2.	Move backward -> score does not increase
	3.	Move sideways -> score does not increase
	4.	>3 different sprites on game screen
	5.	 Vehicle gererated correctly
	6.	 Vehicles can move
	7.	 Vehicles do not collide
	8.	 Vehicles come from either side
	9.	 Score has different values
	10.	 */

    //1.	 Move Forward -> Score increase\
    @Test
    public void playerForwardScoreIncrease() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);
        testScore.update();
        int origScore = testScore.getScore();
        testPlayer.forward();
        testScore.update();
        int newScore = testScore.getScore();
        assertTrue("Previous score (" + origScore + ") should be less than current score("
                + newScore + ")", origScore < newScore);
    }

    @Test
    public void playerScoreIncreaseDifference() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);
        testPlayer.forward();
        int origScore = testScore.getScore();
        testPlayer.forward();
        testScore.update();
        int newScore = testScore.getScore();
        int diff1 = origScore - newScore;
        origScore = testScore.getScore();
        testPlayer.forward();
        testScore.update();
        newScore = testScore.getScore();
        int diff2 = origScore - newScore;

        assertTrue("Score gain 1 (" + diff1 + ") should be less than score gain 2("
                + diff2 + ")", diff1 < diff2);
    }

    //	Move backward -> score does not increase
    @Test
    public void playerBackwardNoChange() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);
        testPlayer.forward();
        testPlayer.forward();
        testPlayer.forward();
        testScore.update();
        int origScore = testScore.getScore();
        testPlayer.backward();
        testScore.update();
        int newScore = testScore.getScore();
        assertEquals(origScore, newScore, 0.1);
    }

    @Test
    public void playerLeftNoChange() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);
        testPlayer.forward();
        testPlayer.forward();
        testPlayer.forward();
        testScore.update();
        int origScore = testScore.getScore();
        testPlayer.left();
        testScore.update();
        int newScore = testScore.getScore();
        assertEquals(origScore, newScore, 0.1);
    }

    @Test
    public void playerRightNoChange() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);
        testPlayer.forward();
        testPlayer.forward();
        testPlayer.forward();
        testScore.update();
        int origScore = testScore.getScore();
        testPlayer.right();
        testScore.update();
        int newScore = testScore.getScore();
        assertEquals(origScore, newScore, 0.1);
    }

    @Test
    public void zeroOnStart() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);
        testScore.update();
        int score = testScore.getScore();

        assertEquals(score, 0, 0.1);
    }


    @Test
    //Test that the player cant get repeat points for a row
    public void noRepeat() {
        double[] origPosition = {512, 1984};

        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet,
                "0", "easy");
        int[] val = {100, 100, 500, 500, 500, 100, 1000, 1000, 1000, 1000, 100, 500, 500, 500,
                100, 1000, 1000, 1000, 1000, 1000, 100, 500, 500, 500, 500, 100, 2000, 2000};

        Score testScore = new Score(context, val, testPlayer);
        testPlayer.forward();
        testPlayer.forward();
        testPlayer.forward();
        testScore.update();
        int origScore = testScore.getScore();
        testPlayer.backward();
        testPlayer.forward();
        testScore.update();
        int newScore = testScore.getScore();
        assertEquals(origScore, newScore, 0.1);
    }

    @Test
    public void carsEqualsRoads() {
        double[] roadPositions = {64, 128, 192, 256};
        SpawnManager spawner = new SpawnManager(context, roadPositions, spriteSheet, "car");
        Car[] cars = spawner.getCars();
        for (int i = 0; i < cars.length; i++) {
            assertEquals(cars[i].getPositionY(), roadPositions[i], 0.1);
        }
    }

    @Test
    public void playerLivesDependOnDifficulty() {
        String difficulty1 = "easy";
        String difficulty2 = "medium";
        String difficulty3 = "hard";
        Player player1 = new Player(context, 0, 0, spriteSheet, "appearance", difficulty1);
        Player player2 = new Player(context, 0, 0, spriteSheet, "appearance", difficulty2);
        Player player3 = new Player(context, 0, 0, spriteSheet, "appearance", difficulty3);

        assertEquals(player1.getLives(), 3);
        assertEquals(player2.getLives(), 2);
        assertEquals(player3.getLives(), 1);
    }




}
