package org.usfirst.frc.team2531.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SubHopper extends Subsystem {
	CANTalon up = new CANTalon(0);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void up(double s){
		
		up.set(s);
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
