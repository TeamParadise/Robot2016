package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveOverDrawbridge extends CommandGroup
{

	public DriveOverDrawbridge()
	{
		addSequential(new SetLinearActuatorSetpoint(1));
		addSequential(new MoveArmToSetpoint(175));
		addSequential(new SetLinearActuatorSetpoint(5.5));
		addSequential(new DriveStraight(-0.75, 2));
	}
}
