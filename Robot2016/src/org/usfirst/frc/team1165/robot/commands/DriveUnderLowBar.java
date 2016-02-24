package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveUnderLowBar extends CommandGroup
{

	public DriveUnderLowBar()
	{
		addSequential(new  SetLinearActuatorSetpoint(5.5),1);
		addSequential(new PrepareForLowBar());
		//addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED,RobotMap.BRAKE_OFFSET,180,RobotMap.ROTATE_CREEP_SPEED));
		addSequential(new DriveStraight(0.75, 1.5));
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED,RobotMap.BRAKE_OFFSET,-180,RobotMap.ROTATE_CREEP_SPEED));
	}
}