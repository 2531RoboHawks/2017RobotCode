package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveSquare extends CommandGroup {

	public DriveSquare() {
		addSequential(new TimeDrive(1000, 0.5));
		addSequential(new Turn2Angle(90));
		addSequential(new TimeDrive(1000, 0.5));
		addSequential(new Turn2Angle(90));
		addSequential(new TimeDrive(1000, 0.5));
		addSequential(new Turn2Angle(90));
		addSequential(new TimeDrive(1000, 0.5));
		addSequential(new Turn2Angle(90));
	}
}
