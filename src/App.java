import processing.core.*;

public class App extends PApplet {
    int scene = 1;
    final int X1 = 100, Y1 = 100, X2 = 900, Y2 = 700;
    int startTime = 0;
    int timeLeft = 20000;
    String[] prompts = {
            "cat", "pillow", "tree", "house", "dog", "car"
    };
    // ^ From chatGPT so it could just be a bunch of prompts to randomly generate
    // from (lines 9-11) ^
    String prompt = "";

    String guess = "";
    // the guess of the player
    int triesLeft = 3;
    String correct = "";
    // saying if its right or wrong
    boolean roundOver = false;

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        background(238, 199, 54);
        textAlign(CENTER, CENTER);
        textSize(60);

        // This part is from chatGPT because I needed to learn how to give an output of
        // a random prompt for the player! (The line below) but its just picking s
        // prompt
        prompt = prompts[(int) random(prompts.length)];

    }

    public void settings() {
        size(1000, 800);
    }

    void message(String visibleText, int y) { // just makes it easier to make my text in the same place
        textAlign(CENTER, CENTER);
        text(visibleText, width / 2f, y);
    }

    public void mouseDragged() {

        if (scene == 2 && mouseX > X1 && mouseX < X2 && mouseY > Y1 && mouseY < Y2 && pmouseX > X1 && pmouseX < X2
                && pmouseY > Y1 && pmouseY < Y2) {

            stroke(0);
            strokeWeight(5);
            line(pmouseX, pmouseY, mouseX, mouseY);
            // This was to get it to draw only in the rectangle and when the scene is the
            // second one
        }

    }

    public void draw() {

        if (scene == 1) {
            textSize(40);
            background(238, 199, 54);
            textAlign(CENTER, CENTER);
            message("Draw a  " + prompt + " in under 20 seconds and press UP to start", 50);

            drawBoard();

            // What should happen in scene 1 ^
        } else if (scene == 2) {

            noStroke();
            fill(238, 199, 54);
            rectMode(CORNER);
            rect(0, 0, width, 140);

            int left = max(0, timeLeft - (millis() - startTime));
            int secs = (int) (left / 1000.0);

            // Dr.Moden helped explain what to do but code itself is og
            fill(255);
            textSize(40);
            textAlign(CENTER, CENTER);
            message("Draw: " + prompt, 50);
            message("Time left: " + secs, 100);

            if (left <= 0) {
                startGuessScene();
                return; // when time is up guessing starts
            }
            // What should happen in scene 2 ^
        }
        if (scene == 3) {
            noStroke();
            fill(238, 199, 54);
            rect(0, 0, width, 220);
            rect(0, Y2, width, 160);
            fill(0);
            textSize(60);
            message("What is it?", 70);
            textSize(28);
            message("Type your guess and press Enter", 120);
            message("Tries left: " + triesLeft, 155);
            int boxW = 600, boxH = 52;
            int boxX = width / 2 - boxW / 2, boxY = 180;
            stroke(0);
            strokeWeight(2);
            fill(255);
            rect(boxX, boxY, boxW, boxH, 8);
            fill(0);
            textAlign(LEFT, CENTER);
            textSize(24);
            text(guess, boxX + 12, boxY + boxH / 2f);
            textAlign(CENTER, CENTER);
            fill(238, 199, 54);
            message(correct, boxY + boxH + 30);

            // this is the code that creates the box to type and makes it known where if
            // code is correct or not is going to go and shows how many tries is left

            if (roundOver) {

                message("Press down to start a new round", boxY + boxH + 65);

            }
        } // to start a new round once round is over

    }

    public void keyTyped() {
        if (scene == 3 && !roundOver) {
            // allow the person to type letters and make it so it goes into the box
            if ((key >= 'A' && key <= 'Z') ||
                    (key >= 'a' && key <= 'z')) {
                guess += key;
            }
        }
    }

    public void keyPressed() {

        if (keyCode == UP) {
            if (scene == 1) {
                scene++;
            }

        } else if (scene == 3 && roundOver) {
            scene++;
        }

        if (scene == 3 && key == ENTER || key == RETURN) {
            if (!roundOver)
                submitGuess();
        } // allows computer to take the guess in
        if (roundOver && keyCode == DOWN) {
            nextRound();
        }
        // starts the next round

    }

    public void drawBoard() {

        rectMode(CORNER);
        noStroke();
        fill(255);
        rect(X1, Y1, X2 - X1, Y2 - Y1);
        noFill();
        noStroke();
        rect(X1, Y1, X2 - X1, Y2 - Y1);
    }

    // make the white board to draw on ^

    void startRound() {

        prompt = prompts[(int) random(prompts.length)];
        // ChatGPT from line 35 ^
        startTime = millis();
        scene = 2;
    }
    // making the new round

    void startGuessScene() {
        scene = 3;
        guess = "";
        triesLeft = 3;
        correct = "";
        roundOver = false;
    }

    void submitGuess() {
        String g = guess.trim();
        String p = prompt.trim();
        // trim basically make a new string and makes it so if user adds space for
        // example then it wont affect if its equal to prompt or not! (from processing
        // reference )

        if (g.equals(p)) {
            fill(238, 199, 54);
            correct = "Correct! It was \"" + prompt + "\"";

            roundOver = true;

        } else {

            triesLeft--;

            if (triesLeft > 0) {

                guess = "";

            } else if (triesLeft <= 3) {
                correct = "Out of tries. It was \"" + prompt + "\".";

                roundOver = true;

            }
        }

    }

    // Checking the guess with the prompt to see if they equal eachother to see if
    // then they can go to the correct screen or if they need to guess again
    // but if the person guesses three times and doesnt get it right then they get
    // to know the answer and the game moves on

    void nextRound() {
        prompt = prompts[(int) random(prompts.length)];
        // ChatGPT from line 35 ^
        scene = 1;
        background(238, 199, 54);
        drawBoard();
    } // starting a new round everytime user presses down
}
