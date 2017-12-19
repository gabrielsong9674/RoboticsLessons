package team3647robotPackage;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	// The speed of the robot while running the program
	double leftSpeed = 0.3;
	double rightSpeed = 0.3;
	double distance = 5;
	double adjustment = 0.2;
	double leftEncoderValue;
	double rightEncoderValue;
	double speed = .3;

	// This function is run whenever the robot starts. This function is used for any
	// initialization of code

	Encoders encoderObject;

	public void robotInit() {
		encoderObject = new Encoders();
	}

	// This function runs once, right before autonomous period starts.

	public void autonomousInit() {
		encoderObject.resetEncoders();
	}

	// This is the function that is called during the autonomous period
	// This function runs periodically, meaning it acts as an infinite loop

	public void autonomousPeriodic() {

		goStraight(distance);
	}

	public void goStraight(double distance) {
		leftEncoderValue = encoderObject.getLeftEncoder();
		rightEncoderValue = encoderObject.getRightEncoder();
		if (leftEncoderValue > rightEncoderValue) {
			leftSpeed -= adjustment;
			Motors.leftMotor.set(leftSpeed);
			rightSpeed += adjustment;
			Motors.rightMotor.set(rightSpeed);
		} else if (leftEncoderValue < rightEncoderValue) {
			rightEncoderValue -= adjustment;
			leftEncoderValue += adjustment;
		} else if (leftEncoderValue == rightEncoderValue) {
			if (leftEncoderValue >= distance && rightEncoderValue >= distance) {
				Motors.leftMotor.set(0);
				Motors.rightMotor.set(0);
			} else {
				Motors.leftMotor.set(speed);
				Motors.rightMotor.set(-speed);
			}
		} 
	}

	// This is the function that is called during the Tele-operated period
	// This function runs periodically, meaning it acts as an infinite loop

	public void teleopPeriodic() {

	}

	// This is the function that is called during the test
	// Test is an option available in the driver station and can be used to test
	// specific pieces of code.
	// This function runs periodically, meaning it acts like an infinite loop

	public void testPeriodic() {

	}
}
