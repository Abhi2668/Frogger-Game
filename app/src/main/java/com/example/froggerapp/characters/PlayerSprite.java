package com.example.froggerapp.characters;

import android.graphics.Canvas;

public class PlayerSprite {

    private final Sprite sprite;

    public PlayerSprite(SpriteSheet spriteSheet) {
        sprite = spriteSheet.getPlayerSprite("2");
    }

    public void draw(Canvas canvas, int x, int y) {
        sprite.draw(canvas, x, y);
    }
}
