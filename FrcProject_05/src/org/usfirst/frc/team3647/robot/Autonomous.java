package org.usfirst.frc.team3647.robot;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Autonomous {
	String autoSelected = "middleAuto";
	long startTime;
	boolean reachedGoal = false;
	boolean resetEncoder = false;
	
	
	public void runAuto(double lEnc, double rEnc) {
		if (autoSelected.equals("middleAuto")) {
			middleAuto(lEnc, rEnc);
		} else if (autoSelected.equals("rightAuto")) {
			rightAuto(lEnc, rEnc);
		} else if (autoSelected.equals("leftAuto")) {
			leftAuto(lEnc, rEnc);
		} else {
			Motors.leftSpark.set(0);
			Motors.rightSpark.set(0);
		}
	}
	
	
	double rightSpeed;
	double leftSpeed;
	double speed;
	double prevError = 0;
	double sumError = 0;
	double kp = 2;
	double ki = 0.6;
	double kd = 0.5;
	double turnForward;
	double turnBackward;
	double pauseTime = 1;
	double sumEncoder = 0;
	double distance = 2000;
	boolean reachGoal = false;
	boolean forward = true;
	
	public void AutoVarInit() {
		reachGoal = false;
		forward = true;
	}
	
	public void TimerInit() {
		startTime = System.currentTimeMillis();
		System.out.print("init timer");
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
		Motors.leftSpark.set(leftSpeed);
		Motors.rightSpark.set(-rightSpeed);
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

		prevError = error;
		Motors.leftSpark.set(leftSpeed);
		Motors.rightSpark.set(-rightSpeed);
	}
	
	
	
	public void middleAuto(double lEnc, double rEnc) {
		// move straight no turn
		sumEncoder = Math.abs(lEnc + rEnc)/2;
		turnForward = 0;
		turnBackward = 0;
		
		if (reachGoal) {
			if (System.currentTimeMillis() - startTime<pauseTime*1000) {
				Motors.leftSpark.set(0);
				Motors.rightSpark.set(0);
				System.out.print("pause");
			}else {
				reachGoal = false;
				forward = false;
				resetEncoder = true;
			}
		}
		else {
			if (forward) {
				if (sumEncoder < distance) {
					leftSpeed = 0.7-.4/(distance/2)*Math.abs(sumEncoder-distance/2);
					rightSpeed = 0.7-.4/(distance/2)*Math.abs(sumEncoder-distance/2);
					System.out.println("PIDForward");
					runPIDforward(lEnc, rEnc);
					System.out.print(sumEncoder);
				}
				else {
					reachGoal = true;
					TimerInit();
				}
			}else {
				if (sumEncoder < distance) {
					leftSpeed = -(0.7-.4/(distance/2)*Math.abs(sumEncoder-distance/2));
					rightSpeed = -(0.7-.4/(distance/2)*Math.abs(sumEncoder-distance/2));
					System.out.println("PIDbackward");
					runPIDbackward(lEnc, rEnc);
					System.out.print(sumEncoder);

				}
				else {
					Motors.leftSpark.set(0);
					Motors.rightSpark.set(0);
				}
			}
		}
}
	
	public void rightAuto(double lEnc, double rEnc) {
		// move straight turn right
	leftSpeed = .8;
	rightSpeed = .8;
	turnForward = 5;
	turnBackward = 5;
		
			if (System.currentTimeMillis() - startTime<5*1000){
				System.out.println("PIDForward");
				runPIDforward(lEnc, rEnc);
			}
			else if(System.currentTimeMillis() - startTime<(5 + 5)*1000 ) {
				System.out.println("PIDBackward");
				runPIDbackward(lEnc, rEnc);
			}
			else {
				Motors.leftSpark.set(0);
				Motors.rightSpark.set(0);
		}	
	}
			
		
	

	public void leftAuto(double lEnc, double rEnc) {
		// move straight turn left
		leftSpeed = .8;
		rightSpeed = .8;
		turnForward = -5;
		turnBackward = -5;
		if (System.currentTimeMillis() - startTime<5*1000){
			System.out.println("PIDForward");
			runPIDforward(lEnc, rEnc);
		}
		else if(System.currentTimeMillis() - startTime<(5 + 5)*1000) {
			System.out.println("PIDBackward");
			runPIDbackward(lEnc, rEnc);
		}
		else {
			Motors.leftSpark.set(0);
			Motors.rightSpark.set(0);
	}	
}
}
