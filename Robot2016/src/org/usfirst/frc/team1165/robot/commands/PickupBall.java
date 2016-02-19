package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PickupBall extends CommandGroup {
    
    public  PickupBall()
    {
    	addSequential(new SetLinearActuatorSetpoint(5.75));
    	addSequential(new SuckInBall(4000));//Drives Right Wheel Positive
    	addSequential(new SetLinearActuatorSetpoint(0.9));
    	//addSequential(new DriveShooterWithJoysticks());
    }
}
