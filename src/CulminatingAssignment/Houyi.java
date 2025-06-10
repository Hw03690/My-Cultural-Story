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
public class Houyi {
  private int x, y;      // Position
  private double velocityY = 0; 
  private double fall = 0.5;
  boolean isJumping = false; // Track jump status
    boolean walkleft = false; // Track jump status

  private int health = 3;  // Player health
  private int width, height;
  private PImage image;
  public PApplet app;
  PImage[] walkLeft;
   int numImages = 4;
  
  Houyi(PApplet p, int x, int y, String imagePath ) {
    this.app = p;
    this.image = app.loadImage(imagePath);
    this.x = x;
    this.y = y;
    this.width = image.width;
    this.height = image.height;
  }

public void applyFall(int groundLevel) {
    velocityY += fall; // Apply fall effect
    y += velocityY; // Update position
    
    if (y >= groundLevel) { // Stop falling when reaching ground
      y = groundLevel;
      velocityY = 0;
      isJumping = false;
    }
  }

  public void jump() {
    if (!isJumping) { // Prevent jumping again in mid-air
      velocityY = -10; // Initial jump force
      isJumping = true; 
    }
  }
  
  
  public void left() {
      walkleft = true; 
      walkLeft = new PImage[numImages];
     for (int i = 0; i < numImages; i++) {
       walkLeft[i] = app.loadImage("walk" + i+1 + "_left.png");
     }
  }
  
    public void move(int dx, int dy){
      x += dx;
      y += dy;
    }
  
    public boolean isCollidingWith(Obstacle obstacle){
      boolean isLeftOfOtherRight = x < obstacle.x + obstacle.width;
      boolean isRightOfOtherLeft = x + width > obstacle.x;
      boolean isAboveOtherBottom = y < obstacle.y + obstacle.height;
      boolean isBelowOtherTop = y + height > obstacle.y;
      return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
    
      public void draw(){
      app.image(image, x, y);
      for (int i = 0; i < numImages; i++) {
       app.image(walkLeft[i], x, y);
      }
      }
}
