package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GyroDrive extends Command {
	public GyroDrive() {
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> GyroDrive");
	}

	protected void execute() {
		if (!OI.axis.getRawButton(1)) {
			Robot.drive.orientationdrive(OI.axis.getRawAxis(0) / (4 * OI.axis.getRawAxis(2)),
					OI.axis.getRawAxis(1) / (4 * OI.axis.getRawAxis(2)),
					OI.axis.getRawAxis(3) / (4 * OI.axis.getRawAxis(2)), Robot.angle - Robot.offset - Robot.rate);
			Robot.offset = 0;
		} else {
			Robot.drive.orientationdrive(OI.axis.getRawAxis(0), OI.axis.getRawAxis(1), OI.axis.getRawAxis(3),
					Robot.angle - Robot.offset - Robot.rate);
			Robot.offset = 0;
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! GyroDrive");
	}

	protected void interrupted() {
		end();
	}
}
