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
public class Houyi extends Character {

    public int x, y;
    private double velocityY = 0;
    private double fall = 0.5;
    public boolean isJumping = false;
    public boolean isWalkingLeft = false;
    public boolean isWalkingRight = false;
    private boolean onCloud;
    public Heart[] hearts = new Heart[3];
    public int health = 3;
    private int width, height;
    private PImage image;
    public PApplet app;
    private PImage[] walkLeft;
    private PImage[] walkRight;

    // Constructor
    Houyi(PApplet p, int x, int y, String imagePath) {
        super("Houyi");
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.x = x;
        this.y = y;
        this.width = image.width;
        this.height = image.height;
        hearts[0] = new Heart(p, 20, 20, "images/heart.png");
        hearts[1] = new Heart(p, 80, 20, "images/heart.png");
        hearts[2] = new Heart(p, 140, 20, "images/heart.png");
    }

    /**
     * Sets the position of Houyi.
     * @param x New X position.
     * @param y New Y position.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        velocityY = 0;
        isJumping = false;
    }

    /**
     * Decreases Houyi's health when taking damage.
     */
    public void takeDamage() {
        this.health--;
    }

    /**
     * Applies gravity to Houyi and checks for cloud collisions.
     * @param clouds Array of clouds to check collision.
     */
    public void applyFall(Cloud[] clouds) {
        if (!isJumping) { // Apply gravity when not jumping
            velocityY += fall;
        }
        y += velocityY; // Move down
        onCloud = false;
        // Loops through cloud objects to detect collision
        for (Cloud cloud : clouds) {
            if (isCollidingWith(cloud) && velocityY > 0) { // Check collision when falling
                y = cloud.y - height / 2; // Place Houyi on top of the cloud
                velocityY = 0; // Stop downward motion
                isJumping = false;
                if (cloud.canFade) {
                    cloud.fade(); // Start fading effect only for specific clouds
                }
            }
        }
        if (!onCloud) {
            velocityY += fall; // Keep falling when not on cloud
        }
    }

    /**
     * Makes Houyi jump upwards.
     */
    public void jump() {
        if (!isJumping) { // Prevent multiple jumps while mid-air
            velocityY = -10;
            isJumping = true;
        }
    }

    /**
     * Loads left-facing animation.
     */
    public void left() {
        isWalkingLeft = true;
        walkLeft = new PImage[4]; // Creates an array for 4 animation frames
        for (int i = 0; i < 4; i++) { // Loops through the frame index
            walkLeft[i] = app.loadImage("images/walk" + (i + 1) + "_left.png"); // Loads animation images
        }
    }

    /**
     * Loads right-facing animation.
     */
    public void right() {
        isWalkingRight = true;
        walkRight = new PImage[4]; // Creates an array for 4 animation frames
        for (int i = 0; i < 4; i++) { // Loops through the frame index
            walkRight[i] = app.loadImage("images/walk" + (i + 1) + "_right.png"); // Loads animation images
        }
    }

    /**
     * Moves Houyi by a specified amount in both horizontal and vertical directions.
     * @param dx Change in X position.
     * @param dy Change in Y position.
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    /**
     * Checks if Houyi collides with another object.
     *
     * @param object The object to check collision with.
     * @return True if Houyi is colliding, otherwise false.
     */
    public boolean isCollidingWith(Object object) {
        boolean isLeftOfOtherRight = x < object.x + object.width;
        boolean isRightOfOtherLeft = x + width > object.x;
        boolean isAboveOtherBottom = y < object.y + object.height;
        boolean isBelowOtherTop = y + height > object.y;
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }

    /**
     * Draws Houyi with appropriate animation frames.
     */
    public void draw() {
        if (isWalkingLeft) { // If Houyi is walking left
            int frame = (app.frameCount / 5) % walkLeft.length; // Select animation frame based on time
            app.image(walkLeft[frame], x, y); // Display left-walking frame
        } else if (isWalkingRight) { // If Houyi is walking right
            int frame = (app.frameCount / 5) % walkRight.length; // // Select animation frame based on time
            app.image(walkRight[frame], x, y); // Display right-walking frame
        } else {
            app.image(image, x, y); // Display idle sprite
        }
    }
}
