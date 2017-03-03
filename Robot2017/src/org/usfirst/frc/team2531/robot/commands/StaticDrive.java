package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StaticDrive extends Command {

	int pov = 0;

	public StaticDrive() {
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> StaticDrive");
	}

	protected void execute() {
		// double a = Math.toRadians((RobotMap.imu.getAngleY() / 4) % 360);
		/*
		 * if (OI.gamepad.getRawAxis(3) > 0) {
		 * Robot.drive.axisdrive((Math.abs(Math.cos(a)) *
		 * OI.gamepad.getRawAxis(4)) / (4 * OI.gamepad.getRawAxis(3)),
		 * (Math.abs(Math.sin(a)) * OI.gamepad.getRawAxis(1)) / (4 *
		 * OI.gamepad.getRawAxis(3)), OI.gamepad.getRawAxis(0) / (4 *
		 * OI.gamepad.getRawAxis(3))); } else {
		 * Robot.drive.axisdrive(Math.abs(Math.cos(a)) *
		 * OI.gamepad.getRawAxis(4), Math.abs(Math.sin(a)) *
		 * OI.gamepad.getRawAxis(1), OI.gamepad.getRawAxis(0)); }
		 */
		// Robot.drive.axisdrive(Math.cos(a) * OI.left.getX(), Math.sin(a) *
		// OI.right.getY(), OI.right.getX());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! StaticDrive");
	}

	protected void interrupted() {
		end();
	}
}
