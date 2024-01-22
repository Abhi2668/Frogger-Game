package com.example.froggerapp.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.froggerapp.characters.Sprite;
import com.example.froggerapp.characters.SpriteSheet;

class RiverTile extends Tile {
    private final Sprite sprite;

    public RiverTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getRiverSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
