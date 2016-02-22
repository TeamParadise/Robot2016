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
   }
    public void initDefaultCommand() 
    {
        setDefaultCommand(new DriveArmWithGamepad());
    }
    public void moveArm()
    {
    	if(Math.abs(Robot.oi.gamepad.getY(Hand.kRight))>0.1)
    	armMotor.set(Robot.oi.gamepad.getY(Hand.kRight));
    	else
        	armMotor.set(0);
    }
}

