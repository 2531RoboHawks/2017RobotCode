
package org.usfirst.frc.team2531.robot;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2531.robot.commands.CenterPath;
import org.usfirst.frc.team2531.robot.commands.DriveSquare;
import org.usfirst.frc.team2531.robot.commands.LeftPath;
import org.usfirst.frc.team2531.robot.commands.LineUpDeployGear;
import org.usfirst.frc.team2531.robot.commands.RightPath;
import org.usfirst.frc.team2531.robot.commands.TimeDrive;
import org.usfirst.frc.team2531.robot.commands.TrackY;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
import org.usfirst.frc.team2531.robot.subsystems.Climber;
import org.usfirst.frc.team2531.robot.subsystems.DriveSystem;
import org.usfirst.frc.team2531.robot.subsystems.GDU;
import org.usfirst.frc.team2531.robot.subsystems.Hopper;

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
	public static GDU gdu;
	public static Hopper hopper;
	public static double offset = 0, current = 0, rate, time = System.currentTimeMillis();
	@SuppressWarnings("rawtypes")
	SendableChooser auto;
	Command autocommand;

	public static int canny1 = 1000, canny2 = 500, threash = 254, min1 = 0, min2 = 240, min3 = 0, max1 = 1, max2 = 255,
			max3 = 1, w = 320, h = 240, minsize = 100;
	public static double angle = 0;

	@Override
	public void robotInit() {
		System.out.println("# Robot");
		RobotMap.cam0 = new Vision("cam0", 0, w, h);
		// RobotMap.cam1 = new Vision("cam1", 1, w, h);
		drive = new DriveSystem();
		gdu = new GDU();
		climber = new Climber();
		hopper = new Hopper();
		oi = new OI();
		RobotMap.imu.calibrate();
		RobotMap.imu.reset();
		RobotMap.heading = 0;
		initSmartDashboard();
		// CameraServer.getInstance().startAutomaticCapture(1);
	}

	@Override
	public void disabledInit() {
		System.out.println("# Disabled");
		RobotMap.imu.reset();
		RobotMap.heading = 0;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		angle = (RobotMap.imu.getAngleX() / 4) % 360;
		proc();
	}

	@Override
	public void autonomousInit() {
		System.out.println("# Autonomous");
		RobotMap.imu.reset();
		RobotMap.heading = 0;
		autocommand = (Command) auto.getSelected();
		if (autocommand != null) {
			autocommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		angle = (RobotMap.imu.getAngleX() / 4) % 360;
		RobotMap.cam0.showLive();
	}

	@Override
	public void teleopInit() {
		System.out.println("# Teleop");
		// RobotMap.imu.reset();
		RobotMap.heading = 0;
		if (autocommand != null) {
			autocommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		angle = (RobotMap.imu.getAngleX() / 4) % 360;
		proc();
		if ((OI.axis.getRawAxis(3) == 0) && (OI.axis.getRawAxis(1) == 0) && (OI.axis.getRawAxis(2) == 0)) {
			offset = Robot.angle - current;
			time = time - System.currentTimeMillis();
			rate = offset / time;
		} else {
			current = Robot.angle;

		}
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
		updateSmartDashboard();
		angle = (RobotMap.imu.getAngleX() / 4) % 360;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initSmartDashboard() {
		auto = new SendableChooser();
		auto.addDefault("No Auto", null);
		auto.addObject("Vision Tracking", new TrackY(true));
		auto.addObject("Time Drive", new TimeDrive(1000, 0.5));
		auto.addObject("Turn", new Turn2Angle(90));
		auto.addObject("Drive In Square", new DriveSquare());
		auto.addObject("Left", new LeftPath());
		auto.addObject("Center", new CenterPath());
		auto.addObject("Right", new RightPath());
		auto.addObject("Line Up Deploy Gear", new LineUpDeployGear());
		auto.addObject("Base Line", new TimeDrive(5000, 0.5, TimeDrive.FRONT));
		SmartDashboard.putData("Autonomous Mode", auto);
		SmartDashboard.putNumber("DesiredHeading", RobotMap.heading);
		SmartDashboard.putNumber("Heading", angle);
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("DesiredHeading", RobotMap.heading);
		SmartDashboard.putNumber("Heading", Robot.angle);
	}

	public void proc() {
		Mat mat = RobotMap.cam0.getImage();
		RobotMap.cam0.setCanny(canny1, canny1);
		RobotMap.cam0.setThreash(threash);
		ArrayList<Rect> l = RobotMap.cam0.TSCRgetBlobs(mat);
		int x = 0;
		int y = 0;
		int size = 0;
		for (int i = 0; i < l.size(); i++) {
			Rect r = l.get(i);
			if (r != null && r.area() > minsize) {
				x += r.x + (r.width / 2);
				y += r.y + (r.height / 2);
				size += 1;
			}
		}
		if (size > 0) {
			x /= size;
			y /= size;
			mat = RobotMap.cam0.showBlobs(mat, l, new Scalar(0, 255, 0));
			Imgproc.line(mat, new Point(x, 0), new Point(x, h), new Scalar(0, 255, 0), 1);
			Imgproc.line(mat, new Point(0, y), new Point(w, y), new Scalar(0, 255, 0), 1);
		}
		RobotMap.cam0.putImage(mat);
	}
}
