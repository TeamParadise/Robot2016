package org.usfirst.frc.team1165.robot;

import org.usfirst.frc.team1165.robot.commands.FlipDriveDirection;
import org.usfirst.frc.team1165.robot.commands.RespoolWinch;
import org.usfirst.frc.team1165.robot.commands.StartWinch;
import org.usfirst.frc.team1165.robot.commands.PickupBall;
import org.usfirst.frc.team1165.robot.commands.PushBallToShooter;
import org.usfirst.frc.team1165.robot.commands.ShootAtHighGoal;
import org.usfirst.frc.team1165.robot.commands.StopWinch;
import org.usfirst.frc.team1165.robot.commands.SuckInBall;
import org.usfirst.frc.team1165.robot.commands.SetLinearActuatorSetpoint;
import org.usfirst.frc.team1165.robot.commands.SwitchSecondayCamera;
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
	public final JoystickButton winchButton = new JoystickButton(gamepad, RobotMap.WINCH_BUTTON);
	public final JoystickButton respoolWinch = new JoystickButton(leftStick,8);
	public final JoystickButton flipDriveDirection = new JoystickButton(leftStick,1);
	public final JoystickButton cameraButton = new JoystickButton(leftStick,2);
	public boolean driveForward = false;
	public boolean enableSecondaryCamera = false;
	public OI()
	{
		SmartDashboard.putNumber(RobotMap.linearActuatorSetpointKey, 2.50);
		SmartDashboard.putData(new SetLinearActuatorSetpoint(RobotMap.linearActuatorSetpointKey));
		SmartDashboard.putNumber("Push Time", 0.5);
		
		respoolWinch.whenPressed(new RespoolWinch());
		respoolWinch.whenReleased(new StopWinch());
		winchButton.whenPressed(new StartWinch());
		winchButton.whenReleased(new StopWinch());
		pushOutButton.whenPressed(new ShootAtHighGoal());
		pickupButton.whenPressed(new PickupBall());
		servoButton.whenPressed(new PushBallToShooter(2));
		flipDriveDirection.whenPressed(new FlipDriveDirection());
		cameraButton.whenPressed(new SwitchSecondayCamera());
	}
	
	public double getActuatorSpeed()
	{
		
		return -Robot.oi.gamepad.getY(Hand.kLeft);
	}
	public boolean hasSecondaryCamera()
	{
		return enableSecondaryCamera;
		
	}
}