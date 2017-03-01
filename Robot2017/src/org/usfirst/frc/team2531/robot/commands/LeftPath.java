package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftPath extends CommandGroup {

	public LeftPath() {
		addSequential(new TimeDrive(2000, 0.5, TimeDrive.BACK));
		addSequential(new Crash(TimeDrive.LEFT, 5));
		addSequential(new DeployGear());
	}
}
