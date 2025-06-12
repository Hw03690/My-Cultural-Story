/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CulminatingAssignment;

import processing.core.PApplet;

import processing.core.PImage;

public class MySketch extends PApplet {
    private PImage background;
    private PImage background1;
    private Story chang_e;
    private Story moon;  
    int stage = 0; 
    private Obstacle portal;
    private Houyi houyi;


    public void settings(){
	   //sets the size of the window
        size (900,731);
    }
    public void setup(){
        background = loadImage("images/night.png");
        background1 = loadImage("images/background1.png");
        chang_e = new Story(this, 360, 600, "images/chang'e_flying.png");
        moon = new Story(this, 605, 110, "images/moon.png");  
        portal = new Obstacle(this, "portal", 650, 475, false, "images/portal.png");
        houyi = new Houyi(this, 360, 650, "images/idle.png");
        
    } 
    
    public void drawCollisions(){
        if (chang_e.isCollidingWith(moon)){
            stage = 2;
        }
    }

    public void draw(){
       if (stage == 0) {  
        background.resize(900,731);
         image(background, 0, 0);
        chang_e.draw();  
        moon.draw();
        chang_e.move(1,2);
        drawCollisions();
       } else if(stage == 1){
            background(255);
       } else if (stage == 2){
           background.resize(900,731);
         image(background, 0, 0);
         moon.draw();
         portal.draw();
         houyi.draw();
         if (keyPressed) {
            if (keyCode == LEFT) {
                houyi.move(-5, 0);
                houyi.left();
            } else if (keyCode == RIGHT) {
                houyi.move(5, 0);
                houyi.right();
            } else if (keyCode == UP) {
                houyi.jump();   
    }
       } else{
             houyi.isWalkingLeft = false;
             houyi.isWalkingRight = false;
         }
    }
}}


