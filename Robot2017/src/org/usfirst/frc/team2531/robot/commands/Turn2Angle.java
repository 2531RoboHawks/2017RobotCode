package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class Turn2Angle extends Command {

	private PID pid = new PID(0.05, 0, 0, 0);
	private double angle;

	public Turn2Angle(double degrees) {
		requires(Robot.drive);
		angle = degrees;
		pid.setOutputLimits(-0.5, 0.5);
		pid.setOnTargetCount(10);
		pid.setOnTargetOffset(1);
	}

	protected void initialize() {
		System.out.println("-> Turn2Angle");
		pid.setSetpoint(angle + RobotMap.imu.getAngleX());
	}

	protected void execute() {
		double p = pid.compute(RobotMap.imu.getAngleX());
		Robot.drive.axisdrive(0, 0, -p);
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
