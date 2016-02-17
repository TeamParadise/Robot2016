package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sets the set point on the linear actuator PID subsystem.
 */
public class SetLinearActuatorSetpoint extends Command
{
	private double setpoint;
	private String setpointKey;
	
	public SetLinearActuatorSetpoint()
	{
		requires(Robot.linearActuator);
	}

	/**
	 * Construct with a hard coded set point.
	 */
	public SetLinearActuatorSetpoint(double setpoint)
	{
		this();
		this.setpoint = setpoint;
	}

	/**
	 * Construct with the key of a SmartDashboard value that
	 * is used to obtain the set point.
	 */
	public SetLinearActuatorSetpoint(String setpointKey)
	{
		this();
		this.setpointKey = setpointKey;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		if (setpointKey != null)
		{
			setpoint = SmartDashboard.getNumber(setpointKey);
		}
		
		// Disable the PID controller:
		Robot.linearActuator.disable();
		
		// Reset some statistics:
		Robot.linearActuatorSensor.resetMinMax();
		Robot.linearActuator.resetSpeeds();
		
		// Set the set point in the PID controller then enable it:
		Robot.linearActuator.setSetpoint(setpoint);
		Robot.linearActuator.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// Nothing to do here, the PID controller is in control.
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		// Ask the linear actuator is we have reached the set point:

		return Robot.linearActuator.onTarget();
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.linearActuator.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
