package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Gear extends Command {

	boolean done, state, toggle;

	public Gear() {
		requires(Robot.gdu);
		done = false;
		toggle = false;
	}

	public Gear(boolean s) {
		requires(Robot.gdu);
		done = false;
		toggle = true;
		state = s;
	}

	protected void initialize() {
		System.out.println("-> Gear");
	}

	protected void execute() {
		if (toggle) {
			Robot.gdu.set(state, true);
			done = true;
		} else {
			Robot.gdu.set(OI.gamepad.getRawButton(6), OI.gamepad.getRawButton(5));
		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		Robot.gdu.set(false, false);
		System.out.println("-! Gear");
	}

	protected void interrupted() {
		end();
	}
}
