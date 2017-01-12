package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsSystem extends Subsystem {

	Solenoid sol = new Solenoid(1);
	public void setstatus(){
		if(sol.get() == true){
			sol.set(false);
		} else{
			sol.set(true);
		}
		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
