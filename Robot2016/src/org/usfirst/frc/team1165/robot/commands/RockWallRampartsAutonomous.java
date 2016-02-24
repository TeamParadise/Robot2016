package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RockWallRampartsAutonomous extends CommandGroup
{

	public RockWallRampartsAutonomous()
	{
		addSequential(new DriveToDefense());
		addSequential(new DriveOverRockWall());
	}
}