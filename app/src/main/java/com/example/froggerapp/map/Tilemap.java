package com.example.froggerapp.map;


import static com.example.froggerapp.map.MapLayout.NUM_COL_TILES;
import static com.example.froggerapp.map.MapLayout.NUM_ROW_TILES;
import static com.example.froggerapp.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.froggerapp.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.froggerapp.GameDisplay;
import com.example.froggerapp.characters.SpriteSheet;

public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public Tilemap(SpriteSheet spriteSheet) {
        mapLayout = new MapLayout();
        this.spriteSheet = spriteSheet;
        initializeTilemap();
    }

    private void initializeTilemap() {
        int[][] layout = mapLayout.getLayout();
        tilemap = new Tile[NUM_ROW_TILES][NUM_COL_TILES];
        for (int iRow = 0; iRow < NUM_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < NUM_COL_TILES; iCol++) {
                tilemap[iRow][iCol] = Tile.getTile(
                        layout[iRow][iCol],
                        spriteSheet,
                        getRectByIndex(iRow, iCol)
                );
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(NUM_COL_TILES * TILE_WIDTH_PIXELS,
                NUM_ROW_TILES * TILE_HEIGHT_PIXELS, config);

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int iRow = 0; iRow < NUM_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < NUM_COL_TILES; iCol++) {
                tilemap[iRow][iCol].draw(mapCanvas);
            }
        }


    }

    private Rect getRectByIndex(int idxRow, int idxCol) {
        return new Rect(
                idxCol * TILE_WIDTH_PIXELS,
                idxRow * TILE_HEIGHT_PIXELS,
                (idxCol + 1) * TILE_WIDTH_PIXELS,
                (idxRow + 1) * TILE_HEIGHT_PIXELS
        );
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(mapBitmap, 0, 256, null);

    }

    public void update() {

    }

    public boolean checkCollisions(double playerX, double playerY) {
        if (mapLayout.getLayout()[(int) (playerY - 256) / 64][(int) playerX / 64] == 1) {
            return true;
        }
        return false;
    }

    public boolean checkWin(double playerX, double playerY) {
        if (mapLayout.getLayout()[(int) (playerY - 256) / 64][(int) playerX / 64] == 0) {
            return true;
        }
        return false;
    }

    public int[] getScoreVal() {
        return mapLayout.getScoreVal();
    }
}