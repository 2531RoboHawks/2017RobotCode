package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPath extends CommandGroup {

	public CenterPath() {
		addSequential(new TimeDrive(500, 0.5, TimeDrive.FRONT));
		addSequential(new Turn2Angle(90));
		addSequential(new LineUpDeployGear());
	}
}
