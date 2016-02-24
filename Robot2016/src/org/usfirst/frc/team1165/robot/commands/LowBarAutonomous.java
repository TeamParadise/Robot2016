package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAutonomous extends CommandGroup
{

	public LowBarAutonomous()
	{
		addSequential(new DriveToDefense());
		addSequential(new DriveUnderLowBar());
	}
}
