package com.example.froggerapp;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {
    private boolean isRunning = false;
    private Game game;
    private SurfaceHolder surfaceHolder;
    private double averageUPS;
    private double averageFPS;
    private static final double MAX_UPS = 20;
    private static final double UPS_PERIOD =  1E3 / MAX_UPS;



    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;

    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    @Override
    public void run() {
        super.run();

        if (!isRunning) {
            throw new RuntimeException("Game ended");
        }
        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime = 0;
        long sleepTime;

        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while (isRunning) {
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            //Pause loop so there is a cap to UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                elapsedTime = System.currentTimeMillis() - startTime;
                game.update();
                updateCount++;

                sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            }


            //UPS & FPS calculation
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    public void kill() {
        isRunning = false;
    }
}
