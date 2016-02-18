package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Operates the linear actuator using input from a joystick
 */
public class OperateLinearActuatorWithJoystick extends Command
{

	public OperateLinearActuatorWithJoystick()
	{
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.linearActuator);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double speed = Robot.oi.getActuatorSpeed();
		// Treat a zone around the center position as zero to prevent fluctuating
		// motor speeds when the joystick is at rest in the center position.
		Robot.linearActuator.setSpeed(Math.abs(speed) >= .1 ? speed : 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		stop();
	}
	
	private void stop()
	{
		Robot.linearActuator.setSpeed(0);
	}
}
