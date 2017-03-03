package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGDU extends Command {

	boolean done, state1, state2, toggle;

	public MoveGDU() {
		requires(Robot.gdu);
		done = false;
		toggle = false;
	}

	public MoveGDU(boolean s1, boolean s2) {
		requires(Robot.gdu);
		done = false;
		toggle = true;
		state1 = s1;
		state2 = s2;
	}

	protected void initialize() {
		System.out.println("-> MoveGDU");
		done = false;
	}

	protected void execute() {
		if (toggle) {
			Robot.gdu.set(state1, state2);
			done = true;
		} else {
			Robot.gdu.set(OI.gamepad.getRawButton(6), OI.gamepad.getRawButton(5));
			// Robot.gdu.set(OI.left.getRawButton(1), OI.right.getRawButton(1));
		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		Robot.gdu.set(false, false);
		System.out.println("-! MoveGDU");
	}

	protected void interrupted() {
		end();
	}
}
