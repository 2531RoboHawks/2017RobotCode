package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterPath extends CommandGroup {

	public CenterPath() {
		addSequential(new TimeDrive(1200, 0.5));
		addSequential(new Turn2Angle(-47));
		addSequential(new TimeDrive(700, 0.5));
		addSequential(new Turn2Angle(47));
		addSequential(new TimeDrive(1600, 0.5));
	}
}
