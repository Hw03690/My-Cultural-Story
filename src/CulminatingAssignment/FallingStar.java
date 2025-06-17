/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CulminatingAssignment;

/**
 *
 * @author hw036
 */
import processing.core.PApplet;
import processing.core.PImage;

class FallingStar {

    public int x, y, speed;
    private int width, height;
    private PImage image;
    private PApplet app;

    // Constructor
    FallingStar(PApplet p, int x, int y, int speed, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = app.loadImage(imagePath);
    }

    /**
     * Updates the position of the star to create a falling effect.
     */
    void update() {
        y += speed; // Falling effect
        if (y > app.height) {
            resetStar(); // If it reaches the bottom, reset position
        }
    }

    /**
     * Resets the star to the top of the screen at a random X position.
     */
    void resetStar() {
        x = (int) app.random(app.width); // New random X position
        y = 0; // Reset to top
    }

    /**
     * Draws the star at its current position.
     */
    void draw() {
        app.image(image, x, y);
    }
}
