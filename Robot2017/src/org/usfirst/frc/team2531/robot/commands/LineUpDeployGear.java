package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frclib.time.Delay;

/**
 *
 */
public class LineUpDeployGear extends CommandGroup {

	public LineUpDeployGear() {
		addSequential(new LineUpGear());
		addSequential(new DeployGear(true));
		addSequential(new Delay(500));
		addSequential(new DeployGear(false));
		addSequential(new Delay(500));
	}

}