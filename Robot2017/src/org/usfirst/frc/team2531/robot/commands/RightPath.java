package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightPath extends CommandGroup {

	public RightPath() {
		addSequential(new TimeDrive(2867, 0.5));
		addSequential(new Turn2Angle(-50));
		addSequential(new TimeDrive(1933, 0.5));
	}
}
