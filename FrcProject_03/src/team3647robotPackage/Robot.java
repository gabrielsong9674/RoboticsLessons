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
	double speed;
	double prevError = 0;
	double sumError = 0;
	
	// This function is run whenever the robot starts. This function is used for any
	// initialization of code

	Encoders encoderObject;
	Joysticks joystickObject;

	public void robotInit() {
		encoderObject = new Encoders();
		joystickObject = new Joysticks();
		drivetrainInitialization();
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
			Motors.setLeftSpeed(0);
			Motors.setRightSpeed(0);
			sumError = 0;
			prevError = 0;
		} 
		else 
		{
//			Motors.leftMotor.set(leftSpeed);
//			Motors.rightMotor.set(rightSpeed);
			runPIDStraight();
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
		speed = .8*leftSpeed - inputValue/2;
		Motors.setLeftSpeed(speed);
		Motors.setRightSpeed(speed);
		prevError = error;
	}
	
	public void testPeriodic() {

	}
}