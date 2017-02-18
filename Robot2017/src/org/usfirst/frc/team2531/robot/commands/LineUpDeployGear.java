package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frclib.time.Delay;

/**
 *
 */
public class LineUpDeployGear extends CommandGroup {

	public LineUpDeployGear() {
		addSequential(new LineUpGear());
		addSequential(new Gear(true));
		addSequential(new Delay(500));
		addSequential(new Gear(false));
		addSequential(new Delay(500));
	}

}