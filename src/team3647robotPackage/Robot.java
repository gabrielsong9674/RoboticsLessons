package team3647robotPackage;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot 
{	
	//This function is run whenever the robot starts. This function is used for any initialization of code
	Encoders encoderObject;
	public void robotInit() 
	{
encoderObject = new Encoders();
	}

	 //This function runs once, right before autonomous period starts. 
	
	public void autonomousInit() 
	{

	}

	//This is the function that is called during the autonomous period
	//This function runs periodically, meaning it acts as an infinite loop
	
	public void autonomousPeriodic() 
	{
		double leftEncoderValue = encoderObject.getLeftEncoder();
		double rightEncoderValue = encoderObject.getRightEncoder();
		goStraight();
	}
	public void goStraight() {
		if(Joysticks.leftJoySticky > .15) {
			Motors.leftMotor.set(.5);
			Motors.rightMotor.set(-.5);
		}
		else if(Joysticks.leftJoySticky < -.15) {
			Motors.leftMotor.set(-.5);
			Motors.rightMotor.set(.5);
		}
		else
		{
			Motors.leftMotor.set(0);
			Motors.rightMotor.set(0);
		}
		
	}

	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	
	public void teleopPeriodic() 
	{
		
	}

	//This is the function that is called during the test
	//Test is an option available in the driver station and can be used to test specific pieces of code.
	//This function runs periodically, meaning it acts like an infinite loop
	
	public void testPeriodic() 
	{
		
	}
}

