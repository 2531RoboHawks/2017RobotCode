package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveClimber extends Command {

	private boolean dir;

	public MoveClimber(boolean d) {
		requires(Robot.climber);
		dir = d;
	}

	protected void initialize() {
		System.out.println("-> MoveClimber");
	}

	protected void execute() {
		if (dir) {
			Robot.climber.set(1);
		} else {
			Robot.climber.set(-1);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.climber.stop();
		System.out.println("-! MoveClimber");
	}

	protected void interrupted() {
		end();
	}
}
