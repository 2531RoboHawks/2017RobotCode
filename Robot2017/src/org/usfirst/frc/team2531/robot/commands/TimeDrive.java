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

	public static final int FRONT = 1, LEFT = 2, RIGHT = 3, BACK = 0;

	public int direction;

	public TimeDrive(long t, double p) {
		requires(Robot.drive);
		pow = p;
		time = t;

	}

	public TimeDrive(long t, double p, int d) {
		requires(Robot.drive);
		pow = p;
		time = t;
		direction = d;

	}

	protected void initialize() {
		System.out.println("-> TimeDrive");
		endTime = time + System.currentTimeMillis();
		pid = new PID(0.06, 0.0, 0.0, RobotMap.heading);
		pid.setOutputLimits(-0.2, 0.2);
		end = false;
	}

	protected void execute() {
		@SuppressWarnings("unused")
		double t = pid.compute(-RobotMap.imu.getAngleZ() / 4);
		switch (direction) {
		case 0:
			Robot.drive.axisdrive(0, pow, 0);
			break;
		case 1:
			Robot.drive.axisdrive(0, -pow, 0);
			break;
		case 2:
			Robot.drive.axisdrive(-pow, 0, 0);
			break;
		case 3:
			Robot.drive.axisdrive(pow, 0, 0);
			break;
		}
		if (endTime < System.currentTimeMillis()) {
			end = true;
		}
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
