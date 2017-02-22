package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {

	int pov = 0;

	public Drive() {
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> Drive");
	}

	protected void execute() {
		if (OI.gamepad.getPOV(0) != -1) {
			pov = OI.gamepad.getPOV(0);
		}
		switch (pov) {
		case 0:
			if (OI.gamepad.getRawAxis(3) > 0) {
				Robot.drive.axisdrive(OI.gamepad.getRawAxis(4) / (4 * OI.gamepad.getRawAxis(3)),
						OI.gamepad.getRawAxis(1) / (4 * OI.gamepad.getRawAxis(3)),
						OI.gamepad.getRawAxis(0) / (4 * OI.gamepad.getRawAxis(3)));
			} else {
				Robot.drive.axisdrive(OI.gamepad.getRawAxis(4), OI.gamepad.getRawAxis(1), OI.gamepad.getRawAxis(0));
			}
			break;
		case 270:
			if (OI.gamepad.getRawAxis(3) > 0) {
				Robot.drive.axisdrive(OI.gamepad.getRawAxis(1) / (4 * OI.gamepad.getRawAxis(3)),
						-OI.gamepad.getRawAxis(4) / (4 * OI.gamepad.getRawAxis(3)),
						OI.gamepad.getRawAxis(0) / (4 * OI.gamepad.getRawAxis(3)));
			} else {
				Robot.drive.axisdrive(OI.gamepad.getRawAxis(1), -OI.gamepad.getRawAxis(4), OI.gamepad.getRawAxis(0));
			}
			break;
		case 180:
			if (OI.gamepad.getRawAxis(3) > 0) {
				Robot.drive.axisdrive(-OI.gamepad.getRawAxis(4) / (4 * OI.gamepad.getRawAxis(3)),
						-OI.gamepad.getRawAxis(1) / (4 * OI.gamepad.getRawAxis(3)),
						OI.gamepad.getRawAxis(0) / (4 * OI.gamepad.getRawAxis(3)));
			} else {
				Robot.drive.axisdrive(-OI.gamepad.getRawAxis(4), -OI.gamepad.getRawAxis(1), OI.gamepad.getRawAxis(0));
			}
			break;
		case 90:
			if (OI.gamepad.getRawAxis(3) > 0) {
				Robot.drive.axisdrive(-OI.gamepad.getRawAxis(1) / (4 * OI.gamepad.getRawAxis(3)),
						OI.gamepad.getRawAxis(4) / (4 * OI.gamepad.getRawAxis(3)),
						OI.gamepad.getRawAxis(0) / (4 * OI.gamepad.getRawAxis(3)));
			} else {
				Robot.drive.axisdrive(-OI.gamepad.getRawAxis(1), OI.gamepad.getRawAxis(4), OI.gamepad.getRawAxis(0));
			}
			break;
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
