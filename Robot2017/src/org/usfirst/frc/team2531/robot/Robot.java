
package org.usfirst.frc.team2531.robot;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2531.robot.commands.Track;
import org.usfirst.frc.team2531.robot.subsystems.Climber;
import org.usfirst.frc.team2531.robot.subsystems.DriveSystem;
import org.usfirst.frc.team2531.robot.subsystems.Gimbal;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frclib.vision.Vision;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveSystem drive;
	public static Climber climber;
	public static Gimbal gimbal;

	@SuppressWarnings("rawtypes")
	SendableChooser auto;

	Command autocommand;

	@Override
	public void robotInit() {
		System.out.println("# Robot");
		climber = new Climber();
		oi = new OI();
		drive = new DriveSystem();
		gimbal = new Gimbal();
		RobotMap.cam0 = new Vision("cam0", 0);
		// RobotMap.cam1 = new Vision("cam1", 1);
		initSmartDashboard();
	}

	@Override
	public void disabledInit() {
		System.out.println("# Disabled");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		// RobotMap.cam0.showLive();
		// RobotMap.cam1.showLive();
		Mat mat = RobotMap.cam0.getImage();
		RobotMap.cam0.setColor(60, 120, 240, 255, 210, 250);
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
			mat = RobotMap.cam0.showBlobs(mat, l, new Scalar(0, 255, 0));
			Imgproc.line(mat, new Point(x, 0), new Point(x, 480), new Scalar(0, 255, 0), 2);
			Imgproc.line(mat, new Point(0, y), new Point(640, y), new Scalar(0, 255, 0), 2);
			RobotMap.cam0.putImage(mat);
		} else {
			RobotMap.cam0.showLive();
		}

	}

	@Override
	public void autonomousInit() {
		System.out.println("# Autonomous");
		autocommand = (Command) auto.getSelected();
		if (autocommand != null) {
			autocommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		// RobotMap.cam0.showLive();
	}

	@Override
	public void teleopInit() {
		System.out.println("# Teleop");
		if (autocommand != null) {
			autocommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		RobotMap.cam0.showLive();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
		updateSmartDashboard();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initSmartDashboard() {
		auto = new SendableChooser();
		auto.addDefault("No Auto", null);
		auto.addObject("Vision Tracking", new Track(false));
		SmartDashboard.putData("Autonomous Mode", auto);
	}

	public void updateSmartDashboard() {
	}
}
