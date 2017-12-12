package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSystem extends Subsystem {

    private Talon talon1 = new Talon(0);
    private Talon talon2 = new Talon(1);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveClimb(double power) {
    	talon1.set(power);
    	talon2.set(power);
    }
}

