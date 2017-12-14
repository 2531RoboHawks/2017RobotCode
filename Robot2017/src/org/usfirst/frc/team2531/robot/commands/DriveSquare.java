package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveSquare extends CommandGroup {

	public DriveSquare(long time, double p) {
		addSequential(new TimeDriveGyro(time, p));
		addSequential(new Turn2Angle(90));
		addSequential(new TimeDriveGyro(time, p));
		addSequential(new Turn2Angle(90));
		addSequential(new TimeDriveGyro(time, p));
		addSequential(new Turn2Angle(90));
		addSequential(new TimeDriveGyro(time, p));
		addSequential(new Turn2Angle(90));
	}
}
