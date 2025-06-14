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
    private double speed = 0.2;
    private int startX;
    

    // Constructor
    public Cloud(PApplet p, int x, int y, boolean disappears, int timer, String imagePath) {
        super(p, "Cloud", x, y, false, imagePath);
        this.disappears = disappears;
        this.timer = timer;
        this.startX = x;
    }

    // Floating effect (moves up and down slightly)
    public void floatUpDown() {
        y += Math.sin(System.currentTimeMillis() * 0.001) * 2;
    }
    
        public void floatLeftRight() {
        x += 0.2; // Cloud moves horizontally

        // Reverse direction if it reaches a boundary
        if (x >= startX + 20 || x <= startX - 20) { 
            speed *= -1; // Change direction
        }
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

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Disappears: " + disappears + " in " + timer + " seconds");
    }

}

