package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveHopper extends Command {

	boolean up, done, stop;

	public MoveHopper(boolean up, boolean stop) {
		this.up = up;
		this.stop = stop;
		done = false;
		requires(Robot.hopper);
	}

	public MoveHopper(boolean up) {
		this.up = up;
		this.stop = true;
		done = false;
		requires(Robot.hopper);
	}

	protected void initialize() {
		System.out.println("-> MoveHopper");
	}

	protected void execute() {
		if (up) {
			Robot.hopper.set(-0.8);
			if (RobotMap.hopperup.get() && !stop) {
				done = true;
			}
		} else {
			Robot.hopper.set(0.8);
			if (RobotMap.hopperdown.get() && !stop) {
				done = true;
			}
		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		Robot.hopper.stop();
		System.out.println("-! MoveHopper");
	}

	protected void interrupted() {
		end();
	}
}
