package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftPath extends CommandGroup {

	public LeftPath() {
		addSequential(new TimeDrive(3000, 0.5, TimeDrive.FRONT));
		addSequential(new Turn2Angle(120));
		addSequential(new LineUpDeployGear());
	}
}
