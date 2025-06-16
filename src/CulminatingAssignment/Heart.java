/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CulminatingAssignment;

import processing.core.PApplet;
import processing.core.PImage;

public class Heart {

    private int x, y;
    private PImage image;
    private PApplet app;

    // Constructor
    Heart(PApplet p, int x, int y, String imagePath) {
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the heart at its current position on the screen.
     */
    public void draw() {
        app.image(image, x, y);
    }
}
