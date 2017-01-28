package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGimbal extends Command {

	double pan = 0.5;

	double inc = 0.01;

	public MoveGimbal() {
		requires(Robot.gimbal);
	}

	protected void initialize() {
		System.out.println("-> MoveGimbal");
	}

	protected void execute() {
		if (OI.gamepad.getPOV() != -1) {
			if (OI.gamepad.getPOV() == 90) {
				pan += inc;
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
		Robot.gimbal.setAngles(pan);
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
