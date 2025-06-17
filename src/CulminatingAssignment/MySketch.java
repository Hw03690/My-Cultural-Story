/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CulminatingAssignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import processing.core.PApplet;
import processing.core.PImage;

public class MySketch extends PApplet {

    private PImage beginning;
    private PImage background;
    private PImage background1;
    private PImage background2;
    private PImage adventure;
    private PImage enterName;
    private Story chang_eFly;
    private Story moon;
    private static int stage = 8;
    private Character character;
    private boolean setPosition = false;
    private Object playButton;
    private Object portal;
    private Object houyiButton;
    private Object chang_eButton;
    private Object restart;
    private Object home;
    private Houyi houyi;
    private Chang_e chang_e;
    private Cloud cloud;
    private Cloud[] stage4Clouds = new Cloud[3];
    private Cloud[] stage5Clouds = new Cloud[4];
    private Object direction_sign;
    private static int collectedStars = 0;
    private boolean gameOver = false;
    private boolean gameSuccess = false;
    private static boolean showInstructions = true;
    private int timeRemaining = 60;
    private String playerName = "";
    private boolean nameEntered = false;
    private FallingStar[][] stars = new FallingStar[1][3];

    public void settings() {
        // sets the size of the window
        size(900, 731);
    }

    public void setup() {
        // Initialize falling stars
        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[i].length; j++) {
                stars[i][j] = new FallingStar(this, j * 180, i * 100, (int) random(1, 5), "images/star.png");
            }
        }

        beginning = loadImage("images/beginning.png");
        enterName = loadImage("images/enterName.png");
        background = loadImage("images/night.png");
        adventure = loadImage("images/adventure.png");
        background1 = loadImage("images/background1.png");
        background2 = loadImage("images/end.png");

        playButton = new Object(this, "play button", 300, 500, false, "images/playButton.png");
        houyiButton = new Object(this, "houyi button", 250, 300, false, "images/houyiButton.png");
        chang_eButton = new Object(this, "chang_e button", 250, 420, false, "images/chang_eButton.png");
        restart = new Object(this, "restart button", 300, 300, false, "images/restart.png");
        home = new Object(this, "home button", 840, 20, false, "images/home.png");
        chang_eFly = new Story(this, 360, 600, "images/chang'e_flying.png");
        moon = new Story(this, 605, 110, "images/moon.png");
        portal = new Object(this, "portal", 650, 475, false, "images/portal.png");
        direction_sign = new Object(this, "portal", 790, 445, false, "images/direction_sign.png");
        houyi = new Houyi(this, 360, 650, "images/idle.png");
        chang_e = new Chang_e(this, 360, 600, "images/chang_eRight.png");
        cloud = new Cloud(this, 0, 650, "images/cloud4.png");

        // Clouds for Stage 4
        stage4Clouds[0] = new Cloud(this, 0, 650, "images/cloud3.png");
        stage4Clouds[1] = new Cloud(this, 350, 650, "images/cloud2.png");
        stage4Clouds[2] = new Cloud(this, 700, 500, "images/cloud3.png");

        // Clouds for Stage 5
        stage5Clouds[0] = new Cloud(this, 0, 650, "images/cloud3.png");
        stage5Clouds[1] = new Cloud(this, 350, 600, "images/cloud1.png", false, true);
        stage5Clouds[2] = new Cloud(this, 550, 550, "images/cloud1.png", false, true);
        stage5Clouds[3] = new Cloud(this, 750, 500, "images/cloud2.png");

        if (setPosition) { // Reset Houyi's position to the starting coordinates
            houyi.setPosition(10, 650);
        }
    }

    /**
     * Detects collisions between story elements and updates stages.
     */
    public void drawCollisions() {
        if (stage == 1 && chang_eFly.isCollidingWith(moon)) { // If chang_eFly collide with moon, go to stage 2
            stage = 2;
        } else if (stage == 3) {
            if (houyi.isCollidingWith(portal)) { // If houyi collide with portal, go to stage 4;
                stage = 4;
            }
        }
    }

    /**
     * Handles mouse click interactions.
     */
    public void mousePressed() {
        showInstructions = true; // Reset instructions visibility
        // If home button is clicked, go to stage 2 (main menu)
        if (home.isClicked(mouseX, mouseY) && home.isVisible) {
            // Reset game for Houyi
            houyi.health = 3; // Reset health
            houyi.setPosition(360, 650);
            setPosition = false;
            resetGame(); // Reset game for Chang_e
            stage = 2;
        }
        // Start game when play button is clicked and is visible
        if (stage == 0 && playButton.isClicked(mouseX, mouseY) && playButton.isVisible) {
            stage = 1; // Go to stage 1
            // Select character and move to corresponding stage
        } else if (stage == 2) {
            if (houyiButton.isClicked(mouseX, mouseY)) {
                this.character = houyi; // Set character to houyi
                stage = 3; // Go to stage 3
            } else if (chang_eButton.isClicked(mouseX, mouseY)) {
                this.character = chang_e; // Set character to chang_e
                stage = 7; // Go to stage 7
            }
        } else if (restart.isClicked(mouseX, mouseY) && restart.isVisible) { // If restart button is clicked and is visible
            // If Houyi is the active character, restart the game for Houyi
            if (character instanceof Houyi) {
                houyi.health = 3; // Reset health
                stage = 4;
                setPosition = false;
            }
            // If Chang'e is the character, restart the game for Chang_e
            if (character instanceof Chang_e) {
                resetGame();
            }
        }
    }

    /**
     * Resets the game variables and stars when restarting (For Chang_e).
     */
    public void resetGame() {
        gameOver = false;
        gameSuccess = false;
        collectedStars = 0;
        timeRemaining = 60;
        // Loop through all stars and reset their positions
        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[i].length; j++) {
                stars[i][j].resetStar(); // Reset all stars
            }
        }
    }

    /**
     * Handles keyboard input for player name entry.
     */
    public void keyPressed() {
        if (stage == 8 && !nameEntered) {
            if (key == BACKSPACE && playerName.length() > 0) { // Remove last character
                playerName = playerName.substring(0, playerName.length() - 1);
            } else if (key == ENTER) {
                nameEntered = true; // Mark name as entered
                writeToFile(playerName); // Save name to file
                stage = 0; // Go to Start screen
            } else if (key != CODED) {
                playerName += key;
            }
        }
    }

    /**
     * Writes the player's name to a file.
     *
     * @param name The player's name.
     */
    public void writeToFile(String name) {
        try {
            FileWriter writer = new FileWriter("player_name.txt", false); // Create file writer
            writer.write(name); // Write name to file
            writer.close(); // Close file
        } catch (IOException e) {
            println("Error writing file: " + e.getMessage()); // Print error if file writing fails
        }
    }

    /**
     * Loads the player name from a saved file.
     *
     * @return The player's name.
     */
    public String loadPlayerName() {
        try {
            File file = new File("player_name.txt");
            Scanner scanner = new Scanner(file); // Create scanner to read file

            if (scanner.hasNextLine()) {
                playerName = scanner.nextLine(); // Read first line from file
            } else {
                println("No name found in file."); // Show message if no name exists
            }
            scanner.close();
        } catch (Exception e) {
            println("Error reading file: " + e.getMessage()); // Print error if file reading fails
        }
        return playerName;
    }

    /**
     * Displays instructions at the start of the game.
     */
    public void displayInstructions() {
        if (showInstructions) { // Only display instructions if showInstructions is true
            if (character instanceof Houyi) { // If character is Houyi, show instructions for Houyi's mission
                fill(255);
                textSize(25);
                textAlign(CENTER);
                text("Houyi's Mission: Reach the Moon to reunite with Chang’e!", width / 2, height / 2 - 40);
                text("Press any key to begin!", width / 2, height / 2);

            } else if (character instanceof Chang_e) { // Instructions for Chang’e’s mission
                fill(255);
                textSize(25);
                textAlign(CENTER);
                text("Help Chang’e reunite with Houyi! Collect 10 stars before time runs out!", width / 2, height / 2);
                text("Press any key to begin!", width / 2, height / 2 + 40);
            }
            if (keyPressed) { // Hide instructions when a key is pressed
                showInstructions = false;
            }
        }
    }

    /**
     * Checks if Chang’e has caught a falling star and updates the game state.
     */
    public void checkStarCatch() {
        // Loop through all stars
        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[i].length; j++) {
                // If the star exists and Chang’e catches it
                if (stars[i][j] != null && chang_e.isCaught(stars[i][j])) {
                    collectedStars++; // Increase collected star count
                    stars[i][j].resetStar(); // Reset the star’s position
                    // If 10 stars are collected, mark game as successful
                    if (collectedStars >= 10) {
                        gameSuccess = true;
                    }
                }
            }
        }
    }

    /**
     * Draws game elements and controls logic.
     */
    public void draw() {
        // If Houyi's health reaches 0, show restart button and stop further drawing
        if (houyi.health == 0) {
            restart.draw();
            return;
        }
        // Handle Houyi's movement based on keyboard input
        if (character instanceof Houyi) {
            if (keyPressed) {
                if (keyCode == LEFT) {
                    houyi.move(-5, 0); // Move left
                    houyi.left(); // Change to left-facing sprite
                }
                if (keyCode == RIGHT) {
                    houyi.move(5, 0); // Move right
                    houyi.right(); // Change to right-facing sprite
                }
                if (keyCode == UP) {
                    houyi.jump(); // Perform jump action
                }

            } else { // If no key is pressed, reset movement states
                houyi.isWalkingLeft = false;
                houyi.isWalkingRight = false;
                houyi.isJumping = false;
            }
            // Handle Chang_e’s movement based on keyboard input
        } else if (character instanceof Chang_e) {
            if (!gameOver) { // Only allow movement if the game isn’t over
                if (keyCode == LEFT) {
                    chang_e.move(-3); // Move left
                    chang_e.left(); // Change to left-facing sprite
                }
                if (keyCode == RIGHT) {
                    chang_e.move(3); // Move right
                    chang_e.right(); // Change to right-facing sprite
                }
            }
        }
        // Show scenes based on the current game stage
        switch (stage) {
            case 0: // Start screen
                background.resize(900, 731);
                image(beginning, 0, 0);
                playButton.draw();
                break;
            case 1: // Animation of Chang_e flying to the moon
                background.resize(900, 731);
                image(background, 0, 0);
                chang_eFly.draw();
                moon.draw();
                chang_eFly.move(1, 2); // Move animation
                drawCollisions(); // Check collisions
                break;
            case 2: // Character selection screen
                adventure.resize(900, 731);
                image(adventure, 0, 0);
                houyiButton.draw();
                chang_eButton.draw();
                break;
            case 7: // Chang_e’s star collection stage
                if (gameSuccess) { // If the game is won, go to ending screen
                    stage = 6;
                }
                if (gameOver) { // If the time runs out
                    fill(255);
                    textSize(60);
                    text("Time’s Up! Game Over!", 450, 190);
                    restart.draw(); // Show restart button
                    return;
                }
                background1.resize(900, 731);
                image(background1, 0, 0);
                cloud.draw();
                chang_e.draw();
                if (!gameSuccess && !gameOver) {
                    displayInstructions(); // Show instructions until game starts
                }
                if (showInstructions == false) { // When instructions disappear
                    for (int i = 0; i < stars.length; i++) { // Iterate through each row of stars
                        for (int j = 0; j < stars[i].length; j++) { // Iterate through each star in the row
                            if (stars[i][j] != null) {
                                stars[i][j].update(); // Move the star downward based on its speed
                                stars[i][j].draw();
                            }
                        }
                    }

                    checkStarCatch(); // Check if stars are caught
                    // Display collected stars count
                    fill(255);
                    textSize(20);
                    text("Stars Collected: " + collectedStars, 100, 40);
                    if (frameCount % 60 == 0 && timeRemaining > 0) {
                        timeRemaining--; // Decrease timer every second
                    }
                    textSize(20);
                    fill(255);
                    text("Time Left: " + timeRemaining, 80, 80);
                    // Check if time is up
                    if (timeRemaining <= 0) {
                        gameOver = true;
                    }
                }
                break;
            case 3: // Houyi’s portal stage
                background.resize(900, 731);
                image(background, 0, 0);
                moon.draw();
                portal.draw();
                houyi.draw();
                displayInstructions(); // Display game instruction
                drawCollisions(); // Check if Houyi collide with portal
                break;
            case 4: // Cloud jumping stage
                if (!setPosition) {
                    houyi.setPosition(10, 650); // Set correct position for stage 3
                    setPosition = true;
                }
                background1.resize(900, 731);
                image(background1, 0, 0);
                // Loop through all clouds in stage 4 and draw them          
                for (int i = 0; i < 3; i++) {
                    stage4Clouds[i].draw();
                }
                // Add movement effects to certain clouds
                stage4Clouds[1].floatLeftRight(); // Move cloud back and forth horizontally
                stage4Clouds[2].floatUpDown(); // Move cloud up and down
                // Apply gravity effect to Houyi, checking for collisions with clouds
                houyi.applyFall(stage4Clouds);
                houyi.draw();
                if (houyi.x > 890) { // Move to next stage when reaching far-right edge
                    houyi.setPosition(10, 650);
                    stage = 5;
                }
                break;
            case 5: // More cloud challenges
                background1.resize(900, 731);
                image(background1, 0, 0);
                // Draw all clouds for stage 5
                for (int i = 0; i < 4; i++) {
                    stage5Clouds[i].draw();
                }
                // Apply fading effect to one cloud
                stage5Clouds[2].fade(); // Cloud fades automatically and reappears
                houyi.applyFall(stage5Clouds); // Apply gravity
                direction_sign.draw();
                houyi.draw();
                // If Houyi reached the far-right edge of the screen, go to next stage
                if (houyi.x > 890) {
                    stage = 6;
                }
                break;
            case 6: // Victory Screen
                background2.resize(900, 731);
                image(background2, 0, 0);
                fill(255);
                rect(width / 2 - 250, 50, 500, 150, 20); // Text box
                // Victory message
                fill(0);
                textAlign(CENTER);
                textSize(30);
                text("Congratulations!", width / 2, 90);
                if (character instanceof Houyi) { // Display message for Houyi's ending
                    textSize(22);
                    text("You have reunited Chang’e and Houyi!", width / 2, 120);
                } else if (character instanceof Chang_e) { // Display message for Chang’e’s ending
                    textSize(22);
                    text("With your help, Chang’e has created a gift that", width / 2, 120);
                    text("will allow Houyi to ascend and reunite with her.", width / 2, 150);
                }
                // Display the player’s name
                textSize(20);
                text("Hero: " + loadPlayerName(), width / 2, 180);

                break;
            case 8: // Name entry screen
                enterName.resize(900, 731);
                image(enterName, 0, 0);
                fill(0);
                textSize(60);
                text(playerName, 160, 490);
                break;
            default:
                break;
        }
        // Draw hearts representing Houyi's health in stages where Houyi plays
        if (stage == 4 || stage == 5) {
            for (int i = 0; i < houyi.health; i++) {
                houyi.hearts[i].draw(); // Draw heart icons based on remaining health
            }
        }
        // Draw home button
        if (stage == 4 || stage == 5 || stage == 6 || stage == 7) {
            home.draw();
        }
        // Check if Houyi falls off the screen
        if (houyi.y > 731) {
            houyi.setPosition(10, 650);
            houyi.takeDamage();
        }
    }
}
