package frclib.time;

import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {

	boolean done = false;
	int milliseconds;

	public Delay(int millis) {
		milliseconds = millis;
	}

	protected void initialize() {
		Thread t = new Thread() {
			public void run() {
				Time.delayMiliseconds(milliseconds);
				done = true;
			}
		};
		t.start();
	}

	protected void execute() {

	}

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
