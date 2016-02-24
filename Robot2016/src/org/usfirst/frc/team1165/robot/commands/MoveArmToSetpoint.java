package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmToSetpoint extends Command
{

	double setpoint;
	public MoveArmToSetpoint(double setpoint)
	{
		requires(Robot.arm);
		this.setpoint = setpoint;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if(setpoint>Robot.absoluteEncoder.upperLimit)
		{
			setpoint = Robot.absoluteEncoder.upperLimit;
		}
		else if(setpoint<Robot.absoluteEncoder.lowerLimit)
		{
			setpoint = Robot.absoluteEncoder.lowerLimit;
		}
		Robot.arm.moveArmToPoint(setpoint);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.arm.onPoint(setpoint);
	}

	// Called once after isFinished returns true
	protected void end()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
	}
}
