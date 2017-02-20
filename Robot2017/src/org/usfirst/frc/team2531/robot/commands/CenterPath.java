package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterPath extends CommandGroup {

	public CenterPath() {
		addSequential(new TimeDrive(1000, 5.0, TimeDrive.RIGHT));
	}
}
