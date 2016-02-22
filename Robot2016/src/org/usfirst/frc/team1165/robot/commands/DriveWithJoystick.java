package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoystick extends Command {

    public DriveWithJoystick() 
    {
    	requires(Robot.robotDrive);
    }

    protected void initialize() 
    {
    }

    protected void execute()
    {
    	if(Robot.oi.driveForward)
    	{
    		Robot.robotDrive.canTalon4.setInverted(true);
    		Robot.robotDrive.canTalon5.setInverted(true);
    		Robot.robotDrive.canTalon6.setInverted(true);
    		Robot.robotDrive.canTalon7.setInverted(true);
    	}
    	else
    	{
    		Robot.robotDrive.canTalon4.setInverted(false);
    		Robot.robotDrive.canTalon5.setInverted(false);
    		Robot.robotDrive.canTalon6.setInverted(false);
    		Robot.robotDrive.canTalon7.setInverted(false);
    	}
    	Robot.robotDrive.arcadeDrive();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
