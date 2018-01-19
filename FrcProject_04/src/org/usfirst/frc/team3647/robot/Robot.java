package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	double leftSpeed;
	double rightSpeed;
	double rightJoystickValueX;
	double leftEncoderValue;
	double rightEncoderValue;
	// This function is run whenever the robot starts. This function is used for any
	// initialization of code
	Joysticks joystickObject;
	@Override
	public void robotInit() {
joystickObject = new Joysticks();
	}

	// This function runs once, right before autonomous period starts.
	@Override
	public void autonomousInit() {

	}

	// This is the function that is called during the autonomous period
	// This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void autonomousPeriodic() {

	}

	// This is the function that is called during the Tele-operated period
	// This function runs periodically, meaning it acts as an infinite loop
	

	@Override
	public void teleopPeriodic() {
		// TODO find out degree
		double c = 0.005;
		double turnDegree;
		if (getDegrees() > 0 && getDegrees() < 180) {// forward
			turnDegree = (getDegrees() - 90);
			leftSpeed = getMagnitude() + c * turnDegree;
			rightSpeed = -(getMagnitude() - c * turnDegree);
		} else {// backward
			turnDegree = (getDegrees() - 90);
			leftSpeed = -(getMagnitude() + c * turnDegree);
			rightSpeed = getMagnitude() - c * turnDegree;
		}
		joystickObject.updateMainController();
		if (getMagnitude() == 0) {
			Motors.leftMotor.set(0);
			Motors.rightMotor.set(0);

		} else {
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(rightSpeed);
			}
		}

	

//Then you should do
//(1) I saw you did experiment with printing the angles to the console, great. I want you to write down 8 angle values for forward, backward, right, left, forward-right, forward-left, backward-right, backward-left. Then draw the directions with corresponding angles (like a x-y coordinate) on the paper and check if those pairs make sense. Because I suspect the getDegrees function is not correct. You want to find out 		
//whether 
//                if (Joysticks.rightJoySticky == 0 && Joysticks.rightJoyStickx > 0) { //right
//			return 180;
//		}
//		if (Joysticks.rightJoySticky == 0 && Joysticks.rightJoyStickx < 0) { //left
//			return 0;
//		}
//or 
// if (Joysticks.rightJoySticky == 0 && Joysticks.rightJoyStickx > 0) {
//			return 0;
//		}
//		if (Joysticks.rightJoySticky == 0 && Joysticks.rightJoyStickx < 0) {
//			return 180;
//		}
//(2) There is still an issue with turnDegree. After you finish step (1), then check if forward is 270 or 90 degree. If forward is 270, then the range in if statement should be getDegrees() < 360 && getDegrees() > 180. If forward is 90, then the range in if statement should be getDegrees() < 180 && getDegrees() > 0. Then in case of 270 (forward), you want to calculate turnDegree=getDegrees()-270. In case of 90 (forward), you want to calculate turnDegree=getDegrees()-90. Similar calculation applies to backward.
//
//(3) You can test the robot, if you want it goes forward-left, but it goes forward-right, then you multiply -1 to the turnDegree in the forward if statement. The similar rule applies to backward.
//}

	// This is the function that is called during the test
	// Test is an option available in the driver station and can be used to test
	// specific pieces of code.
	// This function runs periodically, meaning it acts like an infinite loop
	@Override
	public void testPeriodic() {

	}

	// This function returns the angle of the vector of the right Joystick values
	// You may need to edit this function depending upon what type of controller you
	// are using
	public static double getDegrees() {
		if (Joysticks.rightJoySticky > 0 && Joysticks.rightJoyStickx > 0) // 1st quadrant
		{
			return (270 + Math.toDegrees(Math.atan(Joysticks.rightJoyStickx / Joysticks.rightJoySticky)));
		}
		if (Joysticks.rightJoySticky > 0 && Joysticks.rightJoyStickx < 0) {
			return 180 - Math.toDegrees(Math.atan(Joysticks.rightJoySticky / Joysticks.rightJoyStickx));
		}
		if (Joysticks.rightJoySticky < 0 && Joysticks.rightJoyStickx < 0) {
			return 180 - Math.toDegrees(Math.atan(Joysticks.rightJoySticky / Joysticks.rightJoyStickx));
		}
		if (Joysticks.rightJoySticky < 0 && Joysticks.rightJoyStickx > 0) {
			return -Math.toDegrees(Math.atan(Joysticks.rightJoySticky / Joysticks.rightJoyStickx));
		}
		if (Joysticks.rightJoySticky == 0 && Joysticks.rightJoyStickx > 0) {
			return 180;
		}
		if (Joysticks.rightJoySticky == 0 && Joysticks.rightJoyStickx < 0) {
			return 0;
		}
		if (Joysticks.rightJoySticky > 0 && Joysticks.rightJoyStickx == 0) {
			return 270;
		}
		if (Joysticks.rightJoySticky < 0 && Joysticks.rightJoyStickx == 0) {
			return 90;
		} else {
			return 0;
		}
	}

	// This function returns the magnitude of the vector of the right Joystick
	public static double getMagnitude() {
		return Math.sqrt(Math.pow(Joysticks.rightJoySticky, 2) + Math.pow(Joysticks.rightJoyStickx, 2));
	}

	public void testFunctions() {
		double magnitude, angle;
		magnitude = getMagnitude();
		angle = getDegrees();

		System.out.println("Magnitude: " + magnitude + "; " + "Angle: " + angle + ";");
	}
}
