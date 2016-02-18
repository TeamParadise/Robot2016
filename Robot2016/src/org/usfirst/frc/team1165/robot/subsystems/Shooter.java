package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.commands.DriveShooterWithJoysticks;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem 
{
	public CANTalon leftWheel;
    public CANTalon rightWheel;
	public Shooter()
	{
		leftWheel = new CANTalon(0);
		rightWheel = new CANTalon(1);
		leftWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftWheel.configEncoderCodesPerRev(20);

		leftWheel.setP(0.75);
		leftWheel.setI(0.01);
		leftWheel.setD(0.0);
		
		leftWheel.setCloseLoopRampRate(.01);
		
		rightWheel.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightWheel.configEncoderCodesPerRev(20);

		rightWheel.setP(0.75);
		rightWheel.setI(0.01);
		rightWheel.setD(0.0);
		
		rightWheel.setCloseLoopRampRate(.01);
	}
    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveShooterWithJoysticks());
    }
    public void driveShooter()
    {
    	if(Math.abs(Robot.oi.rightStick.getY()) > 0.1)
    	{
    		leftWheel.set(Robot.oi.rightStick.getY());
    		rightWheel.set(-Robot.oi.rightStick.getY());    		
    	}
    	else
    	{
    		leftWheel.set(0);
    		rightWheel.set(0);
    	}
    }
    public void setThrottle(double speed)
	{
    	rightWheel.setControlMode(TalonControlMode.PercentVbus.getValue());
    	rightWheel.set(speed);
    	
    	leftWheel.setControlMode(TalonControlMode.PercentVbus.getValue());
    	leftWheel.set(-speed);
	}

	public void setRpm(double rpm)
	{
		rightWheel.setControlMode(TalonControlMode.Speed.getValue());
		rightWheel.set(rpm);
		
		leftWheel.setControlMode(TalonControlMode.Speed.getValue());
		leftWheel.set(-rpm);
	}

	public void stopRpm()
	{
		//speedController.set(0);
		rightWheel.setControlMode(TalonControlMode.PercentVbus.getValue());

		leftWheel.setControlMode(TalonControlMode.PercentVbus.getValue());
	}

	public void stopThrottle()
	{
		setThrottle(0);
	}
	public void report()
	{
		SmartDashboard.putNumber("Left Wheel Cur Value", leftWheel.get());
		SmartDashboard.putNumber("Left Wheel Cur RPM", leftWheel.getSpeed());
		
		SmartDashboard.putNumber("Right Wheel Cur Value", rightWheel.get());
		SmartDashboard.putNumber("Right Wheel Cur RPM", rightWheel.getSpeed());
	}
}