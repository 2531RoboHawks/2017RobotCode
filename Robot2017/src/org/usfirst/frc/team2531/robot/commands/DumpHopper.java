package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frclib.time.Delay;

/**
 *
 */
public class DumpHopper extends CommandGroup {

	public DumpHopper() {
		addSequential(new MoveHopper(true));
		addSequential(new Delay(1000));
		addSequential(new MoveHopper(false));
	}
}
