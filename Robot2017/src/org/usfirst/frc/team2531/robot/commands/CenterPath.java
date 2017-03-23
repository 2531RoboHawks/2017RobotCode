package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterPath extends CommandGroup {

	public CenterPath() {
		addSequential(new MoveTrackY(-0.2), 8);
		addSequential(new DeployGear());
	}
}
