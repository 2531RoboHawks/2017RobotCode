package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeployGear extends Command {

	boolean done, state, toggle;

	public DeployGear() {
		requires(Robot.gear);
		done = false;
		toggle = false;
	}

	public DeployGear(boolean s) {
		requires(Robot.gear);
		state = s;
		done = false;
		toggle = true;
	}

	protected void initialize() {
		System.out.println("-> DeployGear");
	}

	protected void execute() {
		if (toggle) {
			Robot.gear.set(state);
			done = true;
		} else {
			Robot.gear.set(OI.gamepad.getRawButton(6));
		}
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		Robot.gear.set(false);
		System.out.println("-! DeployGear");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
