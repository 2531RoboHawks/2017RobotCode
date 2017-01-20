package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeDrive extends Command {
	long endTime;
	boolean end;
	public TimeDrive(long t) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		endTime = System.currentTimeMillis() + t;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("->TimeDrive");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		while(System.currentTimeMillis() > endTime){
			Robot.drive.axisdrive(0, 0.6, 0);
		}
		end = true;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return end;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
