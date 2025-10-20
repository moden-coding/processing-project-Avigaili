import processing.core.*;

public class App extends PApplet{
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        background(238, 199, 54);
        fill(255); 
        noStroke();
        rectMode(CENTER); 
        rect(width / 2, height / 2, 800, 600); 
        stroke(0);
        strokeWeight(5);
        
    }

    public void settings(){
         size(1000, 800); 
        
    }

    public void draw(){
        

    }
    public void mouseDragged() {
        line(pmouseX, pmouseY, mouseX, mouseY);
    }
}
