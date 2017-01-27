package frclib.time;

import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {

	boolean done = false;
	long milliseconds;
	long endtime = 0;

	public Delay(long millis) {
		milliseconds = millis;
	}

	protected void initialize() {
		System.out.println("-> Delay");
		endtime = System.currentTimeMillis() + milliseconds;
		done = false;
	}

	protected void execute() {
		if (System.currentTimeMillis() > endtime) {
			done = true;
		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		System.out.println("-! Delay");
	}

	protected void interrupted() {
		end();
	}
}
