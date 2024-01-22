package com.example.froggerapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.froggerapp.characters.SpriteSheet;
import com.example.froggerapp.functionalUI.MovementButtons;
import com.example.froggerapp.map.Tilemap;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final GameDisplay gameDisplay;
    private final Tilemap tilemap;
    private GameLoop gameLoop;
    private Context context;
    private MovementButtons mb;
    private Score playerScore;

    private Lives playerLives;

    private final SpawnManager carSpawner;
    private final SpawnManager logSpawner;
    private final double[] roadPositions = {768, 832, 896, 960, 1024, 1408, 1472, 1536, 1600};
    private final double[] riverPositions = {448, 512, 576, 640, 1152, 1216, 1280, 1728,
                                                1792, 1856};

    public Game(Context context, Bundle config) {
        super(context);
        this.context = context;
        String pName = config.getString("pName");
        String image = config.getString("sprite");
        String difficulty = config.getString("diff");

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        //INITIALIZE BUTTONS
        mb = new MovementButtons();

        //INITIALIZE GAME OBJECTS: PLAYER, LOGS
        SpriteSheet spriteSheet = new SpriteSheet(context);
        player = new Player(getContext(), 512, 1984, spriteSheet, image, difficulty);


        //INITIALIZE DISPLAY
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels);

        //INITIALIZE MAP
        tilemap = new Tilemap(spriteSheet);
        int[] scoreVals = tilemap.getScoreVal();

        setFocusable(true);
        carSpawner = new SpawnManager(context, roadPositions, spriteSheet, "car");
        logSpawner = new SpawnManager(context, riverPositions, spriteSheet, "log");

        playerScore = new Score(context, scoreVals, player);
        playerLives = new Lives(context, player);
    }

    @Override
    //For now, this is a simple way to move player by clicking on the screen
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
        //WHEN PRESSING DOWN ON SCREEN
        case MotionEvent.ACTION_DOWN:
            if (x < 64 * 14 && x > 64 * 12 && y > 64 * 23 && y < 64 * 25) {
                player.forward(); //CHECK IF PRESSING FORWARD
            } else if (x < 64 * 14 && x > 64 * 12 && y > 64 * 27 && y < 64 * 29) {
                player.backward(); //CHECK IF PRESSING BACKWARD
            } else if (x < 64 * 12 && x > 64 * 10 && y > 64 * 25 && y < 64 * 27) {
                player.left(); //CHECK IF PRESSING LEFT
            } else if (x < 64 * 16 && x > 64 * 14 && y > 64 * 25 && y < 64 * 27) {
                player.right(); //CHECK IF PRESSING RIGHT
            }
            update();
            break;
        default:
            break;
        }
        return true;
    }



    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //DRAW TILEMAP
        tilemap.draw(canvas, gameDisplay);

        //DRAW OBJECTS

        carSpawner.draw(canvas, "car");
        logSpawner.draw(canvas, "log");

        player.draw(canvas);
        mb.draw(canvas);

        playerScore.draw(canvas);
        playerLives.draw(canvas);

        //drawFPS(canvas);
        //drawUPS(canvas);


    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update() {
        //update game state
        player.update();
        carSpawner.update("car");
        logSpawner.update("log");
        boolean onLog = false;
        if (logSpawner.logCollision(player)) {
            onLog = true;
        } else {
            onLog = false;
        }
        if (carSpawner.detectCollisions(player.getPosition()[0], player.getPosition()[1])) {
            player.loseLife();
            playerScore.reset();
        } else if (!onLog) {
            if (tilemap.checkCollisions(player.getPosition()[0], player.getPosition()[1])) {
                player.loseLife();
                playerScore.reset();
            }
        }
        playerScore.update();
        gameDisplay.update();
        if (tilemap.checkWin(player.getPosition()[0], player.getPosition()[1])) {
            player.winGame();
        }
    }
    public Player getPlayer() {
        return player;
    }

    public int getHighScore() {
        return playerScore.getHighScore();
    }


    public void endGame() {
        gameLoop.kill();
    }

}
