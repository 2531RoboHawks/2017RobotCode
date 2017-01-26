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

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> TimeDrive");
		endTime = time + System.currentTimeMillis();
		pid = new PID(0.06, 0.0, 0.0, RobotMap.heading);
		pid.setOutputLimits(-0.2, 0.2);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double t = pid.compute(-RobotMap.imu.getAngleZ() / 4);
		Robot.drive.axisdrive(0, -pow, 0);
		if (endTime < System.currentTimeMillis()) {
			end = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return end;
	}

	// Called once after isFinished returns true
	protected void end() {
		end = false;
		Robot.drive.stop();
		System.out.println("-! TimeDrive");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
