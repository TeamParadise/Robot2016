package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command
{
	private double forwardSpeed;
	private String forwardSpeedKey;
	
	public DriveStraight()
	{
		requires(Robot.robotDrive);
	}

	public DriveStraight(double forwardSpeed, double time) 
	{
		super(time);
		this.forwardSpeed = forwardSpeed;
	}

	protected void initialize()
	{
		if (forwardSpeedKey != null)
		{
			forwardSpeed = SmartDashboard.getNumber(forwardSpeedKey);
		}
		
		Robot.gyroscope.resetGyro();
		//Robot.quadEncoder.reset();
	}

	protected void execute()
	{
		double twistCorrection = Robot.gyroscope.getTwistCorrection();
		Robot.robotDrive.arcadeDrive(forwardSpeed, twistCorrection);

	}
 
	protected boolean isFinished()
	{
		return isTimedOut();
	}

	protected void end()
	{
		Robot.robotDrive.arcadeDrive(0, 0);
	}

	protected void interrupted()
	{
		end();
	}
}