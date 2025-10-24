import processing.core.*;

public class App extends PApplet {
    int scene = 1;
    int millis = 0;
    


    

    public static void main(String[] args) {
        PApplet.main("App");

    }

    public void setup() {
        background(238, 199, 54);
        if (scene >= 2){
        fill(255);
        noStroke();
        rectMode(CENTER);
        rect(width / 2, height / 2, 800, 600);
        stroke(0);
        }
    }

    public void settings() {
        size(1000, 800);
       
    }

    public void mouseDragged() {
       
        if (scene == 2) {
          

            line(pmouseX, pmouseY, mouseX, mouseY);
        }
        else if(scene == 3){
            
        }

    }

    public void draw() {

        if (scene == 1) {
            textSize(60);
            textAlign(CENTER, CENTER);
            text("draw in under 20 seconds", 450, 50);
            text("PRESS UP KEY TO START", 450, 750);
        } else if (scene == 2) {
           
        line(pmouseX, pmouseY, mouseX, mouseY);

            
              println(20-millis());
          
           


            strokeWeight(5);
            textSize(60);
            textAlign(CENTER, CENTER);

        }
        else if (scene ==3){
            noStroke();
             textSize(60);
            textAlign(CENTER, CENTER);
            text("What is it?", 450, 50);
            rectMode(CENTER);
        rect(width / 2, height / 2, 800, 600);
        stroke(0);
        }
       

        
    }

    public void keyPressed() {
        if (keyCode == UP) {
            scene++;
        }
    }

}
