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
public class Obstacle {
    private String type;
    public int x, y;
    public int width, height;
    private boolean isDangerous;
    private PImage image;
    private PApplet app;

    // Constructor
    public Obstacle(PApplet p, String type, int x, int y, boolean isDangerous, String imagePath) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
        this.isDangerous = isDangerous;
    }


    // Display obstacle information
    public void displayInfo() {
        System.out.println("Obstacle Type: " + type);
        System.out.println("Position: (" + x + ", " + y + ")");
        System.out.println("Size: " + width + " x " + height);
        System.out.println("Dangerous: " + isDangerous);
    }
    
        public void draw() {
        app.image(image, x, y);
    }
}
