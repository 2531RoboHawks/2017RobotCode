package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionTrack extends Command {

	public VisionTrack() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> VisionTrack");
	}

	// Called repeatedly when this Command is scheduled to run
	/*
	 * protected void execute() { //Mat m = RobotMap.vis.getRawImage();
	 * //RobotMap.vis.setColor(0, 255, 0, 50, 0, 255); //ArrayList<Rect> b =
	 * RobotMap.vis.HLSgetBlobs(m); for (int i = 0; i < b.size(); i++) { Rect r
	 * = b.get(i); if (r != null) { Imgproc.rectangle(m, r.tl(), r.br(), new
	 * Scalar(0, 255, 0), 6); } } RobotMap.vis.putImage(m); }
	 */

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("-! VisionTrack");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
