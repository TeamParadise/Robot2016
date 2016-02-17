package org.usfirst.frc.team1165.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShootAtHighGoal extends CommandGroup {
    
    public  ShootAtHighGoal() 
    {
    	addSequential(new SetLinearActuatorSetpoint(2.175));
    	addSequential(new SpinShooterWheelsOut(5000));
    	addSequential(new WaitCommand(0.5));
    	addParallel(new PushBallToShooter());
    	addSequential(new SetLinearActuatorSetpoint(0.9));
    }
}
