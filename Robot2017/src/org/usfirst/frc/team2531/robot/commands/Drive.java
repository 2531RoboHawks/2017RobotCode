package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {

	public Drive() {
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> Drive");
	}

	protected void execute() {
		if (OI.gamepad.getRawAxis(3) > 0) {
			Robot.drive.axisdrive(OI.gamepad.getRawAxis(4) / (4 * OI.gamepad.getRawAxis(3)),
					OI.gamepad.getRawAxis(1) / (4 * OI.gamepad.getRawAxis(3)),
					OI.gamepad.getRawAxis(0) / (4 * OI.gamepad.getRawAxis(3)));
		} else {
			Robot.drive.axisdrive(OI.gamepad.getRawAxis(4), OI.gamepad.getRawAxis(1), OI.gamepad.getRawAxis(0));
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! Drive");
	}

	protected void interrupted() {
		end();
	}
}
