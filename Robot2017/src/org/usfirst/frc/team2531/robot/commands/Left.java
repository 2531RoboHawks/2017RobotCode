package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Left extends CommandGroup {

	public Left() {
		addSequential(new TimeDrive(3000, 0.5));
		addSequential(new Turn2Angle(54));
		addSequential(new TimeDrive(2000, 0.5));
	}
}
