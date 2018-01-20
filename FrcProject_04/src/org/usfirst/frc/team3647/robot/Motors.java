package team3647robotPackage;

import edu.wpi.first.wpilibj.Spark;

public class Motors {
	public static WPI_TalonSRX _frontLeftMotor = new WPI_TalonSRX(0);
	public static WPI_TalonSRX _frontRightMotor = new WPI_TalonSRX(3);
	
	public static VictorSPX _leftSlave1 = new VictorSPX(1);
	public static VictorSPX _rightSlave1 = new VictorSPX(0);
	public static VictorSPX _leftSlave2 = new VictorSPX(2);
	public static VictorSPX _rightSlave2 = new VictorSPX(3);

	public static void setLeftSpeed(double leftSpeed) {
		_frontLeftMotor.set(speed);
		}
	public static void setRightSpeed(double rightSpeed) {
		_frontRightMotor.set(speed);
	}
}
