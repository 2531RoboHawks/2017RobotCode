package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenGear extends Command {
	boolean idone, itoggle, istate; 
    public OpenGear() {
    	requires(Robot.gearIn);
    	idone = false;
		itoggle = false;
    }
    public OpenGear(boolean s) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	istate = s;
		idone = false;
		itoggle = true;    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("<- Gear In");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (itoggle) {
			Robot.gear.set(istate);
			idone = true;
		} else {
			Robot.gear.set(OI.gamepad.getRawButton(7));
		}
	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return idone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearIn.set(false);
    	System.out.println("-! Gear In");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end(); 
    }
}
