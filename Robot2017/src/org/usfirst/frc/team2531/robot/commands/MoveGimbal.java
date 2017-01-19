package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGimbal extends Command {

	double pan = 0.5;
	double tilt = 0.3;

	double inc = 0.1;

	public MoveGimbal() {
		requires(Robot.gimbal);
	}

	protected void initialize() {
		System.out.println("-> MoveGimbal");
	}

	protected void execute() {
		if (OI.gamepad.getPOV() != -1) {
			if (OI.gamepad.getPOV() == 0) {
				tilt += inc;
			} else if (OI.gamepad.getPOV() == 90) {
				pan += inc;
			} else if (OI.gamepad.getPOV() == 180) {
				tilt -= inc;
			} else if (OI.gamepad.getPOV() == 270) {
				pan -= inc;
			}
		}
		if (pan > 1) {
			pan = 1;
		}
		if (pan < 0) {
			pan = 0;
		}
		if (tilt > 1) {
			tilt = 1;
		}
		if (tilt < 0) {
			tilt = 0;
		}
		Robot.gimbal.setAngles(pan, tilt);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		System.out.println("-! MoveGimbal");
	}

	protected void interrupted() {
		end();
	}
}
