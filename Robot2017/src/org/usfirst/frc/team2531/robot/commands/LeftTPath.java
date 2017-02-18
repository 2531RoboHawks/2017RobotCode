package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftTPath extends CommandGroup {

	public LeftTPath() {
		addSequential(new TimeDrive(3067, 0.5));
		addSequential(new Turn2Angle(54));
		addSequential(new TimeDrive(1800, 0.5));
	}
}
