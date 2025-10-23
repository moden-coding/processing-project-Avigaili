import processing.core.*;

public class App extends PApplet {
    int scene = 1;

    

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        background(238, 199, 54);
        fill(255);
        noStroke();
        rectMode(CENTER);
        rect(width / 2, height / 2, 800, 600);
        stroke(0);
    }

    public void settings() {
        size(1000, 800);

    }

    public void mouseDragged() {
        int startTime=0;
        startTime = frameCount;
        if (scene == 2 && frameCount>=20) {

            line(pmouseX, pmouseY, mouseX, mouseY);
        }
        else{
             textSize(60);
            textAlign(CENTER, CENTER);
            text("What is it?", 450, 50);
        }

    }

    public void draw() {

        if (scene == 1) {
            textSize(60);
            textAlign(CENTER, CENTER);
            text("draw in under 20 seconds", 450, 50);
            text("PRESS UP KEY TO START", 450, 750);
        } else if (scene == 2) {
            
            println(frameCount);
           


            strokeWeight(5);
            textSize(60);
            textAlign(CENTER, CENTER);

        }
       

        
    }

    public void keyPressed() {
        if (keyCode == UP) {
            scene++;
        }
    }

}
