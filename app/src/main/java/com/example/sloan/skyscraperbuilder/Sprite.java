package com.example.sloan.skyscraperbuilder;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Sprite - this class is used to represent sprites,
 * which are in-game objects that can move around.
 * This class is fairly simple and is just meant to gather together
 * several variables that would be associated with in-game sprite objects
 * such as x/y location, width/height size, dx/dy velocity, and Paint color.
 *
 * Created by clintf on 4/11/s016.
 */

public abstract class Sprite {

    private float spriteWidth;
    private float spriteHeight;

    /**
     * represents the bounds of this as represented by
     *     a rectangle
     */
    private RectF rect = new RectF();

    private float xVelocity = 0.0f;
    private float yVelocity = 0.0f;

    private Paint paint = new Paint(Color.BLUE);

    /**
     * Sprite constructor
     *
     * @param locX - top left x coordinate of this Sprite
     * @param locY - top left y coordinate of this Sprite
     * @param xVel - x velocity of this Sprite
     * @param yVel - y velocity of this Sprite
     */
    public Sprite(float locX, float locY, float xVel, float yVel){

        rect.offsetTo(locX, locY);
        xVelocity = xVel;
        yVelocity = yVel;
    }

    /**
     * setLocation - sets the Sprite's x,y location on
     * screen to be the given values.
     * @param x - represents the top left x coordinate
     * @param y - represents the top left y coordinate
     */
    public void setLocation(float x, float y){
        rect.offsetTo(x, y);
    }

    /**
     * setVelocity - sets the velocity of the Sprite
     * @param x - x velocity
     * @param y - y velocity
     */
    public void setVelocity(float x, float y){
        xVelocity = x;
        yVelocity = y;
    }


    /**
     * setSize - sets the Sprite's size to the given values
     *
     * @param width
     * @param height
     */
    public void setSize(float width, float height){

        spriteWidth = width;
        spriteHeight =  height;
        //rect.offset(width, height);
        rect.set(rect.left, rect.top, (rect.left+width), (rect.top +height));
    }


    /**
     * move - tells the sprite to move itself by its current velocity dx,dy.
     */
    public void move(){
        rect.offset(xVelocity, yVelocity);
    }


    /**
     * updateLocation - moves the Sprite while checking to see if
     * the Sprite is about to go out of the bounds of the params
     * width and height reversing the velocity of this if it
     * goes out the bounds of the width and/or height.
     *
     * @param width - represents the width of the View the Sprite
     *              is drawn in.
     * @param height - represents the height of the View the Sprite
     *               is drawn in.
     */
    public void updateLocation(float width, float height){

        this.move();

        // handles Sprite reaching the left or right side
        // of the view by reversing the xVelocity
        if((rect.left) < 0 || (rect.right) > width){
            xVelocity = xVelocity * -1;
        }

        // handles Sprite reaching the top or bottom
        // of the View by reversing the yVelocity
        if((rect.top) < 0 || (rect.bottom) > height){
            yVelocity *= -1;
        }
    }


    // accessors and mutators
    
    public RectF getRect() {
        return rect;
    }

    public void setRect(RectF rect) {
        this.rect = rect;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public Rect getBounds(){ return new Rect((int)rect.left, (int)rect.top, (int)(rect.left+spriteWidth), (int)(rect.top+spriteHeight)); }


    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }


    /**
     * draws this according to the type of Sorite is subclassed
     * @param canvas - not null medium for drawing this on for
     *               presentation
     */
    public abstract void draw(Canvas canvas);

}
