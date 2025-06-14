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
    private int speed = 1;
    private int startX;
    private int startY;
    

    // Constructor
    public Cloud(PApplet p, int x, int y, boolean disappears, int timer, String imagePath) {
        super(p, "Cloud", x, y, false, imagePath);
        this.disappears = disappears;
        this.timer = timer;
        this.startX = x;
        this.startY = y;
    }

    // Floating effect (moves up and down slightly)
    public void floatUpDown() {
        y += speed; // Cloud moves horizontally

        // Reverse direction if it reaches a boundary
        if (y >= startY + 100 || y <= startY - 10) { 
            speed *= -1; // Change direction
        }
    }
    
        public void floatLeftRight() {
        x += speed; // Cloud moves horizontally

        // Reverse direction if it reaches a boundary
        if (x >= startX + 100 || x <= startX - 30) { 
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

