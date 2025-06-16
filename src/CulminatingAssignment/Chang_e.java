/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CulminatingAssignment;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Helen
 */
public class Chang_e extends Character {

    private int x, y;
    private PApplet app;
    private PImage image;

    // Constructor
    Chang_e(PApplet p, int x, int y, String imagePath) {
        super("Chang_e");
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.x = x;
        this.y = y;
    }

    /**
     * Moves Chang_e horizontally while keeping her within window bounds.
     *
     * @param dx The amount to move in the x direction.
     */
    public void move(int dx) {
        x += dx; // Update position
        // Keep Chang_e within screen boundaries
        if (x < 0) {
            x = 0; // Prevent moving beyond the left boundary
        }
        if (x > app.width - image.width) {
            x = app.width - image.width; // Prevent moving beyond the left boundary
        }
    }

    /**
     * Updates Chang_e’s appearance to face left.
     */
    public void left() {
        this.image = app.loadImage("images/chang_eLeft.png");
    }

    /**
     * Updates Chang_e’s appearance to face right.
     */
    public void right() {
        this.image = app.loadImage("images/chang_eRight.png");
    }

    /**
     * Checks if a falling star has been caught by Chang_e.
     *
     * @param star The FallingStar object to check collision.
     * @return True if the star is within range of Chang_e, otherwise false.
     */
    public boolean isCaught(FallingStar star) {
        return star.x > x - 40 && star.x < x + 40 // Check horizontal collision
                && star.y > y - 40 && star.y < y + 40;  // Check vertical collision
    }

    /**
     * Draws Chang_e at her current position.
     */
    public void draw() {
        app.image(image, x, y);
    }
}
