
package org.usfirst.frc.team1165.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team1165.robot.subsystems.Camera;
import org.usfirst.frc.team1165.robot.subsystems.Camera.CameraMode;
import org.usfirst.frc.team1165.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1165.robot.subsystems.LinearActuatorPID;
import org.usfirst.frc.team1165.robot.subsystems.LinearActuatorSensor;
import org.usfirst.frc.team1165.robot.subsystems.ManipulationArm;
import org.usfirst.frc.team1165.robot.subsystems.MoveServo;
import org.usfirst.frc.team1165.robot.subsystems.Shooter;
import org.usfirst.frc.team1165.robot.subsystems.Winch;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{
	public static final DriveTrain robotDrive = new DriveTrain();
	public static final LinearActuatorSensor linearActuatorSensor = new LinearActuatorSensor();
	public static final LinearActuatorPID linearActuator = new LinearActuatorPID();
	public static final MoveServo moveServo = new MoveServo();
	public static final Shooter shooter = new Shooter();
	public static final Camera camera = new Camera(CameraMode.THREAD,"cam1","cam0");
	public static final Winch winch = new Winch();
	public static final ManipulationArm arm = new ManipulationArm();
	public static OI oi;
	public static boolean activeActuator = false;
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
   
}
