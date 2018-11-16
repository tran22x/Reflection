import processing.core.PApplet;
import processing.core.PVector;

public class MoverDemo extends PApplet {

	

	// Creating an array of objects.
	Mover[] movers = new Mover[20];

	public void setup() {
	  
	 
	  background(255);
	}
	  // Initializing all the elements of the array
//	  for (int i = 0; i < movers.length; i++) {
//	    movers[i] = new Mover(width, height); 
//	  }
//	}
	
	public void settings() {
		size(200,200);
		smooth();
	}

//	public void draw() {
//	  noStroke();
//	  fill(255,10);
//	  rect(0,0,width,height);
//
//	  // Calling functions of all of the objects in the array.
//	  for (int i = 0; i < movers.length; i++) {
//	    movers[i].update(mouseX, mouseY);
//	    movers[i].checkEdges(width, height);
//	    movers[i].display(); 
//	  }
//	}

}
