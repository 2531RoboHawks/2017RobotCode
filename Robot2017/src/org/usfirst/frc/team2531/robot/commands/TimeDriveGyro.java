package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class TimeDriveGyro extends Command {
	private long endTime;
	private boolean end;
	private long time;
	private double pow, angle;
	private PID pid = new PID(0.05, 0, 0, 0);

	public TimeDriveGyro(long t, double p) {
		requires(Robot.drive);
		pow = p;
		time = t;
		pid.setOutputLimits(-0.5, 0.5);
	}

	protected void initialize() {
		System.out.println("-> TimeDrive");
		endTime = time + System.currentTimeMillis();
		end = false;
		angle = RobotMap.imu.getAngleX();
		pid.setSetpoint(angle);
	}

	protected void execute() {
		double t = pid.compute(RobotMap.imu.getAngleX());
		Robot.drive.axisdrive(0, -pow, -t);
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
