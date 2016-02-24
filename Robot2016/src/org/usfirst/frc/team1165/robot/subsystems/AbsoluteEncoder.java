package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.DisplayEncoder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AbsoluteEncoder extends Subsystem
{
	private AnalogInput analogInput = new AnalogInput(RobotMap.ABSOLUTE_ENCODER_PORT);
	private AnalogPotentiometer encoder = new AnalogPotentiometer(analogInput, 360.0, 0);
	public double initialValue;
    public double upperLimit;
    public double lowerLimit;
    private Timer t;
    public AbsoluteEncoder()
    {
    	t = new Timer();
    	t.start();
    	resetEncoder();
    }
    public void initDefaultCommand() 
    {
        setDefaultCommand(new DisplayEncoder());
    }
    public void report()
    {
    	SmartDashboard.putNumber("Encoder Value", encoder.get());
    	SmartDashboard.putNumber("Encoder Initial Value", initialValue);
    	SmartDashboard.putNumber("Encoder Corrected Value", encoder.get()-initialValue);
    	SmartDashboard.putNumber("Encoder Lower Limit",lowerLimit);
    	SmartDashboard.putNumber("Encoder UpperLimit", upperLimit);
    	
    }
    public double getCurrentValue()
    {
    	return encoder.get();
    }
    public boolean atUpperLimit()
    {
    	if(t.get()>5)//114
    	return encoder.get()>=upperLimit;
    	else
    		return encoder.get()>=initialValue;
    }
    public boolean atLowerLimit()
    {
    	return encoder.get()<=lowerLimit;
    }
    public boolean atInitialValue()
    {
    	return encoder.get()<=initialValue+10&&encoder.get()>=initialValue-10;
    }
    public void resetEncoder()
    {
    	initialValue = 182;
    	lowerLimit = 133;
    	upperLimit = 235;
    }
    public double getCorrectedValue()
    {
    	return encoder.get()-initialValue;
    }
}

