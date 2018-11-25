import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import edu.mtholyoke.cs.comsc243.kinect.Body;
import edu.mtholyoke.cs.comsc243.kinect.KinectBodyData;
import edu.mtholyoke.cs.comsc243.kinectTCP.TCPBodyReceiver;
import processing.core.PApplet;
import processing.core.PVector;

public class Reflection extends PApplet {
	public static int PROJECTOR_WIDTH = 1024;
	public static int PROJECTOR_HEIGHT = 786;
	public static float PROJECTOR_RATIO = (float)PROJECTOR_HEIGHT/(float)PROJECTOR_WIDTH;
	TCPBodyReceiver kinectReader;
	private BodyPart mRightHand;
	private BodyPart mLeftHand;
	private BodyPart mRightShoulder;
	private BodyPart mLeftShoulder;
	private BodyPart mHead;
	private BodyPart mRightFoot;
	private BodyPart mLeftFoot;
	private BodyPart head;
	private BodyPart mLeftHip;
	private BodyPart mRightHip;
	private BodyPart mSpineShoulder;
	private BodyPart mSpineMid;
	private BodyPart mSpineBase;
	private BodyPart mRightKnee;
	private BodyPart mLeftKnee;
	private Random random = new Random();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(Reflection.class.getName());
	}
	
	public void createWindow(boolean useP2D, boolean isFullscreen, float windowsScale) {
		if (useP2D) {
			if(isFullscreen) {
				fullScreen(P2D);  			
			} else {
				size((int)(PROJECTOR_WIDTH * windowsScale), (int)(PROJECTOR_HEIGHT * windowsScale), P2D);
			}
		} else {
			if(isFullscreen) {
				fullScreen();  			
			} else {
				size((int)(PROJECTOR_WIDTH * windowsScale), (int)(PROJECTOR_HEIGHT * windowsScale));
			}
		}		
	}
	
	public void setScale(float zoom) {
		scale(zoom* width/2.0f, zoom * -width/2.0f);
		translate(1f/zoom , -PROJECTOR_RATIO/zoom );		
	}

	public void settings() {
		createWindow(true, true, .5f);
	}
	
	private void setupMovers() {
//		for(int i = 0; i < moversRight.length; i++) {
//			double width =  -1.5 + random.nextDouble() * (1.5 - (-1.5));
//			double height = -1.5 + random.nextDouble() * (1.5 - (-1.5));
//			moversRight[i] = new Mover((float)width, (float)height);
//		}
//		for(int i = 0; i < moversLeft.length; i++) {
//			double width =  -1.5 + random.nextDouble() * (1.5 - (-1.5));
//			double height = -1.5 + random.nextDouble() * (1.5 - (-1.5));
//			moversLeft[i] = new Mover((float)width, (float)height);
//		}
		mRightHand = new BodyPart(this, 5);
		mLeftHand = new BodyPart(this, 5);
		mRightShoulder = new BodyPart(this, 5);
		mLeftShoulder = new BodyPart(this, 5);
		mRightHip = new BodyPart(this, 5);
		mLeftHip = new BodyPart(this, 5);
		mHead = new BodyPart(this, 10);
		mLeftFoot = new BodyPart(this, 5);
		mRightFoot = new BodyPart(this, 5);
		mSpineShoulder = new BodyPart(this, 6);
		mSpineBase = new BodyPart(this, 6);
		mSpineMid = new BodyPart(this, 6);
		mRightKnee = new BodyPart(this,5);
		mLeftKnee = new BodyPart(this,5);
	}
	public void setup(){

		/*
		 * use this code to run your PApplet from data recorded by recorder 
		 */
		/*
		try {
			kinectReader = new KinectBodyDataProvider("test.kinect", 10);
		} catch (IOException e) {
			System.out.println("Unable to creat e kinect producer");
		}
		 */
		setupMovers();
		smooth();
		background(255);
		
		kinectReader = new TCPBodyReceiver("138.110.92.93", 8008);
		try {
			kinectReader.start();
		} catch (IOException e) {
			System.out.println("Unable to connect to kinect server");
			exit();
		}
	}
	
	public void draw(){
		setScale(.5f);
		noStroke();
		background(255,255,255);
		//fill(255,10);
		//rect(-2,(float)-1.3,4,3);
//		KinectBodyData bodyData = kinectReader.getMostRecentData();
		KinectBodyData bodyData = kinectReader.getNextData();
		if(bodyData == null) return;
		Body person = bodyData.getPerson(0);
		if(person != null){
			PVector shoulderRight = person.getJoint(Body.SHOULDER_RIGHT);
			PVector shoulderLeft = person.getJoint(Body.SHOULDER_RIGHT);
			PVector handRight = person.getJoint(Body.HAND_RIGHT);
			PVector handLeft = person.getJoint(Body.HAND_LEFT);
			PVector footLeft = person.getJoint(Body.FOOT_LEFT);
			PVector footRight = person.getJoint(Body.FOOT_RIGHT);
			PVector hipLeft = person.getJoint(Body.HIP_LEFT);
			PVector hipRight = person.getJoint(Body.HIP_RIGHT);
			PVector spineShoulder = person.getJoint(Body.SPINE_SHOULDER);
			PVector spineBase = person.getJoint(Body.SPINE_BASE);
			PVector spineMid = person.getJoint(Body.SPINE_MID);
			PVector head = person.getJoint(Body.HEAD);
			PVector kneeRight = person.getJoint(Body.KNEE_RIGHT);
			PVector kneeLeft = person.getJoint(Body.KNEE_LEFT);
			fill(255,255,255);
			noStroke();
			
			drawIfValid(handRight);
			drawIfValid(shoulderRight);
			
			//mRightHand.updateMovers(handRight);
			mLeftHand.updateMovers(handLeft, shoulderLeft);
			mLeftShoulder.updateMovers(handLeft, shoulderLeft);
			mRightHand.updateMovers(handRight, shoulderRight);
			mRightShoulder.updateMovers(handRight, shoulderRight);
			mLeftFoot.updateMovers(footLeft,hipLeft);
			mLeftHip.updateMovers(footLeft,hipLeft);
			mRightFoot.updateMovers(footRight,hipRight);
			mRightHip.updateMovers(footRight,hipRight);
			mSpineMid.updateMovers(spineShoulder, spineMid);
			mSpineShoulder.updateMovers(spineShoulder, spineMid);
			//mLeftHand.updateMovers(handLeft);
			//mRightShoulder.updateMovers(shoulderRight);
			//mLeftShoulder.updateMovers(shoulderLeft);
			//mRightHip.updateMovers(hipRight);
			//mLeftHip.updateMovers(hipLeft);
			mHead.updateMovers(head);
			//mSpineShoulder.updateMovers(spineShoulder);
			//mSpineBase.updateMovers(spineBase);
			//mSpineMid.updateMovers(spineMid);
			//mLeftFoot.updateMovers(footLeft);
			//mRightFoot.updateMovers(footRight);
			//mRightKnee.updateMovers(kneeRight);
			//mLeftKnee.updateMovers(kneeLeft);
			}
			}

	/**
	 * Draws an ellipse in the x,y position of the vector (it ignores z).
	 * Will do nothing is vec is null.  This is handy because get joint 
	 * will return null if the joint isn't tracked. 
	 * @param vec
	 */
	public void drawIfValid(PVector vec) {
		if(vec != null) {
			fill(255,0,0);
			ellipse(vec.x, vec.y, .1f,.1f);
		}

	}
}
