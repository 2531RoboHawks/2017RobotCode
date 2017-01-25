package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGear extends Command {
	boolean state;
	boolean done;

	public MoveGear(boolean s) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.gear);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("->Sol1");
		done = false;

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.gear.set(state);
		done = true;

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("-!Sol1");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
