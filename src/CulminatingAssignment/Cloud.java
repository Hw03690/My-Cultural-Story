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
public class Cloud extends Obstacle {
    private PImage image;
    private PApplet app;
    private boolean disappears;
    private int timer;

    // Constructor
    public Cloud(PApplet p, int x, int y, boolean disappears, int timer, String imagePath) {
        super(p, "Cloud", x, y, false, imagePath);
        this.disappears = disappears;
        this.timer = timer;
    this.image = app.loadImage(imagePath);
    
    }

    // Floating effect (moves up and down slightly)
    public void floatUpDown() {
        y += Math.sin(System.currentTimeMillis() * 0.001) * 2;
    }

    // Update method - clouds disappear over time
    public void update() {
        floatUpDown();
        if (disappears) {
            timer--;
            if (timer <= 0) {
                System.out.println("Cloud at (" + x + ", " + y + ") has vanished!");
            }
        }
    }

    // Override displayInfo method for individual clouds
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Disappears: " + disappears + " in " + timer + " seconds");
    }

    // Draw the cloud image
    public void draw() {
        app.image(image, x, y);
    }
}

