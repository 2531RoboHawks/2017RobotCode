package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterPath extends CommandGroup {

	public CenterPath() {
		addSequential(new Crash(TimeDrive.LEFT, 20));
		addSequential(new DeployGear());
	}
}
