package team3647robotPackage;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// The speed of the robot while running the program
	double leftSpeed = 0.4;
	double rightSpeed = 0.4;
	double distance = 1400;
	double adjustment = 0.005;
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
		if (Math.abs(rightEncoderValue - leftEncoderValue) < 5) {
			rightSpeed = .4;
			leftSpeed = .4;
		} else {
			if (rightEncoderValue > leftEncoderValue) {
				rightSpeed -= adjustment;
				leftSpeed += adjustment;
			} else {
				rightSpeed += adjustment;
				leftSpeed -= adjustment;
			}
		}
		if (rightEncoderValue / 2 + leftEncoderValue / 2 < distance) {
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(-rightSpeed);
		} else {
			Motors.leftMotor.set(0);
			Motors.rightMotor.set(0);
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
