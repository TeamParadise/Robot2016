package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.DriveArmWithGamepad;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ManipulationArm extends Subsystem
{
	CANTalon armMotor;

	public ManipulationArm()
	{
		armMotor = new CANTalon(2);
		armMotor.setInverted(true);
	}

	public void initDefaultCommand()
	{
		setDefaultCommand(new DriveArmWithGamepad());
	}

	public void moveArm()
	{
		double value = Robot.oi.gamepad.getY(Hand.kRight) * 0.2568;
		if ((Robot.absoluteEncoder.atLowerLimit() && value < 0)
				|| (Robot.absoluteEncoder.atUpperLimit() && value > 0)
				|| (Math.abs(Robot.oi.gamepad.getY(Hand.kRight)) < 0.1))
		{
			value = 0;
		}
		armMotor.set(value);
	}
	public void moveArmToPoint(double setpoint)
	{
		if(setpoint>Robot.absoluteEncoder.getCurrentValue()-15)
		{
			armMotor.set(0);
		}
		if(setpoint>Robot.absoluteEncoder.getCurrentValue()&&!Robot.absoluteEncoder.atUpperLimit())
		{
			armMotor.set(0.4);
		}
		else if(setpoint<Robot.absoluteEncoder.getCurrentValue()&&!Robot.absoluteEncoder.atLowerLimit())
		{
			armMotor.set(-0.4);
		}
	}
	public boolean onPoint(double setpoint)
	{
		return Math.abs(Robot.absoluteEncoder.getCurrentValue())-setpoint<25;
		
	}
}