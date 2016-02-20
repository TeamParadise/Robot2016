package org.usfirst.frc.team1165.robot;

import org.usfirst.frc.team1165.robot.commands.PickupBall;
import org.usfirst.frc.team1165.robot.commands.PushBallToShooter;
import org.usfirst.frc.team1165.robot.commands.ShootAtHighGoal;
import org.usfirst.frc.team1165.robot.commands.SuckInBall;
import org.usfirst.frc.team1165.robot.commands.SetLinearActuatorSetpoint;
import org.usfirst.frc.team1165.util.Gamepad;

import edu.wpi.first.wpilibj.GenericHID.Hand;
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
	public final Joystick leftStick = new Joystick(0);
	//public final Joystick rightStick = new Joystick(0);
	public final Gamepad gamepad = new Gamepad(1);
	public final JoystickButton servoButton = new JoystickButton(gamepad, RobotMap.SERVO_BUTTON_NUMBER);
	public final JoystickButton pickupButton = new JoystickButton(gamepad,RobotMap.PICKUP_BUTTON_NUMBER);
	public final JoystickButton pushOutButton = new JoystickButton(gamepad,RobotMap.SHOOT_AT_HIGH_GOAL_BUTTON_NUMBER);
	public OI()
	{
		SmartDashboard.putNumber(RobotMap.linearActuatorSetpointKey, 2.50);
		SmartDashboard.putData(new SetLinearActuatorSetpoint(RobotMap.linearActuatorSetpointKey));
		SmartDashboard.putNumber("Push Time", 0.5);
		
		pushOutButton.whenPressed(new ShootAtHighGoal());
		pickupButton.whenPressed(new PickupBall());
		servoButton.whenPressed(new PushBallToShooter(2));
	}
	
	public double getActuatorSpeed()
	{
		
		return -Robot.oi.gamepad.getY(Hand.kLeft);
	}
}