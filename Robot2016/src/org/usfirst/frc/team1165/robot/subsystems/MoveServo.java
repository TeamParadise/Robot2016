package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.ServoPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveServo extends Subsystem {
    
	public static Servo servo1;
	public static double maxAngle = 75;
	public MoveServo()
	{
		servo1 = new Servo(0);
	}
    public void initDefaultCommand() 
    {
    	setDefaultCommand(new ServoPosition());
    }
    public boolean protectServo()
    //make sure that if the wheels are going in or we are less than 3500 rpm out
    //that we don't let the servo out so the ball can't break it
    {
    	boolean retract = Robot.shooter.rightWheel.getSpeed() < 3500;
    	if (retract) servo1.setAngle(maxAngle);
    	return retract;
    }
    /*public void angle(Joystick stick)
    {
    	double angle=Robot.shooter.rightWheel.getSpeed() >=3500 && Robot.oi.ServoButton.get()? 0 : maxAngle;
       	servo1.setAngle(angle);
    }*/
    public void push()
    {
    	if (!protectServo()) servo1.setAngle(0);
    }
    public void idle()
    {
    	servo1.setAngle(maxAngle);
    }
    public double getAngle()
    {
    	return servo1.getAngle();
    }
    public boolean servoExtended()
    {
    	return servo1.getAngle() == 0;
    }
    public void report()
    {
    	SmartDashboard.putNumber("Servo Value", getAngle());
    }
}