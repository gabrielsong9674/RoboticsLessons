package org.usfirst.frc.team3647.robot;


import edu.wpi.first.wpilibj.Timer;
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
	double kp = 10;
	double ki = 0.1;
	double kd = 0.03;
	double pauseTime = 2;
	double averEnc = 0;
	double sumEnc = 0;
	double forwdist_turn = 2000;
	double backdist_turn = 2000;
	double straightdistance = 5000;
	double backwardStraightDistance = 4400;
	boolean reachGoal = false;
	boolean forward = true;
	boolean isturn= false;

	public void AutoVarInit() {
		reachGoal = false;
		forward = true;
		isturn= false;
		sumEnc = 0;
		resetEncoder = true;	
	}
	
	public void TimerInit() {
		startTime = System.currentTimeMillis();
		System.out.print("init timer");
	}
	public void runPIDforward(double lEnc, double rEnc, double turnForward) {
		double error = (lEnc - rEnc - turnForward) / 1000; // scaling down the values to make them easier to
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

	public void runPIDbackward(double lEnc, double rEnc, double turnBackward) {
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
		averEnc = Math.abs(lEnc + rEnc)/2;

		if (reachGoal) {//runs third
			if (System.currentTimeMillis() - startTime<pauseTime*1000) {//pause
				Motors.leftSpark.set(0);
				Motors.rightSpark.set(0);
				System.out.print("pause");
			}else {
				reachGoal = false;
				forward = false;
				resetEncoder = true;
			}
		}
		else { //runs first
			if (forward) {
				if (averEnc < straightdistance) {
					leftSpeed = 1-.7/(straightdistance/2)*Math.abs(averEnc-straightdistance/2);
					rightSpeed = 1-.7/(straightdistance/2)*Math.abs(averEnc-straightdistance/2);
					System.out.println("PIDForward");
					runPIDforward(lEnc, rEnc, 0);
					System.out.println("left"+ lEnc+ " right" + rEnc);
				}
				else {//runs second
					reachGoal = true;
					TimerInit();
				}
			}else {//backward runs fourth because forward = false
				if (averEnc < straightdistance) {
					leftSpeed = -(1-.7/(straightdistance/2)*Math.abs(averEnc-straightdistance/2));
					rightSpeed = -(1-.7/(straightdistance/2)*Math.abs(averEnc-straightdistance/2));
					System.out.println("PIDbackward");
					runPIDbackward(lEnc, rEnc, 0);
					System.out.println("left"+ lEnc+ " right" + rEnc);

				}
				else {
					Motors.leftSpark.set(0);
					Motors.rightSpark.set(0);
					System.out.println("left"+ lEnc+ " right" + rEnc);
				}
			}
		}
}
	
	public void rightAuto(double lEnc, double rEnc) {
		// move straight no turn
		averEnc = Math.abs(lEnc + rEnc)/2;
		double turnError = -30;//desired difference between left and right; right faster
		if (reachGoal) {
			if (System.currentTimeMillis() - startTime<pauseTime*1000) {
				Motors.leftSpark.set(0);
				Motors.rightSpark.set(0);
				System.out.print("pause");
			}else {
				reachGoal = false;
				forward = false;
				resetEncoder = true;
				isturn = true;
			}
		}
		else { 
			if (forward) {
				if(isturn == false) {
					if (averEnc < straightdistance) {
						leftSpeed = 0.8-.4/(straightdistance/2)*Math.abs(averEnc-straightdistance/2);
						rightSpeed = 0.8-.4/(straightdistance/2)*Math.abs(averEnc-straightdistance/2);
						System.out.println("PIDForward");
						runPIDforward(lEnc, rEnc, 0);
						System.out.println("left"+ lEnc+ " right" + rEnc);
					}
					else {
						resetEncoder = true;
						isturn = true;
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);
						sumEnc = 0;
					}
				}
				else {
					if(sumEnc < forwdist_turn) {
						sumEnc = sumEnc + averEnc;
						leftSpeed = 0.5;
						rightSpeed = 0.5;
						System.out.println("PIDForwardTurn");
						System.out.println("sum"+sumEnc);
						runPIDforward(lEnc, rEnc, turnError);
						resetEncoder = true;
					}
					else {
						reachGoal = true;
						TimerInit();
						sumEnc = 0;
					}
				}
			}else {
				if(isturn == true) {
					if (sumEnc < backdist_turn) {
						sumEnc = sumEnc + averEnc;
						leftSpeed = -0.5;
						rightSpeed = -0.5;
						System.out.println("PIDbackwardTurn");
						System.out.println("sum"+sumEnc);
						runPIDbackward(lEnc, rEnc, -turnError);
						resetEncoder = true;
	
					}
					else {
						resetEncoder = true;
						isturn = false;
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);
					}
				}
				else {
					if (averEnc < straightdistance) {
						leftSpeed = -(0.8-.4/(straightdistance/2)*Math.abs(averEnc-straightdistance/2));
						rightSpeed = -(0.8-.4/(straightdistance/2)*Math.abs(averEnc-straightdistance/2));
						System.out.println("PIDbackward");
						runPIDbackward(lEnc, rEnc, 0);
						System.out.println("left"+ lEnc+ " right" + rEnc);
	
					}
					else {
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);
						System.out.println("left"+ lEnc+ " right" + rEnc);
					}
				}
				
			}
		}		
	}
			
		
	public void leftAuto(double lEnc, double rEnc) {
	// move straight no turn
		
		averEnc = Math.abs(lEnc + rEnc)/2;
		

		double turnError = 30;//desired difference between left and right encoders; left faster
		if (reachGoal) {
			if (System.currentTimeMillis() - startTime<pauseTime*1000) {
				Motors.leftSpark.set(0);
				Motors.rightSpark.set(0);
				System.out.print("pause");
			}else {
				reachGoal = false;
				forward = false;
				resetEncoder = true;
				isturn = true;
			}
		}
		else { 
			if (forward) {
				if(isturn == false) {
					if (averEnc < straightdistance) {
						leftSpeed = 0.8-.4/(straightdistance/2)*Math.abs(averEnc-straightdistance/2);
						rightSpeed = 0.8-.4/(straightdistance/2)*Math.abs(averEnc-straightdistance/2);
						System.out.println("PIDForward");
						runPIDforward(lEnc, rEnc, 0);
						System.out.println("left"+ lEnc+ " right" + rEnc);
					}
					else {
						resetEncoder = true;
						isturn = true;
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);
						sumEnc = 0;
					}
				}
				else {
					if(sumEnc < forwdist_turn) {
						sumEnc = sumEnc + averEnc;
						leftSpeed = 0.5;
						rightSpeed = 0.5;
						System.out.println("PIDForwardTurn");
						System.out.println("sum"+sumEnc);
						runPIDforward(lEnc, rEnc, turnError);
						resetEncoder = true;
					}
					else {
						reachGoal = true;
						TimerInit();
						sumEnc = 0;
					}
				}
			}else {
				if(isturn == true) {
					if (sumEnc < backdist_turn) {
						sumEnc = sumEnc + averEnc;
						leftSpeed = -0.5;
						rightSpeed = -0.5;
						System.out.println("PIDbackwardTurn");
						System.out.println("sum"+sumEnc);
						runPIDbackward(lEnc, rEnc, -turnError);
						resetEncoder = true;
	
					}
					else {
						resetEncoder = true;
						isturn = false;
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);
					}
				}
				else {
					if (averEnc < straightdistance) {
						leftSpeed = -(0.8-.4/(straightdistance/2)*Math.abs(averEnc-straightdistance/2));
						rightSpeed = -(0.8-.4/(straightdistance/2)*Math.abs(averEnc-straightdistance/2));
						System.out.println("PIDbackward");
						runPIDbackward(lEnc, rEnc, 0);
						System.out.println("left"+ lEnc+ " right" + rEnc);
	
					}
					else {
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);
						System.out.println("left"+ lEnc+ " right" + rEnc);
					}
				}
				
			}
		}		
	}

}
