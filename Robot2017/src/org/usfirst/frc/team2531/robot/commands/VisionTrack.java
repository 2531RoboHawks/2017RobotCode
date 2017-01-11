package org.usfirst.frc.team2531.robot.commands;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionTrack extends Command {

	CvSource out;

	MjpegServer server;

	public VisionTrack() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> VisionTrack");
		out = new CvSource("stream", PixelFormat.kMJPEG, 640, 480, 30);
		server = new MjpegServer("cam", 1182);
		server.setSource(out);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		RobotMap.vis.setColor(0, 255, 0, 50, 0, 255);
		Mat m = RobotMap.vis.getRaw();
		ArrayList<Rect> b = RobotMap.vis.HLSgetBlobs(m);
		for (int i = 0; i < b.size(); i++) {
			Rect r = b.get(i);
			if (r != null) {
				Imgproc.rectangle(m, r.tl(), r.br(), new Scalar(0, 255, 0), 6);
			}
		}
		out.putFrame(m);
		out.free();
		CameraServer.getInstance().putVideo("stream", 640, 480);
	}

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
