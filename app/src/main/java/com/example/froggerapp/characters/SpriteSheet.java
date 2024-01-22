package com.example.froggerapp.characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.froggerapp.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.playercolors,
                bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[3];
        spriteArray[0] = new Sprite(this, new Rect(0 * 64, 0, 1 * 64, 64));
        spriteArray[1] = new Sprite(this, new Rect(1 * 64, 0, 2 * 64, 64));
        spriteArray[2] = new Sprite(this, new Rect(2 * 64, 0, 3 * 64, 64));
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getGoalSprite() {
        return getSpriteByIndex(1, 0);
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(1, 2);
    }

    public Sprite getRiverSprite() {
        return getSpriteByIndex(1, 1);
    }

    public Sprite getRoadSprite() {
        return getSpriteByIndex(1, 3);
    }

    public Sprite getPlayerSprite(String choice) {
        Sprite out = getSpriteByIndex(0, 1);
        switch (choice) {
        case "0":
            out = getSpriteByIndex(0, 1);
            break;
        case "1":
            out = getSpriteByIndex(0, 2);
            break;
        case "2":
            out = getSpriteByIndex(0, 0);
            break;
        default:
            break;
        }
        return out;
    }

    public Sprite getMsPacManSprite() {
        return getSpriteByIndex(2, 3);
    }
    public Sprite getGhostSprite() {
        return getSpriteByIndex(3, 0);
    }

    public Sprite getPacManSprite() {
        if (((int) (Math.random() * 2)) == 0) {
            return getSpriteByIndex(2, 0);
        } else {
            return new Sprite(this, new Rect(1 * SPRITE_WIDTH_PIXELS,
                    2 * SPRITE_HEIGHT_PIXELS,
                    3 * SPRITE_WIDTH_PIXELS,
                    3 * SPRITE_HEIGHT_PIXELS));
        }

    }

    public Sprite getFastManSprite() {
        return getSpriteByIndex(0, (int) (Math.random() * 3));
    }

    public Sprite getLogSprite() {
        return getLogByIndex(4, 0);
    }



    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol * SPRITE_WIDTH_PIXELS,
                idxRow * SPRITE_HEIGHT_PIXELS,
                (idxCol + 1) * SPRITE_WIDTH_PIXELS,
                (idxRow + 1) * SPRITE_HEIGHT_PIXELS
        ));
    }

    private Sprite getLogByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol * SPRITE_WIDTH_PIXELS,
                idxRow * SPRITE_HEIGHT_PIXELS,
                (idxCol + 4) * SPRITE_WIDTH_PIXELS,
                (idxRow + 4) * SPRITE_HEIGHT_PIXELS
        ));
    }
}