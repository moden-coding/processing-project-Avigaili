
import processing.core.*;

public class App extends PApplet {
    int scene = 1;
    final int X1 = 100, Y1 = 100, X2 = 900, Y2 = 700;
    int startTime = 0;
    int timeLeft = 20000;
    String[] prompts = {
            "cat", "pillow", "tree", "house", "dog", "car"
    };
    String prompt = "";


    String guess = "";
    int triesLeft = 3;
    String correct = "";
    boolean roundOver = false;


    public static void main(String[] args) {
        PApplet.main("App");

    }


    public void setup() {
        background(238, 199, 54);
        textAlign(CENTER, CENTER);
        textSize(60);
        prompt = prompts[(int) random(prompts.length)];
    }

    public void settings() {
        size(1000, 800);

    }

    public void mouseDragged() {

        if (scene == 2 && mouseX > X1 && mouseX < X2 && mouseY > Y1 && mouseY < Y2 && pmouseX > X1 && pmouseX < X2
                && pmouseY > Y1 && pmouseY < Y2) {

            stroke(0);
            strokeWeight(5);
            line(pmouseX, pmouseY, mouseX, mouseY);
        } else if (scene == 3) {

        }

    }

    public void draw() {

        if (scene == 1) {
            textSize(60);
            background(238, 199, 54);
            textAlign(CENTER, CENTER);
            text("draw " + prompt + " in under 20 seconds", width / 2f, 50);
            text("PRESS UP KEY TO START", width / 2f, 750);
            drawBoard();
        } else if (scene == 2) {

            noStroke();
            fill(238, 199, 54);
            rectMode(CORNER);
            rect(0, 0, width, 140);
            drawBoard();
            int left = max(0, timeLeft - (millis() - startTime));
            int secs = (int)(left / 1000.0);
            fill(0);
            textSize(40);
            textAlign(CENTER, CENTER);
            text("Draw: " + prompt, width / 2f, 50);
            text("Time left: " + secs, width / 2f, 100);

        }
        if (triesLeft <= 0) {
            startGuessScene();
        } else if (scene == 3) {
            noStroke();
            fill(238, 199, 54);
            rect(0, 0, width, 220);
            rect(0, Y2 + 10, width, 160);
            drawBoard();
            fill(0);
            textSize(60);
            text("What is it?", width / 2f, 70);

            textSize(28);
            text("Type your guess and press Enter", width / 2f, 120);
            text("Tries left: " + triesLeft, width / 2f, 155);
            int boxW = 600, boxH = 52;
            int boxX = width / 2 - boxW / 2, boxY = 180;

            stroke(0);
            strokeWeight(2);
            fill(255);
            rect(boxX, boxY, boxW, boxH, 8);
            fill(0);
            textAlign(LEFT, CENTER);
            textSize(24);
            String shown = guess;
            if (!roundOver && ((millis() / 500) % 2 == 0))
                shown += "|";
            text(shown, boxX + 12, boxY + boxH / 2f);
            textAlign(CENTER, CENTER);
            text(correct, width / 2f, boxY + boxH + 30);

            if (roundOver) {
                text("Press ↑ to start a new round", width / 2f, boxY + boxH + 65);
            }
        }

    }

    public void keyPressed() {
        if (keyCode == UP) {
          startRound();
        } else if (scene == 3 && roundOver){
            startRound();
        } else if (scene ==2 ){
            
        
        }
    
    if(scene==3&&!roundOver) {
        if (key == BACKSPACE) {
            if (guess.length() > 0)
    
        } else if (key == ENTER || key == RETURN) {
            guess();
           
        } 
    }
}

void drawBoard() {
       
        rectMode(CORNER);
        noStroke();
        fill(255);
        rect(X1, Y1, X2 - X1, Y2 - Y1);
        noFill();
        stroke(0);
        strokeWeight(2);
        rect(X1, Y1, X2 - X1, Y2 - Y1);
    }

    void startRound() {
       
        background(238, 199, 54);
        drawBoard();

 
        prompt = prompts[(int) random(prompts.length)];
        startTime = millis();

       
        scene = 2;
    }

    void startGuessScene() {
        scene = 3;
        guess = "";
        triesLeft = 3;
        correct = "";
        roundOver = false;
    }

    void submitGuess() {
        String g = guess();
        String p = prompt();

        if (g.equals(p)) {
            correct = "Correct! It was \"" + prompt + "\".";
            roundOver = true;
        } else {
            triesLeft--;
            if (triesLeft > 0) {
                correct = "Try again.";
                guess = "";
            } else {
                correct = "Out of tries. It was \"" + prompt + "\".";
                roundOver = true;
            }
        }
    }
}
