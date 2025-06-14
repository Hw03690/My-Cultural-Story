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
    int stage = 3; 
    private Obstacle portal;
    private Houyi houyi;
    private Cloud cloud;
    private Cloud cloud2;
    private Cloud cloud3;
    private Cloud [] clouds = new Cloud [3];



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
        houyi = new Houyi(this, 10, 650, "images/idle.png");
        clouds[0] = new Cloud(this, 0, 650, false, 100, "images/cloud3.png");
        clouds[1] = new Cloud(this, 350, 650, false, 100, "images/cloud2.png");
        clouds[2] = new Cloud(this, 700, 500, false, 100, "images/cloud2.png");


    } 
    
    public void drawCollisions(){
        if (chang_e.isCollidingWith(moon)){
            stage = 2;
        }
    }

    public void draw(){
                 if (keyPressed) {
            if (keyCode == LEFT) {
                houyi.move(-5, 0);
                houyi.left();
            } else if (keyCode == RIGHT) {
                houyi.move(5, 0);
                houyi.right();
            } else if (keyCode == UP) {
                println("Jump key pressed!");
                houyi.jump();   
    }
       } else{
             houyi.isWalkingLeft = false;
             houyi.isWalkingRight = false;
             houyi.isJumping = false;
         }
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
         
         
    } else if (stage == 3){
        background1.resize(900,731);
         image(background1, 0, 0);
         for (int i = 0; i<3; i++){
            clouds[i].draw(); 
         }
         clouds[1].floatLeftRight();
         clouds[2].floatUpDown();
         houyi.applyFall(clouds);       
         houyi.draw();
         
        
         

    }
}}


