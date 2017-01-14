package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

	private boolean dir;

	public Climb(boolean d) {
		requires(Robot.climber);
		dir = d;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> Climb");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (dir) {
			Robot.climber.set(1);
		} else {
			Robot.climber.set(-1);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("-! Climb");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
