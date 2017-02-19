package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Crash extends Command {

	public static final int FRONT = 0, LEFT = 2, RIGHT = 3, BACK = 1;

	public int direction;
	public boolean done;

	public int stopval = 20;

	public Crash(int d) {
		direction = d;
		done = false;
		requires(Robot.drive);
	}

	public Crash(int d, int s) {
		direction = d;
		stopval = s;
		done = false;
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> Crash");
	}

	protected void execute() {
		switch (direction) {
		case 0:
			Robot.drive.axisdrive(0, 0.5, 0);
			break;
		case 1:
			Robot.drive.axisdrive(0, -0.5, 0);
			break;
		case 2:
			Robot.drive.axisdrive(-0.5, 0, 0);
			break;
		case 3:
			Robot.drive.axisdrive(-0.5, 0, 0);
			break;
		}
		if (Math.abs(RobotMap.imu.getAccelX()) > stopval || Math.abs(RobotMap.imu.getAccelY()) > stopval) {
			done = true;
		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! Crash");
	}

	protected void interrupted() {
		end();
	}
}
