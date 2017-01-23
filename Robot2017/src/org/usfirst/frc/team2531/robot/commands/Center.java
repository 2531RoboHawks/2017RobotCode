package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Center extends CommandGroup {

	public Center() {
		addSequential(new TimeDrive(1000, 0.5));
		addSequential(new Turn2Angle(-47));
		addSequential(new TimeDrive(800, 0.5));
		addSequential(new Turn2Angle(47));
		addSequential(new TimeDrive(1500, 0.5));
	}
}
