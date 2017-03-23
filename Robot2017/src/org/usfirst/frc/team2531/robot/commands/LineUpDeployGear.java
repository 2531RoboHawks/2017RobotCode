package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LineUpDeployGear extends CommandGroup {

	public LineUpDeployGear() {
		addSequential(new MoveTrackY(-0.2), 5);
		addSequential(new DeployGear());
	}

}