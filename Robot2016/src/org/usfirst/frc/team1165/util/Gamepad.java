package org.usfirst.frc.team1165.util;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad extends Joystick
{
	public enum Axis
	{
		LEFT_X(0),
		LEFT_Y(1),
		LEFT_TRIG(2),
		RIGHT_TRIG(3),
		RIGHT_X(4),
		RIGHT_Y(5);
		
		private int value;
		
		private Axis(int value)
		{
			this.value = value;
		}
		
		public int getValue()
		{
			return this.value;
		}
	}
	
	public enum Button
	{
		A(1),
		B(2),
		X(3),
		Y(4),
		LEFT_BUMP(5),
		RIGHT_BUMP(6),
		BACK(7),
		START(8),
		LEFT_STICK(9),
		RIGHT_STICK(10);
		
		private int value;
		
		private Button(int value)
		{
			this.value = value;
		}
		
		public int getValue()
		{
			return this.value;
		}
	}
	
	public Gamepad(int port)
	{
		super(port);
	}
	
	@Override
	public boolean getBumper(Hand hand)
	{
		Button button = hand == Hand.kLeft ? Button.LEFT_BUMP : Button.RIGHT_BUMP;
		return getRawButton(button.getValue());
		
	}
	
	@Override
	public boolean getTop(Hand hand)
	{
		Button button = hand == Hand.kLeft ? Button.LEFT_STICK : Button.RIGHT_STICK;
		return getRawButton(button.getValue());
	}
	
	@Override
	public boolean getTrigger(Hand hand)
	{
		return getTop(hand);
	}
	
	@Override
	public double getX(Hand hand)
	{
		Axis axis = hand == Hand.kLeft ? Axis.LEFT_X : Axis.RIGHT_X;
		return getRawAxis(axis.getValue());
	}
	
	@Override
	public double getY(Hand hand)
	{
		Axis axis = hand == Hand.kLeft ? Axis.LEFT_Y : Axis.RIGHT_Y;
		return getRawAxis(axis.getValue());
	}
	
	@Override
	public double getZ(Hand hand)
	{
		return 0;
	}
}
