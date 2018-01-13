
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
	double prevError = 0;
	double sumError = 0;
	
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
			sumError = 0;
			prevError = 0;
		} 
		else 
		{
//			Motors.leftMotor.set(leftSpeed);
//			Motors.rightMotor.set(rightSpeed);
			runPIDStraight();
			Turn();
		}
		

	}

	public void runPIDStraight() {
		double kp = 0.015;
		double ki = 0.005;
		double kd = 0.095;
		double error = leftEncoderValue - rightEncoderValue;
		double diffError = error - prevError;
		sumError = sumError + error;
		
		double inputValue = kp * error + ki * sumError + kd * diffError;
		Motors.leftMotor.set(.8*leftSpeed - inputValue/2);
		Motors.rightMotor.set(.8*-rightSpeed - inputValue/2);
		prevError = error;
	}
	public void Turn() {
		double c = .8;
		if(rightJoystickValueX > 0) {
			
			Motors.leftMotor.set(c*leftSpeed);
			Motors.rightMotor.set(c*rightSpeed);
		}
		else {
			Motors.leftMotor.set(c*-leftSpeed);
			Motors.rightMotor.set(c*-rightSpeed);
		}
		
	}
	/*if ((Math.abs(leftEncoderValue - rightEncoderValue) < 6)) 
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
			Motors.rightMotor.set(-rightSpeed);
		} 
		else 
		{
//			Motors.leftMotor.set(leftSpeed + .5);
//			Motors.rightMotor.set(rightSpeed - .5);
//			leftSpeed = leftJoystickValueY;
//			rightSpeed =  rightJoystickValueY;
			Motors.leftMotor.set(leftSpeed);
			Motors.rightMotor.set(-rightSpeed + .4 );
		}
	
*/

	public void runPIDBackward() {
		double kp = 0.1;
		double ki = 0.005;
		double kd = 0.05;
		double error = leftEncoderValue - rightEncoderValue;
		double diffError = error - prevError;
		sumError = sumError + error;
		
		double inputValue = kp * error + ki * sumError + kd * diffError;
		Motors.leftMotor.set(leftSpeed - inputValue/2);
		Motors.rightMotor.set(-rightSpeed - inputValue/2);
		prevError = error;
	}
/*
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
	*/

	// This is the function that is called during the test
	// Test is an option available in the driver station and can be used to test
	// specific pieces of code.
	// This function runs periodically, meaning it acts like an infinite loop

	public void testPeriodic() {

	}
}