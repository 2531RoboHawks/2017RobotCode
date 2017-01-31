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

public class TrackLookUp extends Command {

	private PID turn = new PID(0.001, 0, 0, Robot.w / 2);
	private PID move = new PID(0.005, 0, 0, Robot.h / 2);
	private double turn_power = 0;
	private double move_power = 0;
	private double last_x = 0;
	private double last_y = 0;
	private boolean stop = false;
	private double[] t = { 0.0, 0.2, 0.3, 0.4 };

	public TrackLookUp(boolean ontargetstop) {
		stop = ontargetstop;
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> Track");
		turn.setOutputLimits(-0.5, 0.5);
		move.setOutputLimits(-0.5, 0.5);
		last_x = 0;
		last_y = 0;
	}

	protected void execute() {
		Mat mat = RobotMap.cam0.getImage();
		RobotMap.cam0.setColor(Robot.min1, Robot.max1, Robot.min2, Robot.max2, Robot.min3, Robot.max3);
		ArrayList<Rect> l = RobotMap.cam0.TRGBgetBlobs(mat, 230, 255);
		int x = 0;
		int y = 0;
		int size = 0;
		for (int i = 0; i < l.size(); i++) {
			Rect r = l.get(i);
			if (r != null && r.area() > 500) {
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
			RobotMap.cam0.putImage(mat);
			double p = 0;
			if (x < (Robot.w / 2) + 3 && x > (Robot.w / 2) - 3) {
				p = t[0];
			} else if (x < Robot.w / 3) {
				p = t[2];
			} else if (x < Robot.w / 4) {
				p = t[1];
			} else if (x < (Robot.w / 3) + Robot.w) {
				p = -t[2];
			} else if (x < (Robot.w / 4) + Robot.w) {
				p = -t[1];
			}
		} else {
			Imgproc.line(mat, new Point(last_x, 0), new Point(last_x, Robot.h), new Scalar(0, 255, 0), 1);
			Imgproc.line(mat, new Point(0, last_y), new Point(Robot.w, last_y), new Scalar(0, 255, 0), 1);
			RobotMap.cam0.putImage(mat);
			Robot.drive.stop();
		}
	}

	protected boolean isFinished() {
		if (stop) {
			return turn.onTarget() && move.onTarget();
		} else {
			return false;
		}
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! Track");
	}

	protected void interrupted() {
		end();
	}
}