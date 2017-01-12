package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GimbalCommand extends Command {
	boolean fin;

	public GimbalCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.gimbal);
		fin = false;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> Gimbal");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (OI.Dpad1.get() == true) {
			Robot.gimbal.setAngUP();
		} else if (OI.Dpad2.get() == true) {
			Robot.gimbal.setAngDOWN();
		} else if (OI.Dpad3.get() == true) {
			Robot.gimbal.setPosUP();
		} else if (OI.Dpad4.get() == true) {
			Robot.gimbal.setPosDOWN();
		} else {
			fin = true;
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return fin;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("-! Gimbal");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
