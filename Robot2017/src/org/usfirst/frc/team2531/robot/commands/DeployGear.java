package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeployGear extends Command {
	boolean done;

	public DeployGear() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.gear);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> MoveGear");

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.gear.set(OI.gamepad.getRawButton(6));

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.gear.set(false);
		System.out.println("-! MoveGear");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
