package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightPath extends CommandGroup {

	public RightPath() {
		addSequential(new TimeDrive(2500, 0.5, TimeDrive.FRONT));
		addSequential(new Turn2Angle(60));
		addSequential(new LineUpDeployGear());
	}
}
