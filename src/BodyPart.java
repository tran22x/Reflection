import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;

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
