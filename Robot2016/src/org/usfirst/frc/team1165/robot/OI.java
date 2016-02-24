package org.usfirst.frc.team1165.robot;

import org.usfirst.frc.team1165.robot.commands.CancelCommand;
import org.usfirst.frc.team1165.robot.commands.DriveOverRockWall;
import org.usfirst.frc.team1165.robot.commands.DriveOverTerrain;
import org.usfirst.frc.team1165.robot.commands.DriveStraight;
import org.usfirst.frc.team1165.robot.commands.DriveUnderLowBar;
import org.usfirst.frc.team1165.robot.commands.DriveUnderPortcullis;
import org.usfirst.frc.team1165.robot.commands.FlipDriveDirection;
import org.usfirst.frc.team1165.robot.commands.FlipRobotFront;
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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	public final Joystick leftStick = new Joystick(0);
	public final Gamepad gamepad = new Gamepad(1);
	public final JoystickButton servoButton 		= new JoystickButton(gamepad, RobotMap.SERVO_BUTTON_NUMBER);
	public final JoystickButton pickupButton 		= new JoystickButton(gamepad,RobotMap.PICKUP_BUTTON_NUMBER);
	public final JoystickButton pushOutButton 		= new JoystickButton(gamepad,RobotMap.SHOOT_AT_HIGH_GOAL_BUTTON_NUMBER);
	public final JoystickButton winchButton 		= new JoystickButton(gamepad, RobotMap.WINCH_BUTTON);
	public final JoystickButton cancelButton 		= new JoystickButton(gamepad,RobotMap.CANCEL_BUTTON);
	public final JoystickButton flipDriveDirection 	= new JoystickButton(leftStick,1);
	public final JoystickButton cameraButton 		= new JoystickButton(leftStick,2);
	public final JoystickButton portcullisButton 	= new JoystickButton(leftStick,3);
	public final JoystickButton flipFront 			= new JoystickButton(leftStick,7);
	public final JoystickButton respoolWinch 		= new JoystickButton(leftStick,8);
	public final JoystickButton lowBarButton 		= new JoystickButton(leftStick,9);
	public final JoystickButton terrainButton 		= new JoystickButton(leftStick,10);
//	public final JoystickButton drawBridgeButton 	= new JoystickButton(leftStick,11);
	public final JoystickButton rockWall 			= new JoystickButton(leftStick,12);
	public boolean driveForward = true;
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
		flipDriveDirection.whenReleased(new FlipDriveDirection());
		cameraButton.whenPressed(new SwitchSecondayCamera());
		flipFront.whenPressed(new FlipRobotFront());
		lowBarButton.whenPressed(new DriveUnderLowBar());
		terrainButton.whenPressed(new DriveOverTerrain());
		rockWall.whenPressed(new DriveOverRockWall());
		cancelButton.whenPressed(new CancelCommand());
		portcullisButton.whenPressed(new DriveUnderPortcullis());
		//drawbridgeButton.whenPressed(new DriveOverDrawbridge());
	}
	
	public double getActuatorSpeed()
	{
		
		return -Robot.oi.gamepad.getY(Hand.kLeft);
	}
	public boolean useSecondaryCamera()
	{
		return enableSecondaryCamera;
	}
}