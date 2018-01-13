
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

	double leftJoystickValueY;
	double rightJoystickValueY;
	double rightJoystickValueX;
	double rightJoystickX;
	double leftEncoderValue;
	double rightEncoderValue;
	double rightSpeed;
	double leftSpeed;

	// This function is run whenever the robot starts. This function is used for any
	// initialization of code

	Encoders encoderObject;
	Joysticks joystickObject;

	public void robotInit() {
		encoderObject = new Encoders();
		joystickObject = new Joysticks();
	}

	// This function runs once, right before autonomous period starts.

	public void autonomousInit() {
		encoderObject.resetEncoders();
	}

	// This is the function that is called during the autonomous period
	// This function runs periodically, meaning it acts as an infinite loop

	public void autonomousPeriodic() {

	}

	// This is the function that is called during the Tele-operated period
	// This function runs periodically, meaning it acts as an infinite loop

	public void teleopPeriodic() {
		joystickObject.updateMainController();
		leftJoystickValueY = Joysticks.leftJoySticky;
		rightJoystickValueX = Joysticks.rightJoyStickx;
		leftEncoderValue = encoderObject.getLeftEncoder();
		rightEncoderValue = encoderObject.getRightEncoder();

		leftSpeed = leftJoystickValueY;
		rightSpeed = leftJoystickValueY;
		
		if (leftJoystickValueY == 0)
		{
			encoderObject.resetEncoders();
			Motors.leftMotor.set(0);
			Motors.rightMotor.set(0);
		} 
		else if (leftJoystickValueY > 0) 
		{
//			Motors.leftMotor.set(leftSpeed);
//			Motors.rightMotor.set(rightSpeed);
			runPIDForward();
		}
		else 
		{
//			Motors.leftMotor.set(-leftSpeed);
//			Motors.rightMotor.set(-rightSpeed);
			runPIDBackward();
		}

	}

	public void runPIDForward() {
		if ((Math.abs(leftEncoderValue - rightEncoderValue) < 6)) 
		{
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(-rightSpeed);
		} 
		else if (leftEncoderValue > rightEncoderValue)
		{
//			Motors.leftMotor.set(leftSpeed - .5);
//			Motors.rightMotor.set(-(rightSpeed + .5));
//			leftSpeed = leftJoystickValueY;
//			rightSpeed =  rightJoystickValueY;
			Motors.leftMotor.set(leftSpeed - .4);
			Motors.leftMotor.set(-rightSpeed);
		} 
		else 
		{
//			Motors.leftMotor.set(leftSpeed + .5);
//			Motors.rightMotor.set(rightSpeed - .5);
//			leftSpeed = leftJoystickValueY;
//			rightSpeed =  rightJoystickValueY;
			Motors.leftMotor.set(leftSpeed);
			Motors.leftMotor.set(-rightSpeed + .4 );
		}
	}

	public void runPIDBackward() {
		if ((Math.abs(leftEncoderValue - rightEncoderValue) < 6)) 
		{
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(-rightSpeed);
		}
		else if (leftEncoderValue > rightEncoderValue)
		{
			Motors.leftMotor.set(leftSpeed + .4);
			Motors.rightMotor.set(-rightSpeed);
		} 
		else 
		{
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(-rightSpeed - .4);
		}
	}

	// This is the function that is called during the test
	// Test is an option available in the driver station and can be used to test
	// specific pieces of code.
	// This function runs periodically, meaning it acts like an infinite loop

	public void testPeriodic() {

	}
}