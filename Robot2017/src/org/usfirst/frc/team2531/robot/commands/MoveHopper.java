package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveHopper extends Command {

	boolean up, done;

	public MoveHopper(boolean up) {
		this.up = up;
		done = false;
		requires(Robot.hopper);
	}

	protected void initialize() {
		System.out.println("-> MoveHopper");
	}

	protected void execute() {
		if (up) {

		} else {

		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		System.out.println("-! MoveHopper");
	}

	protected void interrupted() {
		end();
	}
}
