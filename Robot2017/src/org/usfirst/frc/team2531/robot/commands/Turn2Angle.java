package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class Turn2Angle extends Command {

	private PID pid = new PID(0.08, 0, 0, 0);
	private double degrees = 0;

	public Turn2Angle(double degrees) {
		requires(Robot.drive);
		this.degrees = degrees;
	}

	protected void initialize() {
		System.out.println("-> Turn2Angle");
		RobotMap.heading += degrees;
		pid.setSetpoint(RobotMap.heading);
		pid.setOnTargetOffset(1);
		pid.setOutputLimits(-0.4, 0.4);
		pid.setOnTargetCount(10);
	}

	protected void execute() {
		double p = pid.compute(RobotMap.imu.getAngleZ() / 4);
		Robot.drive.axisdrive(0, 0, p);
	}

	protected boolean isFinished() {
		return pid.onTarget();
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! Turn2Angle");
	}

	protected void interrupted() {
		end();
	}
}
