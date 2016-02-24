package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveOverTerrain extends CommandGroup
{

	public DriveOverTerrain()
	{
		addSequential(new  SetLinearActuatorSetpoint(1.0),1.2);
		addSequential(new PrepareForTerrain());
		//addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED,RobotMap.BRAKE_OFFSET,180,RobotMap.ROTATE_CREEP_SPEED));
		addSequential(new DriveStraight(0.75, 2));
		addSequential(new RotateToHeading(RobotMap.ROTATE_SPEED,RobotMap.BRAKE_OFFSET,180,RobotMap.ROTATE_CREEP_SPEED));
		}
}