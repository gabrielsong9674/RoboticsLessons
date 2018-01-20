package team3647robotPackage;

import edu.wpi.first.wpilibj.Encoder;

public class Encoders {

	public double getRightEncoder() {
		return Motors._frontLeftMotor.getSensorCollection().getQuadraturePosition();
	}

	public double getLeftEncoder() {
		return -Motors._frontRightMotor.getSensorCollection().getQuadraturePosition();
	}

	public void resetEncoders() {
		Motors._frontLeftMotor.getSensorCollection().setQuadraturePosition(0, 10);
		Motors._frontRightMotor.getSensorCollection().setQuadraturePosition(0, 10);
	}
}