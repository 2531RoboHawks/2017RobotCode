package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LineUpGear extends CommandGroup {

	public LineUpGear() {
		addSequential(new TrackR(true));
	}
}
