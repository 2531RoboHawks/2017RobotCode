package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.OpenGear;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIn extends Subsystem {
	 Solenoid on = new Solenoid(0); 
	 Solenoid off = new Solenoid(1); 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void set(boolean s){
		on.set(s);
		off.set(!s);
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new OpenGear());
    }
    
}

