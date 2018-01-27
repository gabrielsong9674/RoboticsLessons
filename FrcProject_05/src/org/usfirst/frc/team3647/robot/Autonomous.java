package org.usfirst.frc.team3647.robot;

import java.util.Date;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Autonomous {
	String autoSelected = "middleAuto";
	long startTime = System.currentTimeMillis();
	boolean reachedGoal = false;
	
	
	
	public void runAuto(double lEnc, double rEnc) {
		if (autoSelected.equals("middleAuto")) {
			middleAuto(lEnc, rEnc);
		} else if (autoSelected.equals("rightAuto")) {
			rightAuto(lEnc, rEnc);
		} else if (autoSelected.equals("leftAuto")) {
			leftAuto(lEnc, rEnc);
		} else {
			Motors.leftSRX.set(0);
			Motors.rightSRX.set(0);
		}
	}
	public void example
	{
//		Motors.leftSRX.set(0);
//		Motors.rightSRX.set(0);
		
		Motors.drive.tankDrive(leftSpeed, rightSpeed, false);
	}
	double leftEncoderValue;
	double rightEncoderValue;
	double rightSpeed;
	double leftSpeed;
	double speed;
	double prevError = 0;
	double sumError = 0;
	double kp = 0.2;
	double ki = 0.01;
	double kd = .1;
	double turnForward;
	double turnBackward;
	double forwardTime =5;
	double backwardTime =5;

	public void runPIDforward() {
		double error = (leftEncoderValue - rightEncoderValue - turnForward) / 1000; // scaling down the values to make them easier to
																		// interpret
		double diffError = error - prevError;// previous errors
		sumError = sumError + error;// sum of all the errors
		double inputValue = kp * error + ki * sumError + kd * diffError; // PID equation

		leftSpeed = .8 * leftSpeed - inputValue / 2; // 80% speed
		rightSpeed = .8 * rightSpeed + inputValue / 2;
		if (leftSpeed < 0) { // eliminates error where speed goes below zero
			leftSpeed = 0;
		}
		if (rightSpeed < 0) { // eliminates error where speed goes below zero
			rightSpeed = 0;
		}

		prevError = error; // to calculate all errors
		Motors.drive.tankDrive(leftSpeed, rightSpeed, false);
		
	}

	public void runPIDbackward() {
		double error = (leftEncoderValue - rightEncoderValue - turnBackward) / 1000;
		double diffError = error - prevError;
		sumError = sumError + error;
		double inputValue = kp * error + ki * sumError + kd * diffError;

		leftSpeed = .8 * leftSpeed - inputValue / 2;
		rightSpeed = .8 * rightSpeed + inputValue / 2;

		if (leftSpeed > 0) {
			leftSpeed = 0;
		}
		if (rightSpeed > 0) {
			rightSpeed = 0;
		}
		Motors.drive.tankDrive(leftSpeed, rightSpeed, false);
		prevError = error;
	}
	
	
	
	public void middleAuto(double lEnc, double rEnc) {
		// move straight no turn
		leftSpeed = .3;
		rightSpeed = .3;
		turnForward = 0;
		turnBackward = 0;
		if(reachedGoal == false) {
			if ((new Date()).getTime() - startTime<5*60*1000){
				runPIDforward();
			}
			else {
				reachedGoal = true;
				startTime = System.currentTimeMillis();
			}
		}
		else {
			if((new Date()).getTime() - startTime<5*60*1000) {
				runPIDbackward();
			}
		}	
}
	
	public void rightAuto(double lEnc, double rEnc) {
		// move straight turn right
	leftSpeed = .3;
	rightSpeed = .3;
	turnForward = 5;
	turnBackward = 5;
		if(reachedGoal == false) {
			if ((new Date()).getTime() - startTime<forwardTime*60*1000){
				runPIDforward();
			}
			else {
				reachedGoal = true;
				startTime = System.currentTimeMillis();
			}
		}
		else {
			if((new Date()).getTime() - startTime<backwardTime*60*1000) {
				runPIDbackward();
			}
		}	

			
		
	}

	public void leftAuto(double lEnc, double rEnc) {
		// move straight turn left
		leftSpeed = .3;
		rightSpeed = .3;
		turnForward = -5;
		turnBackward = -5;
			if(reachedGoal == false) {
				if ((new Date()).getTime() - startTime<forwardTime*60*1000){
					runPIDforward();
				}
				else {
					reachedGoal = true;
					startTime = System.currentTimeMillis();
				}
			}
			else {
				if((new Date()).getTime() - startTime<backwardTime*60*1000) {
					runPIDbackward();
				}
			}	

		
	}
}
