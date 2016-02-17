package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.OperateLinearActuatorWithJoystick;
import org.usfirst.frc.team1165.wpilibj.command.PIDSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * PID subsystem for the AndyMark am-3076 linear actuator.
 */
public class LinearActuatorPID extends PIDSubsystem
{
	private CANTalon motor;
	
	// PID parameters
	private final static double kP = 0.85;
	private final static double kI = 0.0;
	private final static double kD = 0;
	
	// Minimum and maximum allowable set point positions:
	private final static double minPosition = 0.5;
	private final static double maxPosition = 5.75;
	
	// Minimum and maximum allowable speeds:
	private final static double minSpeed = -0.85;
	private final static double maxSpeed = 0.85;
	
	// Tolerance for reaching set point:
	private final static double tolerance = 0.16;
	
	// Keeps track of maximum and minimum speed set by the PID controller.
	private double maxSetSpeed;
	private double minSetSpeed = Double.MAX_VALUE;

	// Constructor.
	public LinearActuatorPID()
	{
		// Set the parameters of the PID controller:
		super(kP, kI, kD);
		
        setInputRange(minPosition, maxPosition);
        setOutputRange(minSpeed, maxSpeed);
		// Set ranges of PID controller:
		//setPercentTolerance(10);
        setAbsoluteTolerance(tolerance);
		
        // Construct the motor that drives the actuator.
		motor = new CANTalon(RobotMap.linearActuatorMotorChannel);
		motor.setInverted(true);

		// Let's show everything on the LiveWindow:
        LiveWindow.addActuator("Actuator", "Motor", motor);
        LiveWindow.addSensor("Actuator", "Pot", Robot.linearActuatorSensor.getPot());
        LiveWindow.addActuator("Actuator", "PID", getPIDController());
	}

	/**
	 * Set a default command that allows us to control the actuator via the
	 * joystick when it is not being controlled by the PID controller.
	 */
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new OperateLinearActuatorWithJoystick());
	}
	
	public void resetSpeeds()
	{
		maxSetSpeed = 0;
		minSetSpeed = Double.MAX_VALUE;
	}

	/**
	 * Called by the PID controller to get the current input value.
	 * Returns the current value from the analog potentiometer
	 * indicating the position of the actuator.
	 */
	protected double returnPIDInput()
	{
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return Robot.linearActuatorSensor.getPosition();
	}

	/**
	 * Sets the motor speed between -1 and 1.
	 */
	public void setSpeed(double speed)
	{
		SmartDashboard.putNumber(RobotMap.linearActuatorSpeedKey, speed);
		motor.set(-speed);
		if (Math.abs(speed) > Math.abs(maxSetSpeed))
		{
			maxSetSpeed = speed;
		}
		if (Math.abs(speed) > 0 && Math.abs(speed) < Math.abs(minSetSpeed))
		{
			minSetSpeed = speed;
		}
		SmartDashboard.putNumber(RobotMap.linearActuatorSpeedMaxKey, maxSetSpeed);
		SmartDashboard.putNumber(RobotMap.linearActuatorSpeedMinKey, minSetSpeed);
	}
	
	/**
	 * Called by the PID controller with its current calculated output value,
	 * which is used to set the motor direction and speed.
	 */
	protected void usePIDOutput(double output)
	{
		setSpeed(output);
	}
}
