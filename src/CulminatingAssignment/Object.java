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
public class Object {

    private String type;
    public int x, y;
    public int width, height;
    private boolean isDangerous;
    private PImage image;
    private PApplet app;

    // Constructor
    public Object(PApplet p, String type, int x, int y, boolean isDangerous, String imagePath) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
        this.isDangerous = isDangerous;
    }

    /**
     * Displays object information.
     */
    public void displayInfo() {
        System.out.println("Object Type: " + type);
        System.out.println("Position: (" + x + ", " + y + ")");
        System.out.println("Size: " + width + " x " + height);
        System.out.println("Dangerous: " + isDangerous);
    }

    /**
     * Checks if the object is clicked based on mouse coordinates.
     *
     * @param mouseX X position of the mouse.
     * @param mouseY Y position of the mouse.
     * @return True if the object was clicked, false otherwise.
     */
    public boolean isClicked(int mouseX, int mouseY) {
        int width = image.width;
        int height = image.height;

        return mouseX >= x && mouseX <= x + width // Check horizontal click range
                && mouseY >= y && mouseY <= y + height; // Check vertical click range
    }

    /**
     * Draws the object at its current position on the screen.
     */
    public void draw() {
        app.image(image, x, y);
    }
}
