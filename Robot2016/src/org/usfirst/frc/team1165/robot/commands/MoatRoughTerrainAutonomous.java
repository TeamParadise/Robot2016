package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoatRoughTerrainAutonomous extends CommandGroup
{

	public MoatRoughTerrainAutonomous()
	{
		addSequential(new DriveToDefense());
		addSequential(new DriveOverTerrain());
	}
}
