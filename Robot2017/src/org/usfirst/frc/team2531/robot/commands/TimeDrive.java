package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeDrive extends Command {
	private long endTime;
	private boolean end;
	private long time;
	private double pow;

	public TimeDrive(long t, double p) {
		requires(Robot.drive);
		pow = p;
		time = t;

	}

	protected void initialize() {
		System.out.println("-> TimeDrive");
		endTime = time + System.currentTimeMillis();
		end = false;
	}

	protected void execute() {
		Robot.drive.axisdrive(0, pow, 0);
		if (System.currentTimeMillis() > endTime)
			end = true;
	}

	protected boolean isFinished() {
		return end;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! TimeDrive");
	}

	protected void interrupted() {
		end();
	}
}
