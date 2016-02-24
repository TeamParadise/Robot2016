package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class RotateToHeading extends Command
{
	private String brakeOffsetKey;
	private String targetHeadingKey;
	private String rotateMagnitudeKey;
	private String creepMagnitudeKey;
	
	private double brakeOffset;
	private double targetHeading;
	private double rotateMagnitude;
	private double creepMagnitude;

	private double currentHeading;
	private double previousHeading; 
	private boolean isCreeping;
	private double sign;
	
	private RotateToHeading()
	{
		super(RobotMap.ROTATE_TO_HEADING_TIMEOUT);
		requires(Robot.robotDrive);
	}

	public RotateToHeading(String rotateMagnitudeKey, String brakeOffsetKey, String targetHeadingKey, String creepMagnitudeKey)
	{
		this();
		this.brakeOffsetKey = brakeOffsetKey;
		this.targetHeadingKey = targetHeadingKey;
		this.rotateMagnitudeKey = rotateMagnitudeKey;
		this.creepMagnitudeKey = creepMagnitudeKey;
	}

	public RotateToHeading(double rotateMagnitude, double brakeOffset, double targetHeading, double creepMagnitude)
	{
		this();
		this.brakeOffset = Math.abs(brakeOffset);
		this.targetHeading = targetHeading;
		this.rotateMagnitude = rotateMagnitude;
		this.creepMagnitude = creepMagnitude;
	}

	public double getTargetAdjustment()
	{
		return 0;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		if (null != brakeOffsetKey)
		{
			brakeOffset = Math.abs(SmartDashboard.getNumber(brakeOffsetKey));
			targetHeading = SmartDashboard.getNumber(targetHeadingKey);
			rotateMagnitude = Math.abs(SmartDashboard.getNumber(rotateMagnitudeKey));
			creepMagnitude = Math.abs(SmartDashboard.getNumber(creepMagnitudeKey));
		}
		
		Robot.gyroscope.resetGyro();
				
		// If our target is within braking range, do not start driving, 
		// but creep to the target instead.
		isCreeping = Math.abs(targetHeading) < brakeOffset;
		
		sign = targetHeading < 0 ? -1 : 1;
		
		previousHeading = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		//SmartDashboard.putBoolean("Is creeping", isCreeping);

		// We rotate until we reach brakeRange.
		// We then reverse the motors until we come to a stop.
		// We then rotate creepily until we reach range.
		currentHeading = Robot.gyroscope.getHeading();
		
		if (Math.abs(currentHeading) < (Math.abs(targetHeading) - brakeOffset))
		{
			Robot.robotDrive.arcadeDrive(0, sign * rotateMagnitude);
		}
		else if (!isCreeping && Math.abs(previousHeading) < Math.abs(currentHeading))
		{
			Robot.robotDrive.arcadeDrive(0, -sign * rotateMagnitude);
		}
		else
		{ 
			isCreeping = true;
			Robot.robotDrive.arcadeDrive(0, sign * creepMagnitude);
		}

		previousHeading = currentHeading;
		SmartDashboard.putNumber("Current Heading", currentHeading);
		SmartDashboard.putNumber("Rotate Magnitude",rotateMagnitude);
		SmartDashboard.putNumber("Target Heading", targetHeading);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Math.abs(Robot.gyroscope.getHeading()) >= Math.abs(targetHeading) || isTimedOut();
	}
	

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.robotDrive.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}