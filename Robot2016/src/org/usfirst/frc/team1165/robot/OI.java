package org.usfirst.frc.team1165.robot;

import org.usfirst.frc.team1165.robot.commands.PickupBall;
import org.usfirst.frc.team1165.robot.commands.PushBallToShooter;
import org.usfirst.frc.team1165.robot.commands.ShootAtHighGoal;
import org.usfirst.frc.team1165.robot.commands.SuckInBall;
import org.usfirst.frc.team1165.robot.commands.SetLinearActuatorSetpoint;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	public final Joystick leftStick = new Joystick(1);
	public final Joystick rightStick = new Joystick(0);
	public final Button ServoButton = new JoystickButton(rightStick,RobotMap.SERVO_BUTTON_NUMBER);
	public final Button pickupButton = new JoystickButton(rightStick,RobotMap.PICKUP_BUTTON_NUMBER);
	public final Button pushOutButton = new JoystickButton(rightStick,RobotMap.SHOOT_AT_HIGH_GOAL_BUTTON_NUMBER);
	public OI()
	{
		SmartDashboard.putNumber(RobotMap.linearActuatorSetpointKey, 2.50);
		SmartDashboard.putData(new SetLinearActuatorSetpoint(RobotMap.linearActuatorSetpointKey));
		SmartDashboard.putNumber("Push Time", 0.5);
		
		pushOutButton.whenPressed(new ShootAtHighGoal());
		pickupButton.whenPressed(new PickupBall());
		ServoButton.whenPressed(new PushBallToShooter());
	}
	
	public double getActuatorSpeed()
	{
		
		return -leftStick.getY();
	}
}