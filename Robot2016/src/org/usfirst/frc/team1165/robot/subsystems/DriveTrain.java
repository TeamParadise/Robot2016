package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team1165.util.Gamepad;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem
{
	CANTalon canTalon4;
	CANTalon canTalon5;
	CANTalon canTalon6;
	CANTalon canTalon7;
	RobotDrive robotDrive;
	
   public DriveTrain()
   {
	   canTalon4 = new CANTalon(4);
	   canTalon5 = new CANTalon(5);
	   canTalon6 = new CANTalon(6);
	   canTalon7 = new CANTalon(7);
	   robotDrive = new RobotDrive(canTalon4,canTalon5,canTalon6,canTalon7);
   }

    public void initDefaultCommand() 
    {
    	setDefaultCommand(new DriveWithJoystick());
    }
    
    public void tankDrive()
    {
    	robotDrive.tankDrive(Robot.oi.gamepad, Gamepad.Axis.LEFT_Y.getValue(), Robot.oi.gamepad, Gamepad.Axis.RIGHT_Y.getValue(), false);
    }
    public void arcadeDrive()
    {
    	robotDrive.arcadeDrive(Robot.oi.leftStick,Joystick.AxisType.kY.value,Robot.oi.leftStick,Joystick.AxisType.kZ.value,true);
    }
}

