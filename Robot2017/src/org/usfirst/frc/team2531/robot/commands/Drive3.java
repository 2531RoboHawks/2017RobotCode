package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive3 extends Command {

	public Drive3() {
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> Drive3");
	}

	protected void execute() {
		if (OI.axis.getRawAxis(2) > 0) {
			Robot.drive.axisdrive(OI.axis.getRawAxis(0) / (4 * OI.axis.getRawAxis(2)),
					OI.axis.getRawAxis(1) / (4 * OI.axis.getRawAxis(2)),
					OI.axis.getRawAxis(3) / (4 * OI.axis.getRawAxis(2)));
		} else {
			Robot.drive.axisdrive(OI.axis.getRawAxis(0), OI.axis.getRawAxis(1), OI.axis.getRawAxis(3));
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! Drive3");
	}

	protected void interrupted() {
		end();
	}
}
