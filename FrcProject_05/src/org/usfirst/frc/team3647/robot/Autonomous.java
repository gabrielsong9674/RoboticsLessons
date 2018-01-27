package org.usfirst.frc.team3647.robot;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Autonomous {
	String autoSelected = "middleAuto";
	long startTime;
	boolean reachedGoal = false;
	
	
	
	public void runAuto(double lEnc, double rEnc) {
		if (autoSelected.equals("middleAuto")) {
			middleAuto(lEnc, rEnc);
		} else if (autoSelected.equals("rightAuto")) {
			rightAuto(lEnc, rEnc);
		} else if (autoSelected.equals("leftAuto")) {
			leftAuto(lEnc, rEnc);
		} else {
			Motors.drive.tankDrive(0, 0, false);
		}
	}
	
	
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
	double forwardTime =2;
	double backwardTime =2;
	
	public void TimerInit() {
		startTime = System.currentTimeMillis();
	}
	public void runPIDforward(double lEnc, double rEnc) {
		double error = (lEnc - lEnc - turnForward) / 1000; // scaling down the values to make them easier to
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

	public void runPIDbackward(double lEnc, double rEnc) {
		double error = (lEnc - rEnc - turnBackward) / 1000;
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
			if (System.currentTimeMillis() - startTime<forwardTime*60*1000){
				System.out.println("PIDForward");
				runPIDforward(lEnc, rEnc);
			}
			else {
				reachedGoal = true;
				startTime = System.currentTimeMillis();
			}
		}
		else {
			if(System.currentTimeMillis() - startTime<backwardTime*60*1000) {
				System.out.println("PIDBackward");
				runPIDbackward(lEnc, rEnc);
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
			if (System.currentTimeMillis() - startTime<forwardTime*60*1000){
				System.out.println("PIDForward");
				runPIDforward(lEnc, rEnc);
			}
			else {
				reachedGoal = true;
				startTime = System.currentTimeMillis();
			}
		}
		else {
			if(System.currentTimeMillis() - startTime<backwardTime*60*1000) {
				System.out.println("PIDBackward");
				runPIDbackward(lEnc, rEnc);
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
				if (System.currentTimeMillis() - startTime<forwardTime*60*1000){
					System.out.println("PIDForward");
					runPIDforward(lEnc, rEnc);
				}
				else {
					reachedGoal = true;
					startTime = System.currentTimeMillis();
				}
			}
			else {
				if(System.currentTimeMillis() - startTime<backwardTime*60*1000) {
					System.out.println("PIDBackward");
					runPIDbackward(lEnc, rEnc);
				}
			}	

		
	}
}
