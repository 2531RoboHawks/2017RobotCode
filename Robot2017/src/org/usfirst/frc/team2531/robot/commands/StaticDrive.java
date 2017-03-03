package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class StaticDrive extends Command {

	int pov = 0;

	public StaticDrive() {
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> StaticDrive");
	}

	protected void execute() {
		if (OI.axis.getRawAxis(2) > 0) {
			Robot.drive.orientationdrive(OI.axis.getRawAxis(0) / (4 * OI.axis.getRawAxis(2)),
					OI.axis.getRawAxis(1) / (4 * OI.axis.getRawAxis(2)),
					OI.axis.getRawAxis(3) / (4 * OI.axis.getRawAxis(2)), RobotMap.imu.getAngleY() / 4);
		} else {
			Robot.drive.orientationdrive(OI.axis.getRawAxis(0), OI.axis.getRawAxis(1), OI.axis.getRawAxis(3),
					RobotMap.imu.getAngleY() / 4);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! StaticDrive");
	}

	protected void interrupted() {
		end();
	}
}
