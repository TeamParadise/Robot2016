package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportLinearActuatorPosition;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Provides current position of linear actuator using an AnalogPotentiometer.
 */
public class LinearActuatorSensor extends Subsystem
{
	private AnalogPotentiometer pot;
	
	// When applied to the analog potentiometer, the scale
	// and offset get us a range of from 0 to 6 inches.
	private final double scale = 12.75;
	private final double offset = -0.02;
	
	// Keep track of min and max positions: 
	private double minPosition;
	private double maxPosition;
	
	public LinearActuatorSensor()
	{
		pot = new AnalogPotentiometer(RobotMap.linearActuatorSensorPort, scale, offset);
		resetMinMax();
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new ReportLinearActuatorPosition());
	}
	
	public double getPosition()
	{
		return pot.get();
	}
	
	public AnalogPotentiometer getPot()
	{
		return pot;
	}
	
	public void resetMinMax()
	{
		synchronized(this)
		{
			minPosition = Double.MIN_VALUE;
			maxPosition = Double.MAX_VALUE;
		}
	}
	
	public void reportPosition()
	{
		synchronized (this)
		{
			double position = getPosition();
			minPosition = Math.min(minPosition, position);
			maxPosition = Math.max(maxPosition, position);
			SmartDashboard.putNumber(RobotMap.linearActuatorSensorKey, position);
			SmartDashboard.putNumber(RobotMap.linearActuatorSensorMinKey, minPosition);
			SmartDashboard.putNumber(RobotMap.linearActuatorSensorMaxKey, maxPosition);
			
			SmartDashboard.putNumber("Error", Robot.linearActuator.getPIDController().getError());
			SmartDashboard.putNumber("Average Error", Robot.linearActuator.getPIDController().getAvgError());
			SmartDashboard.putBoolean("On Target", Robot.linearActuator.onTarget());
		}
	}
}
