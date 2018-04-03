package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Encoders 
{
	public double leftEncoderValue, rightEncoderValue;
	
	Encoder rightEncoder = new Encoder(2,3, false, Encoder.EncodingType.k4X);
	Encoder leftEncoder = new Encoder(0,1, false, Encoder.EncodingType.k4X);
	
	public void setEncoderValues()
	{
		leftEncoderValue = -leftEncoder.get();
		rightEncoderValue = rightEncoder.get();
	}
	
	public  void resetEncoders()
	{
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void testEncoders()
	{
		System.out.println("Left Encoder Value: " + leftEncoderValue);
		System.out.println("Right Encoder Value: " + rightEncoderValue);
	}
}
