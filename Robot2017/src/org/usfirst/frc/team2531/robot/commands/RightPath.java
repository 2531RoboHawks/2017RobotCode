package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightPath extends CommandGroup {

	public RightPath() {
		addSequential(new TimeDrive(2000, 0.5, TimeDrive.FRONT));
		addSequential(new Crash(TimeDrive.LEFT, 5));
		addSequential(new DeployGear());
	}
}
