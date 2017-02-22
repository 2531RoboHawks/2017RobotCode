package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftPath extends CommandGroup {

	public LeftPath() {
		addSequential(new TimeDrive(800, 0.5));
		addSequential(new Turn2Angle(140));
		addSequential(new Crash(TimeDrive.LEFT, 20));
		addSequential(new DeployGear());
	}
}
