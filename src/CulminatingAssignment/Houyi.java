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
  public int x, y;      // Position
  private double velocityY = 0; 
  private double fall = 0.5;
  boolean isJumping = false; // Track jump status
    boolean isWalkingLeft = false; // Track jump status
        boolean isWalkingRight = false; // Track jump status


  private int health = 3;  // Player health
  private int width, height;
  private PImage image;
  public PApplet app;
  PImage[] walkLeft;
  PImage[] walkRight;
  
  Houyi(PApplet p, int x, int y, String imagePath ) {
    this.app = p;
    this.image = app.loadImage(imagePath);
    this.x = x;
    this.y = y;
    this.width = image.width;
    this.height = image.height;
  }

  public void setPosition(int x, int y){
      this.x = x;
      this.y = y;
      velocityY = 1;
  }
  
public void applyFall(Cloud[] clouds) {
    velocityY += fall; // Gravity effect
    y += velocityY;

    boolean onCloud = false;

    for (Cloud cloud : clouds) {
        if (isCollidingWith(cloud)) {
            y = cloud.y - height / 2; // Snap Houyi to cloud top
            velocityY = 0; // Stop falling ONLY when landed
            isJumping = false; // Allow jumping again
            onCloud = true;
        }
    }

    if (!onCloud) {
        velocityY += fall; // Keeps falling when NOT on a cloud
    }
}

  public void jump() {
    if (!isJumping) { // Prevent jumping again in mid-air
      velocityY = -10; // Initial jump force
      isJumping = true; 
    }
  }
  
  
  public void left() {
      isWalkingLeft = true; 
          walkLeft = new PImage[4];
     for (int i = 0; i < 4; i++) {
      walkLeft[i] = app.loadImage("images/walk" + (i+1) + "_left.png");
     }
  }
  
    public void right() {
      isWalkingRight = true; 
          walkRight = new PImage[4];
     for (int i = 0; i < 4; i++) {
      walkRight[i] = app.loadImage("images/walk" + (i+1) + "_right.png");
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
          if(isWalkingLeft){
              int frame = (app.frameCount/5)% walkLeft.length;
              app.image(walkLeft[frame], x, y);
          } else if(isWalkingRight){
              int frame = (app.frameCount/5)% walkRight.length;
              app.image(walkRight[frame], x, y);
          }
          else{
      app.image(image, x, y);
      }
      }
}
