package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Autonomous {
	String autoSelected = "leftAuto";
	long startTime;
	boolean reachedGoal = false;
	boolean resetEncoder = false;

	public void runAuto(double lEnc, double rEnc) {
		if (autoSelected.equals("middleAuto")) {
			middleAuto(lEnc, rEnc, 5000);
		} else if (autoSelected.equals("rightAuto")) {
<<<<<<< HEAD
			rightAuto(lEnc, rEnc, 0, 20, 30);
		} else if (autoSelected.equals("leftAuto")) {
			leftAuto(lEnc, rEnc, 0, 30, 20);
=======
			rightAuto(lEnc, rEnc, 0, 26, 34);
		} else if (autoSelected.equals("leftAuto")) {
			leftAuto(lEnc, rEnc, 0, 34, 26);
>>>>>>> e56df314b74c0f18ac7d082ca69c230d23250cfc
		} else {
			Motors.leftSpark.set(0);
			Motors.rightSpark.set(0);
		}
	}

	double rightSpeed;
	double leftSpeed;
	int iter = 0;
	double prevError = 0;
	double sumError = 0;
	double kpForward = 10;
	double kiForward = 0.1;
	double kdForward = 0.03;
	double pauseTime = 2;
	double averEnc = 0;
	boolean reachGoal = false;
	boolean forward = true;
	boolean isturn = false;
	int maxIter = 60;

	public void AutoVarInit() {
		prevError = 0;
		sumError = 0;
		averEnc = 0;
		reachGoal = false;
		forward = true;
		isturn = false;
		resetEncoder = true;
		iter = 0;
	}

	public void TimerInit() {
		startTime = System.currentTimeMillis();
	}

	public void PIDStraight(double lEnc, double rEnc, boolean forward, double kp, double ki, double kd) {

		double error = (lEnc - rEnc) / 1000; // scaling down the values to make them easier to
												// interpret
		double diffError = error - prevError;// previous errors
		sumError = sumError + error;// sum of all the errors
		double inputValue = kp * error + ki * sumError + kd * diffError; // PID equation

		leftSpeed = .8 * leftSpeed - inputValue / 2; // 80% speed
		rightSpeed = .8 * rightSpeed + inputValue / 2;

		if (forward == true) {
			if (leftSpeed < 0) { // eliminates error where speed goes below zero
				leftSpeed = 0;
			}
			if (rightSpeed < 0) { // eliminates error where speed goes below zero
				rightSpeed = 0;
			}
		} else {
			if (leftSpeed > 0) {
				leftSpeed = 0;
			}
			if (rightSpeed > 0) {
				rightSpeed = 0;
			}
		}

		prevError = error;
		Motors.leftSpark.set(leftSpeed);
		Motors.rightSpark.set(-rightSpeed);
	}

	public void middleAuto(double lEnc, double rEnc, double straightdistance) {
		// move straight no turn
		averEnc = Math.abs(lEnc + rEnc) / 2;
		if (reachGoal) {// runs third
			if (System.currentTimeMillis() - startTime < pauseTime * 1000) {// pause
				Motors.leftSpark.set(0);
				Motors.rightSpark.set(0);
			} else {
				reachGoal = false;
				forward = false;
				resetEncoder = true;
			}
		} else { // runs first
			if (forward) {
				if (averEnc < straightdistance) {
					leftSpeed = 1 - .7 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2);
					rightSpeed = 1 - .7 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2);
					PIDStraight(lEnc, rEnc, true, kpForward, kiForward, kdForward);

				} else {// runs second
					reachGoal = true;
					TimerInit();
				}
			} else {// backward runs fourth because forward = false
				if (averEnc < straightdistance) {
					leftSpeed = -(1 - .7 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2));
					rightSpeed = -(1 - .7 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2));
					PIDStraight(lEnc, rEnc, false, kpForward, kiForward, kdForward);

				} else {
					Motors.leftSpark.set(0);
					Motors.rightSpark.set(0);
				}
			}
		}
	}

	public void rightAuto(double lEnc, double rEnc, double straightdistance, double leftDistance,
			double rightDistance) {
		// move straight no turn
				averEnc = Math.abs(lEnc + rEnc) / 2;
				if (reachGoal) {
					if (System.currentTimeMillis() - startTime < pauseTime * 1000) {
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);
					} else {
						reachGoal = false;
						forward = false;
						resetEncoder = true;
						isturn = true;
						iter = 0;
					}
				} else {
					if (forward) {
						if (isturn == false) {
							if (averEnc < straightdistance) {
								leftSpeed = 0.85 - .5 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2);
								rightSpeed = 0.85 - .5 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2);
								PIDStraight(lEnc, rEnc, true, kpForward, kiForward, kdForward);

							} else {
								resetEncoder = true;
								isturn = true;
								Motors.leftSpark.set(0);
								Motors.rightSpark.set(0);
							}
						} else {
<<<<<<< HEAD
							leftSpeed = 0.5;
=======
							leftSpeed = 0.4;
>>>>>>> e56df314b74c0f18ac7d082ca69c230d23250cfc
							rightSpeed = 0.6;
							if (iter < maxIter && Math.abs(lEnc) < leftDistance && Math.abs(rEnc) < rightDistance) {
								Motors.leftSpark.set(leftSpeed);
								Motors.rightSpark.set(-rightSpeed);
							} else if (iter < maxIter && Math.abs(lEnc) < leftDistance) {
								Motors.leftSpark.set(leftSpeed);
								Motors.rightSpark.set(0);
							} else if (iter < maxIter && Math.abs(rEnc) < rightDistance) {
								Motors.leftSpark.set(0);
								Motors.rightSpark.set(-rightSpeed);
							} else if (iter < maxIter) {
								iter += 1;
								resetEncoder = true;
							} else {
								reachGoal = true;
								TimerInit();
							}
							System.out.println(lEnc + "|" + rEnc);

						}
					} else {
						if (isturn == true) {
<<<<<<< HEAD
							leftSpeed = -0.5;
=======
							leftSpeed = -0.4;
>>>>>>> e56df314b74c0f18ac7d082ca69c230d23250cfc
							rightSpeed = -0.6;
							if (iter < maxIter && Math.abs(lEnc) < leftDistance && Math.abs(rEnc) < rightDistance) {
								Motors.leftSpark.set(leftSpeed);
								Motors.rightSpark.set(-rightSpeed);
							} else if (iter < maxIter && Math.abs(lEnc) < leftDistance) {
								Motors.leftSpark.set(leftSpeed);
								Motors.rightSpark.set(0);
							} else if (iter < maxIter && Math.abs(rEnc) < rightDistance) {
								Motors.leftSpark.set(0);
								Motors.rightSpark.set(-rightSpeed);
							} else if (iter < maxIter) {
								iter += 1;
								resetEncoder = true;
							} else {

								resetEncoder = true;
								isturn = false;
								Motors.leftSpark.set(0);
								Motors.rightSpark.set(0);

							}

						} else {
							if (averEnc < straightdistance) {
								leftSpeed = -(0.85 - .5 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2));
								rightSpeed = -(0.85 - .5 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2));

								PIDStraight(lEnc, rEnc, false, kpForward, kiForward, kdForward);

							} else {
								Motors.leftSpark.set(0);
								Motors.rightSpark.set(0);

							}
						}

					}
				}
			}
	public void leftAuto(double lEnc, double rEnc, double straightdistance, double leftDistance, double rightDistance) {
		// move straight no turn
		averEnc = Math.abs(lEnc + rEnc) / 2;
		if (reachGoal) {
			if (System.currentTimeMillis() - startTime < pauseTime * 1000) {
				Motors.leftSpark.set(0);
				Motors.rightSpark.set(0);
			} else {
				reachGoal = false;
				forward = false;
				resetEncoder = true;
				isturn = true;
				iter = 0;
			}
		} else {
			if (forward) {
				if (isturn == false) {
					if (averEnc < straightdistance) {
						leftSpeed = 0.85 - .5 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2);
						rightSpeed = 0.85 - .5 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2);
						PIDStraight(lEnc, rEnc, true, kpForward, kiForward, kdForward);

					} else {
						resetEncoder = true;
						isturn = true;
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);
					}
				} else {
					leftSpeed = 0.6;
<<<<<<< HEAD
					rightSpeed = 0.5;
=======
					rightSpeed = 0.4;
>>>>>>> e56df314b74c0f18ac7d082ca69c230d23250cfc
					if (iter < maxIter && Math.abs(lEnc) < leftDistance && Math.abs(rEnc) < rightDistance) {
						Motors.leftSpark.set(leftSpeed);
						Motors.rightSpark.set(-rightSpeed);
					} else if (iter < maxIter && Math.abs(lEnc) < leftDistance) {
						Motors.leftSpark.set(leftSpeed);
						Motors.rightSpark.set(0);
					} else if (iter < maxIter && Math.abs(rEnc) < rightDistance) {
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(-rightSpeed);
					} else if (iter < maxIter) {
						iter += 1;
						resetEncoder = true;
					} else {
						reachGoal = true;
						TimerInit();
					}
					System.out.println(lEnc + "|" + rEnc);

				}
			} else {
				if (isturn == true) {
					leftSpeed = -0.6;
<<<<<<< HEAD
					rightSpeed = -0.5;
=======
					rightSpeed = -0.4;
>>>>>>> e56df314b74c0f18ac7d082ca69c230d23250cfc
					if (iter < maxIter && Math.abs(lEnc) < leftDistance && Math.abs(rEnc) < rightDistance) {
						Motors.leftSpark.set(leftSpeed);
						Motors.rightSpark.set(-rightSpeed);
					} else if (iter < maxIter && Math.abs(lEnc) < leftDistance) {
						Motors.leftSpark.set(leftSpeed);
						Motors.rightSpark.set(0);
					} else if (iter < maxIter && Math.abs(rEnc) < rightDistance) {
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(-rightSpeed);
					} else if (iter < maxIter) {
						iter += 1;
						resetEncoder = true;
					} else {

						resetEncoder = true;
						isturn = false;
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);

					}

				} else {
					if (averEnc < straightdistance) {
						leftSpeed = -(0.85 - .5 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2));
						rightSpeed = -(0.85 - .5 / (straightdistance / 2) * Math.abs(averEnc - straightdistance / 2));

						PIDStraight(lEnc, rEnc, false, kpForward, kiForward, kdForward);

					} else {
						Motors.leftSpark.set(0);
						Motors.rightSpark.set(0);

					}
				}

			}
		}
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> e56df314b74c0f18ac7d082ca69c230d23250cfc
