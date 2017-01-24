package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Right extends CommandGroup {

	public Right() {
		addSequential(new TimeDrive(2867, 0.5));
		addSequential(new Turn2Angle(-50));
		addSequential(new TimeDrive(1933, 0.5));
	}
}
