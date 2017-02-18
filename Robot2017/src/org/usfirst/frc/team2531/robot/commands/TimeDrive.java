package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class TimeDrive extends Command {
	private long endTime;
	private boolean end;
	private long time;
	private double pow;
	private PID pid;

	public TimeDrive(long t, double p) {
		requires(Robot.drive);
		pow = p;
		time = t;

	}

	protected void initialize() {
		System.out.println("-> TimeDrive");
		endTime = time + System.currentTimeMillis();
		pid = new PID(0.06, 0.0, 0.0, RobotMap.heading);
		pid.setOutputLimits(-0.2, 0.2);
	}

	protected void execute() {
		double t = pid.compute(-RobotMap.imu.getAngleZ() / 4);
		Robot.drive.axisdrive(0, -pow, 0);
		if (endTime < System.currentTimeMillis()) {
			end = true;
		}
	}

	protected boolean isFinished() {
		return end;
	}

	protected void end() {
		end = false;
		Robot.drive.stop();
		System.out.println("-! TimeDrive");
	}

	protected void interrupted() {
		end();
	}
}
