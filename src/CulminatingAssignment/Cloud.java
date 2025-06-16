/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CulminatingAssignment;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author 345700744
 */
public class Cloud extends Object {

    private PImage image;
    private PApplet app;
    private boolean isVisible = true;
    private boolean disappears;
    private int disappearTime = 120;
    public boolean canFade = false;
    private int alpha = 255; // Full opacity
    private int speed = 1;
    private int startX;
    private int startY;

    //Constructor for a basic cloud
    public Cloud(PApplet p, int x, int y, String imagePath) {
        super(p, "Cloud", x, y, false, imagePath);
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.startX = x;
        this.startY = y;
        this.disappears = false;
    }

    // Overloaded constructor to enable disappearing and fading effects
    public Cloud(PApplet p, int x, int y, String imagePath, boolean disappears, boolean canFade) {
        super(p, "Cloud", x, y, false, imagePath);
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.disappears = disappears;
        this.canFade = canFade;
        this.alpha = 255;
    }

    /**
     * Sets the cloud's position.
     * @param x New X position.
     * @param y New Y position.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Makes the cloud move slightly up and down.
     */
    public void floatUpDown() {
        y += speed; // Move vertically

        // Reverse direction if it reaches a boundary
        if (y >= startY + 100 || y <= startY - 10) {
            speed *= -1; // Change direction
        }
    }

    /**
     * Makes the cloud move slightly left and right.
     */
    public void floatLeftRight() {
        x += speed; // Move horizontally

        // Reverse direction if it reaches a boundary
        if (x >= startX + 100 || x <= startX - 30) {
            speed *= -1; // Change direction
        }
    }

    /**
     * Gradually fades the cloud until it disappears.
     */
    public void fade() {
        setPosition(startX, startY); // Ensure the cloud stays in position
        if (canFade && alpha > 0) { // Only fade if allowed
            alpha -= 0.1; // Reduce opacity gradually
        }
        if (alpha == 0) { // Once fully transparent, remove the cloud
            isVisible = false;
            setPosition(10000, 10000); // Move off-screen
        }
    }

    /**
     * Makes the cloud disappear.
     */
    public void disappear() {
        if (disappears) {
            isVisible = false;
        }
    }

    /**
     * Updates the cloud's state, allowing it to reappear after disappearing.
     */
    public void update() {
        if (!isVisible) {
            disappearTime--; // Countdown before reappearing
            if (disappearTime <= 0) {
                isVisible = true; // Cloud reappears
                alpha = 255; // Reset transparency
                disappearTime = 120; // Reset timer
            }
        }
    }

    /**
     * Draws the cloud on the screen if it is visible.
     */
    public void draw() {
        if (isVisible) {
            if (canFade) {
                app.tint(255, alpha); // Apply fading effect
            }
            app.image(image, x, y);
            app.tint(255, 255); // Reset transparency effect for other cloud
        }
        update();
    }

    /**
     * Displays cloud information.
     */
    @Override
    public void displayInfo() {
        super.displayInfo();
        if (canFade) {
            System.out.println("This cloud can fade");
        }
    }
}
