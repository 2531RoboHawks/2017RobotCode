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

public class Track extends Command {

	private PID turn = new PID(0.001, 0, 0, 320);
	private PID move = new PID(0.005, 0, 0, 240);
	private double turn_power = 0;
	private double move_power = 0;
	private double last_x = 0;
	private double last_y = 0;
	private boolean stop = false;

	public Track(boolean ontargetstop) {
		stop = ontargetstop;
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> Track");
		turn.setOutputLimits(-0.5, 0.5);
		move.setOutputLimits(-0.5, 0.5);
	}

	protected void execute() {
		Mat mat = RobotMap.cam0.getImage();
		RobotMap.cam0.setColor(60, 110, 250, 255, 215, 240);
		ArrayList<Rect> l = RobotMap.cam0.filterArea(RobotMap.cam0.RGBgetBlobs(mat), 200);
		int x = 0;
		int y = 0;
		for (int i = 0; i < l.size(); i++) {
			Rect r = l.get(i);
			if (r != null) {
				x = r.x + (r.width / 2);
				y = r.y + (r.height / 2);
			}
		}
		if (!l.isEmpty()) {
			x /= l.size();
			y /= l.size();
			last_x = x;
			last_y = y;
			mat = RobotMap.cam0.showBlobs(mat, l, new Scalar(0, 255, 0));
			Imgproc.line(mat, new Point(x, 0), new Point(x, 480), new Scalar(0, 255, 0), 5);
			Imgproc.line(mat, new Point(0, y), new Point(640, y), new Scalar(0, 255, 0), 5);
			turn_power = -turn.compute(x);
			move_power = move.compute(y);
			Robot.drive.axisdrive(0, move_power, turn_power);
			RobotMap.cam0.putImage(mat);
		} else {
			Imgproc.line(mat, new Point(last_x, 0), new Point(last_x, 480), new Scalar(0, 255, 0), 5);
			Imgproc.line(mat, new Point(0, last_y), new Point(640, last_y), new Scalar(0, 255, 0), 5);
			RobotMap.cam0.putImage(mat);
			turn_power = -turn.compute(last_x);
			Robot.drive.axisdrive(0, 0, turn_power);
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