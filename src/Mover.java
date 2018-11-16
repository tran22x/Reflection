import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;
public class Mover {
	
		private PImage fire;
	  PVector location;
	  PVector velocity;
	  PVector acceleration;
	  float topspeed;
	  private Random random = new Random();
	  private PApplet sketch;

	  Mover(PApplet sketch, float width, float height) {
	    location = new PVector(width,height);
	    this.sketch =sketch;
	    velocity = new PVector(0,0);
	    topspeed = (float)0.07;
	    fire = sketch.loadImage("fire.png");
	  }
	  
	  public void update(PVector v1, PVector v2) {
		  float x = v1.x - v2.x;
		  float y = v1.y - v2.y;
		  update(x,y);
	  }

	  public void update(float x, float y) {
	    // Our algorithm for calculating acceleration:
	    PVector mouse = new PVector(x,y);
	    PVector dir = PVector.sub(mouse,location);  // Find vector pointing towards mouse
	    dir.normalize();     // Normalize
	    dir.mult((float)0.01);       // Scale 
	    acceleration = dir;  // Set to acceleration

	    // Motion 101!  Velocity changes by acceleration.  Location changes by velocity.
	    velocity.add(acceleration);
	    velocity.limit(topspeed);
	    location.add(velocity);
	  }

	  public void display() {
	    sketch.noStroke();
	    sketch.fill(0);
	    sketch.image(fire,location.x,location.y, 0.1f, 0.1f);
	    //sketch.ellipse(location.x,location.y,.02f,.02f);
	  }
	  
	  public void updateRandom(PVector v, float dt) {
			velocity.add(v.mult(dt));
			velocity.mult(.9f); // drag
			location.add(velocity.mult(dt));
			v.set(0,0);
		}

	  public void checkEdges(float width, float height) {

	    if (location.x > width) {
	      location.x = (float)2.5;
	    } else if (location.x < -2.5) {
	      location.x = (float)-2.5;
	    }

	    if (location.y > height) {
	      location.y = (float)2.5;
	    }  else if (location.y < -2.5) {
	      location.y = (float)-2.5;
	    }
}
}
