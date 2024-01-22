package com.example.froggerapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import android.app.Activity;
import android.content.Context;
import static org.mockito.runners.MockitoJUnitRunner.*;

import com.example.froggerapp.characters.SpriteSheet;

public class Sprint2UnitTests {

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
    public void playerForwardTest() {
        double[] origPosition = {512, 1984};
        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet, "0", "easy");
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        testPlayer.forward();
        origPosition[1] -= 64;
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        assertEquals(testPlayer.getPosition()[1], origPosition[1], 0.1);
    }

    @Test
    public void playerBackwardTest() {
        double[] origPosition = {512, 1920};
        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet, "0", "easy");
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        testPlayer.backward();
        origPosition[1] += 64;
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        assertEquals(testPlayer.getPosition()[1], origPosition[1], 0.1);
    }

    @Test
    public void playerLeftTest() {
        double[] origPosition = {512, 1984};
        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet, "0", "easy");
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        testPlayer.left();
        origPosition[0] -= 64;
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        assertEquals(testPlayer.getPosition()[1], origPosition[1], 0.1);
    }

    @Test
    public void playerRightTest() {
        double[] origPosition = {512, 1984};
        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet, "0", "easy");
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        testPlayer.right();
        origPosition[0] += 64;
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        assertEquals(testPlayer.getPosition()[1], origPosition[1], 0.1);
    }

    @Test
    public void leftBorderTest() {
        double[] origPosition = {0, 1984};
        double[] newPosition = {0 - 64, 1984};
        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet, "0", "easy");
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        testPlayer.left();
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        assertEquals(testPlayer.getPosition()[1], origPosition[1], 0.1);
        assertNotEquals(testPlayer.getPosition()[0], newPosition[0], 0.1);
    }

    @Test
    public void RightBorderTest() {
        double[] origPosition = {1024, 1984};
        double[] newPosition = {512 + 64, 1984};
        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet, "0", "easy");
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        testPlayer.right();
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        assertEquals(testPlayer.getPosition()[1], origPosition[1], 0.1);
        assertNotEquals(testPlayer.getPosition()[0], newPosition[0], 0.1);
    }

    @Test
    public void BottomBorderTest() {
        double[] origPosition = {512, 1984};
        double[] newPosition = {512, 1984 + 64};
        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet, "0", "easy");
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        testPlayer.backward();
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        assertEquals(testPlayer.getPosition()[1], origPosition[1], 0.1);
        assertNotEquals(testPlayer.getPosition()[1], newPosition[1], 0.1);
    }

    @Test
    public void TopBorderTest() {
        double[] origPosition = {512, 256};
        double[] newPosition = {512, 256 - 64};
        Player testPlayer = new Player(context, origPosition[0], origPosition[1], spriteSheet, "0", "easy");
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        testPlayer.forward();
        assertEquals(testPlayer.getPosition()[0], origPosition[0], 0.1);
        assertEquals(testPlayer.getPosition()[1], origPosition[1], 0.1);
        assertNotEquals(testPlayer.getPosition()[1], newPosition[1], 0.1);
    }

    @Test
    public void playerNameNull() {
        InitialConfigurationScreen initScreen =  Mockito.mock(InitialConfigurationScreen.class);
        String n = "";
        String error = "";
        Mockito.when(initScreen.verifyUsername(n, error)).thenReturn("Please enter a Name\n");
        assertEquals("Please enter a Name\n", initScreen.verifyUsername(n, error));
    }

    @Test
    public void playerNameWhitespace() {
        InitialConfigurationScreen initScreen =  Mockito.mock(InitialConfigurationScreen.class);
        String n = "";
        String error = "";

        for (int i = 0; i < 10; i++) {
            Mockito.when(initScreen.verifyUsername(n, error)).thenReturn("Invalid Name - Cannot Be Only Whitespace\n");
            assertEquals("Invalid Name - Cannot Be Only Whitespace\n", initScreen.verifyUsername(n, error));
            error = "";
            n = n + " ";
        }
    }

    @Test
    public void playerNameTooLong() {
        InitialConfigurationScreen initScreen =  Mockito.mock(InitialConfigurationScreen.class);
        String n = "hisdhishdishdhisdh";
        String error = "";
        Mockito.when(initScreen.verifyUsername(n, error)).thenReturn("Name too long, reduce a bit!\n");
        assertEquals("Name too long, reduce a bit!\n", initScreen.verifyUsername(n, error));
    }

    @Test
    public void noSprite() {
        InitialConfigurationScreen initScreen =  Mockito.mock(InitialConfigurationScreen.class);
        String n = "";
        String error = "";
        Mockito.when(initScreen.verifySpriteSelected(n, error)).thenReturn("Please Choose a Sprite\n");
        assertEquals("Please Choose a Sprite\n", initScreen.verifySpriteSelected(n, error));
    }
}
