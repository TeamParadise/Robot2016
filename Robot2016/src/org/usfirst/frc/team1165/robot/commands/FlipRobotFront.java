package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FlipRobotFront extends CommandGroup
{

	public FlipRobotFront()
	{
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED,RobotMap.BRAKE_OFFSET,180,RobotMap.ROTATE_CREEP_SPEED));
	}
}