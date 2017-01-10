package frclib.time;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Delay extends Command {

	boolean done = false;
	int milliseconds;

	public Delay(int millis) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		milliseconds = millis;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Time.delayMiliseconds(milliseconds);
		done = true;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
