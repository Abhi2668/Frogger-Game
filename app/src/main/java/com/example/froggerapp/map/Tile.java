package com.example.froggerapp.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.froggerapp.characters.SpriteSheet;

abstract class Tile {

    protected final Rect mapLocationRect;
    
    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        
        switch (idxTileType) {
        case 0:
            return new GoalTile(spriteSheet, mapLocationRect);
        case 1:
            return new RiverTile(spriteSheet, mapLocationRect);
        case 2:
            return new GrassTile(spriteSheet, mapLocationRect);
        case 3:
            return new RoadTile(spriteSheet, mapLocationRect);
        default:
            return null;
        }
    }

    public abstract void draw(Canvas canvas);
}
