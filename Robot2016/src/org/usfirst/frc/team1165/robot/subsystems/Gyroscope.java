package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ReportGyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogGyro;

public class Gyroscope extends Subsystem
{
	AnalogGyro gyro;
	final double twistPower = 0.275;
	final double smallTwistPower = 0.1;
	final double smallAngle = 1;
	final double bigAngle = 2;

	public Gyroscope()
	{
		gyro = new AnalogGyro(RobotMap.GYROSCOPE_PORT);
	}

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new ReportGyro());
	}
	public void resetGyro()
	{
		gyro.reset();
	}
	public void report()
	{
		SmartDashboard.putNumber("Gyroscope Angle", gyro.getAngle());
	}

	public double getHeading()
	{
		return gyro.getAngle();
	}

	public double getTwistCorrection()
	{
		double angle = getHeading();

		if (Math.abs(angle) > bigAngle)
		{
			return angle > 0 ? -twistPower : twistPower;
		}
		if (Math.abs(angle) < smallAngle)
		{
			return 0;
		}
		return angle > 0 ? -smallTwistPower : smallTwistPower;
	}
}