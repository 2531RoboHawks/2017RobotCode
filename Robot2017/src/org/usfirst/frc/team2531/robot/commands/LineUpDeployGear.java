package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frclib.time.Delay;

/**
 *
 */
public class LineUpDeployGear extends CommandGroup {

	public LineUpDeployGear() {
		addSequential(new LineUpGear());
		addSequential(new MoveGDU(true, false));
		addSequential(new Delay(500));
		addSequential(new MoveGDU(false, false));
		addSequential(new Delay(500));
	}

}