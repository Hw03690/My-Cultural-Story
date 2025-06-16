/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CulminatingAssignment;

/**
 *
 * @author 345700744
 */

import processing.core.PApplet;
import processing.core.PImage;

public class Story {

    public int x, y;
    private int speed;
    private PImage image;
    public PApplet app;
    private int width, height;

    // Constructor
    public Story(PApplet p, int x, int y, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = app.loadImage(imagePath);
        this.speed = 0;
        this.width = image.width;
        this.height = image.height;
    }

    /**
     * Checks if this story object is colliding with another.
     *
     * @param other The other Story object to check collision with.
     * @return True if collision is detected, otherwise false.
     */
    public boolean isCollidingWith(Story other) {
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }

    /**
     * Moves the story object by a specified amount.
     *
     * @param dx Change in X position.
     * @param dy Change in Y position.
     */
    public void move(double dx, double dy) {
        x += dx;
        y -= dy;
    }

    /**
     * Draws the story object at its current position on the screen.
     */
    public void draw() {
        app.image(image, x, y);
    }
}
