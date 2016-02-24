package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveUnderPortcullis extends CommandGroup
{

	public DriveUnderPortcullis()
	{
		addParallel(new SetLinearActuatorSetpoint(3.5));
		addSequential(new DriveStraight(0.5, 0.5));
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED,RobotMap.BRAKE_OFFSET, 15, RobotMap.ROTATE_CREEP_SPEED));
		addParallel(new SetLinearActuatorSetpoint(1.25));
		addSequential(new DriveStraight(0.5, 1));
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED,RobotMap.BRAKE_OFFSET, 180, RobotMap.ROTATE_CREEP_SPEED));
	}
}