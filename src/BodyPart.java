import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;
import java.lang.Math; 

public class BodyPart {
	
	private PApplet sketch;
	private Mover[] m;
	private Random random = new Random();
	public BodyPart (PApplet sketch, int size) {
		this.sketch = sketch;
		m = new Mover[size];
		initiateMovers();
	}
	
	public Mover[] getMoverArray(){
		return m;
	}
	
	private void initiateMovers() {
		for(int i = 0; i < m.length; i++) {
			double width =  -1.5 + random.nextDouble() * (1.5 - (-1.5));
			double height = -1.5 + random.nextDouble() * (1.5 - (-1.5));
			m[i] = new Mover(sketch,(float)width, (float)height);
		}
	}
	
	public void updateMovers(PVector v1, PVector v2) {
		for (int i=0; i < m.length; i++) {
			if (v1 != null & v2 != null) {
				//get the closest point on the line between v1 and v2 to the current loc of m[i]
				PVector v = getDistance(v1.x, v1.y, v2.x, v2.y, m[i].getLocation().x, m[i].getLocation().y);
				m[i].update(v.x, v.y);
			}
			 if (v1 != null) {
				m[i].update(v1.x, v1.y);
			}
			else if (v2 != null) {
				m[i].update(v2.x, v2.y);
			}
			else {
				float r1 = -1 + random.nextFloat() * (1 - (-1)); //generate random number between 1 and -1 
				float r2 = -1 + random.nextFloat() * (1 - (-1)); //generate random number between 1 and -1 
				m[i].updateRandom(new PVector(r1, r2), 1);
			}
			m[i].checkEdges((float)2.5, (float)2.5); //2.5 is the size of screen
			m[i].display();
	}
		
	}
	
	private PVector getDistance( float x1, float y1, float x2, float y2, float x, float y ){
		  PVector result = new PVector(); 
		  float dx = x2 - x1; 
		  float dy = y2 - y1; 
		  float d = (float) Math.sqrt(dx*dx + dy*dy ); 
		  float ca = dx/d; // cosine
		  float sa = dy/d; // sine 
		  float mX = (-x1+x)*ca + (-y1+y)*sa; 
		  
		  if( mX <= 0 ){
		    result.x = x1; 
		    result.y = y1; 
		  }
		  else if( mX >= d ){
		    result.x = x2; 
		    result.y = y2; 
		  }
		  else{
		    result.x = x1 + mX*ca; 
		    result.y = y1 + mX*sa; 
		  }
		  
		  dx = x - result.x; 
		  dy = y - result.y; 
		  result.z = (float) Math.sqrt( dx*dx + dy*dy ); 
		 // System.out.println("Getting distance");
		  return result;   
	}
	
	public void updateMovers(PVector v1) {
		for (int i=0; i < m.length; i++) {
//			if (handRight!= null && averageArm != null) {
//			m[i].update(averageArm.x, averageArm.y);
//			}
//			if (v1 != null & v2 != null) {
//				m[i].update(v1, v2);
//			}
			 if (v1 != null) {
				m[i].update(v1.x, v1.y);
			}
//			else if (v2 != null) {
//				m[i].update(v2.x, v2.y);
//			}
			else {
				float r1 = -1 + random.nextFloat() * (1 - (-1)); //generate random number between 1 and -1 
				float r2 = -1 + random.nextFloat() * (1 - (-1)); //generate random number between 1 and -1 
				m[i].updateRandom(new PVector(r1, r2), 1);
			}
			m[i].checkEdges((float)2.5, (float)2.5); //2.5 is the size of screen
			m[i].display();
	}
	}
}
