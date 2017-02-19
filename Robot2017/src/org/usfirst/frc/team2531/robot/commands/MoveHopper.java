package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

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
			Robot.hopper.set(0.5);
			if (RobotMap.hopperup.get()) {
				done = true;
			}
		} else {
			Robot.hopper.set(-0.5);
			if (RobotMap.hopperdown.get()) {
				done = true;
			}
		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		Robot.climber.stop();
		System.out.println("-! MoveHopper");
	}

	protected void interrupted() {
		end();
	}
}
