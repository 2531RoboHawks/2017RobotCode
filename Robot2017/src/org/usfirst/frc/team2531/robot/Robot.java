
package org.usfirst.frc.team2531.robot;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2531.robot.commands.Center;
import org.usfirst.frc.team2531.robot.commands.Demo;
import org.usfirst.frc.team2531.robot.commands.Left;
import org.usfirst.frc.team2531.robot.commands.LineUpGear;
import org.usfirst.frc.team2531.robot.commands.Right;
import org.usfirst.frc.team2531.robot.commands.TimeDrive;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
import org.usfirst.frc.team2531.robot.subsystems.Climber;
import org.usfirst.frc.team2531.robot.subsystems.DriveSystem;
import org.usfirst.frc.team2531.robot.subsystems.GearMechanisim;
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
	public static GearMechanisim gear;

	@SuppressWarnings("rawtypes")
	SendableChooser auto;

	Command autocommand;

	public static int min1 = 2, min2 = 254, min3 = 126, max1 = 102, max2 = 255, max3 = 255;

	@Override
	public void robotInit() {
		System.out.println("# Robot");
		RobotMap.cam0 = new Vision("cam0", 0);
		climber = new Climber();
		drive = new DriveSystem();
		gimbal = new Gimbal();
		gear = new GearMechanisim();
		oi = new OI();
		RobotMap.imu.reset();
		RobotMap.heading = 0;
		initSmartDashboard();
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
		//RobotMap.cam0.showLive();
		// RobotMap.cam1.showLive();
		Mat mat = RobotMap.cam0.getImage();
		RobotMap.cam0.setColor(min1, max1, min2, max2, min3, max3);
		ArrayList<Rect> l = RobotMap.cam0.RGBgetBlobs(mat);
		int x = 0;
		int y = 0;
		int size = 0;
		for (int i = 0; i < l.size(); i++) {
			Rect r = l.get(i);
			if (r != null && r.area() > 2000) {
				x = r.x + (r.width / 2);
				y = r.y + (r.height / 2);
				size += 1;
			}
		}
		if (size > 0) {
			x /= size;
			y /= size;
			mat = RobotMap.cam0.showBlobs(mat, l, new Scalar(0, 255, 0));
			Imgproc.line(mat, new Point(x, 0), new Point(x, 480), new Scalar(0, 255, 0), 2);
			Imgproc.line(mat, new Point(0, y), new Point(640, y), new Scalar(0, 255, 0), 2);
			 RobotMap.cam0.putImage(mat);
		}

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
		// RobotMap.cam0.showLive();
	}

	@Override
	public void teleopInit() {
		System.out.println("# Teleop");
		RobotMap.imu.reset();
		RobotMap.heading = 0;
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
		auto.addObject("Vision Tracking", new LineUpGear());
		auto.addObject("Time Drive", new TimeDrive(1000, 0.5));
		auto.addObject("Turn", new Turn2Angle(90));
		auto.addObject("Demo", new Demo());
		auto.addObject("Left", new Left());
		auto.addObject("Center", new Center());
		auto.addObject("Right", new Right());
		SmartDashboard.putData("Autonomous Mode", auto);
		SmartDashboard.putNumber("DesiredHeading", RobotMap.heading);
		SmartDashboard.putNumber("Heading", RobotMap.imu.getAngleZ() / 4);
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("DesiredHeading", RobotMap.heading);
		SmartDashboard.putNumber("Heading", RobotMap.imu.getAngleZ() / 4);
	}
}
