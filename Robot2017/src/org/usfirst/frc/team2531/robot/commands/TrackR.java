package org.usfirst.frc.team2531.robot.commands;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

public class TrackR extends Command {

	private PID turn = new PID(0.008, 0.005, 0, Robot.w / 2);
	private double turn_power = 0;
	private double last_x = 0;
	private double last_y = 0;
	private boolean stop = false;

	public TrackR(boolean ontargetstop) {
		stop = ontargetstop;
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> TrackR");
		turn.setSetpoint(RobotMap.heading);
		turn.setOnTargetOffset(2);
		turn.setOutputLimits(-0.4, 0.4);
		turn.setOnTargetCount(10);
		turn.setLoopTime(10);
		last_x = 0;
		last_y = 0;
	}

	protected void execute() {
		Mat mat = RobotMap.cam0.getImage();
		RobotMap.cam0.setCanny(Robot.canny1, Robot.canny2);
		RobotMap.cam0.setThreash(Robot.threash);
		RobotMap.cam0.setColor(Robot.min1, Robot.max1, Robot.min2, Robot.max2, Robot.min3, Robot.max3);
		ArrayList<Rect> l = RobotMap.cam0.RGBgetBlobs(mat);
		int x = 0;
		int y = 0;
		int size = 0;
		for (int i = 0; i < l.size(); i++) {
			Rect r = l.get(i);
			if (r != null && r.area() > Robot.minsize) {
				x += r.x + (r.width / 2);
				y += r.y + (r.height / 2);
				size += 1;
			}
		}
		if (size > 0) {
			x /= size;
			y /= size;
			last_x = x;
			last_y = y;
			mat = RobotMap.cam0.showBlobs(mat, l, new Scalar(0, 255, 0));
			Imgproc.line(mat, new Point(x, 0), new Point(x, Robot.h), new Scalar(0, 255, 0), 1);
			Imgproc.line(mat, new Point(0, y), new Point(Robot.w, y), new Scalar(0, 255, 0), 1);
			turn_power = -turn.compute2(x);
			Robot.drive.axisdrive(0, 0, turn_power);
			RobotMap.cam0.putImage(mat);
		} else {
			Imgproc.line(mat, new Point(last_x, 0), new Point(last_x, Robot.h), new Scalar(0, 255, 0), 1);
			Imgproc.line(mat, new Point(0, last_y), new Point(Robot.w, last_y), new Scalar(0, 255, 0), 1);
			RobotMap.cam0.putImage(mat);
			Robot.drive.stop();
		}
	}

	protected boolean isFinished() {
		if (stop) {
			return turn.onTarget();
		} else {
			return false;
		}
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! TrackR");
	}

	protected void interrupted() {
		end();
	}
}