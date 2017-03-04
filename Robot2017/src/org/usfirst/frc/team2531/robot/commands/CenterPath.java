package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterPath extends CommandGroup {

	public CenterPath() {
		addSequential(new TimeDrive(2000, 0.5, TimeDrive.LEFT));
		addSequential(new DeployGear());
		addSequential(new TimeDrive(1000, -0.5, TimeDrive.LEFT));
	}
}
