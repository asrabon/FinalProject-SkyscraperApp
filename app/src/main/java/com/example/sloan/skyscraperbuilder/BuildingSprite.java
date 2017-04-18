package com.example.sloan.skyscraperbuilder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;


/**
 * Represents s Sprite in the shape of a Ball
 * Created by clintf on 3/29/17.
 * @author Clint Fuchs
 * @date 3/29/2017
 * @email clintf@coastal.edu
 * @course CSCI 343
 */


public class BuildingSprite extends Sprite {


    public final static float DEFAULT_HEIGHT = 150.0f;
    public final static float DEFAULT_WIDTH = 250.0f;
    private Bitmap buildingBitmap;

    public BuildingSprite(float xLoc, float yLoc, float xVel, float yVel, Bitmap bitmap){
        super(xLoc, yLoc, xVel, yVel);
        buildingBitmap = bitmap;
    }

    /**
     * draws this onto the Canvas parameter
     * @param canvas - not null medium for drawing this on for
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(buildingBitmap, null, super.getRect(), super.getPaint());
    }

}
