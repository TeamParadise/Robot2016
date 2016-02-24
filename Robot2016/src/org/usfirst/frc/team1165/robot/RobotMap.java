package org.usfirst.frc.team1165.robot;

import org.usfirst.frc.team1165.util.Gamepad;
import org.usfirst.frc.team1165.util.Gamepad.Button;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
    public static final int joystickPort0					= 0;
    
    public static final int PICKUP_BUTTON_NUMBER				= Gamepad.Button.LEFT_BUMP.getValue();
    public static final int SHOOT_AT_HIGH_GOAL_BUTTON_NUMBER	= Gamepad.Button.RIGHT_BUMP.getValue();
    public static final int SERVO_BUTTON_NUMBER 				= Gamepad.Button.Y.getValue();
    public static final int WINCH_BUTTON						= Gamepad.Button.A.getValue();
      
    public static final int ROTATE_TO_HEADING_TIMEOUT				= 3;
    public static final double DRIVE_SPEED 							=   0.4;		// Drive magnitude
    public static final double DRIVE_START_SPEED 					=   0.5;		// Drive magnitude
    public static final double ROTATE_SPEED 						=   0.6;	// Rotate magnitude
    public static final double ROTATE_CREEP_SPEED 					=   0.5;	// Rotate magnitude
/*    public static final double CREEP_DISTANCE						=   30;		// Inches
    public static final double CREEP_TO_OBJECT_TIMEOUT				=   7.5;	// seconds
*/    public static final double BRAKE_OFFSET							=   15;//degrees

    public static final int linearActuatorMotorChannel		= 3;
    public static final int linearActuatorSensorPort		= 0;
    
    public static final int ABSOLUTE_ENCODER_PORT			= 2;
    public static final int GYROSCOPE_PORT					= 1;
    //public static final Joystick SERVO_STICK = Robot.oi.rightStick;
    
    public static final String linearActuatorSpeedKey		= "Linear Actuator Speed";
    public static final String linearActuatorSpeedMaxKey	= "Linear Actuator Speed Max";
    public static final String linearActuatorSpeedMinKey	= "Linear Actuator Speed Min";
    public static final String linearActuatorSensorKey		= "Linear Actuator Sensor";
    public static final String linearActuatorSensorMinKey	= "Linear Actuator Sensor Min";
    public static final String linearActuatorSensorMaxKey	= "Linear Actuator Sensor Max";
    public static final String linearActuatorSetpointKey	= "Linear Actuator Setpoint";

    public static final String primaryCameraName 			= "cam0";
    public static final String secondaryCameraName 			= "cam1";
}