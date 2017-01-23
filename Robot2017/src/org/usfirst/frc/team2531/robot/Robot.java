
package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.Demo;
import org.usfirst.frc.team2531.robot.commands.TimeDrive;
import org.usfirst.frc.team2531.robot.commands.Track;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
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

	public int min1 = 80, min2 = 180, min3 = 180, max1 = 140, max2 = 255, max3 = 255;

	@Override
	public void robotInit() {
		System.out.println("# Robot");
		RobotMap.cam0 = new Vision("cam0", 0);
		climber = new Climber();
		drive = new DriveSystem();
		gimbal = new Gimbal();
		oi = new OI();
		RobotMap.imu.calibrate();
		RobotMap.imu.reset();
		RobotMap.heading = 0;
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
		RobotMap.cam0.showLive();
		// RobotMap.cam1.showLive();
		/*
		 * Mat mat = RobotMap.cam0.getImage(); RobotMap.cam0.setColor(min1 + b,
		 * max1 + b, min2 + b, max2 + b, min3 + b, max3 + b); ArrayList<Rect> l
		 * = RobotMap.cam0.filterArea(RobotMap.cam0.RGBgetBlobs(mat), 1000); int
		 * x = 0; int y = 0; for (int i = 0; i < l.size(); i++) { Rect r =
		 * l.get(i); if (r != null) { x = r.x + (r.width / 2); y = r.y +
		 * (r.height / 2); } } if (!l.isEmpty()) { x /= l.size(); y /= l.size();
		 * mat = RobotMap.cam0.showBlobs(mat, l, new Scalar(0, 255, 0));
		 * //Imgproc.line(mat, new Point(x, 0), new Point(x, 480), new Scalar(0,
		 * 255, 0), 2); //Imgproc.line(mat, new Point(0, y), new Point(640, y),
		 * new Scalar(0, 255, 0), 2); RobotMap.cam0.putImage(mat); }
		 */

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
		/*
		 * Mat mat = RobotMap.cam0.getImage(); RobotMap.cam0.setColor(min1 + b,
		 * max1 + b, min2 + b, max2 + b, min3 + b, max3 + b); ArrayList<Rect> l
		 * = RobotMap.cam0.filterArea(RobotMap.cam0.RGBgetBlobs(mat), 1000); int
		 * x = 0; int y = 0; for (int i = 0; i < l.size(); i++) { Rect r =
		 * l.get(i); if (r != null) { x = r.x + (r.width / 2); y = r.y +
		 * (r.height / 2); } } if (!l.isEmpty()) { x /= l.size(); y /= l.size();
		 * mat = RobotMap.cam0.showBlobs(mat, l, new Scalar(0, 255, 0));
		 * Imgproc.line(mat, new Point(x, 0), new Point(x, 480), new Scalar(0,
		 * 255, 0), 2); Imgproc.line(mat, new Point(0, y), new Point(640, y),
		 * new Scalar(0, 255, 0), 2); RobotMap.cam0.putImage(mat); }
		 */
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
		auto.addObject("Time Drive", new TimeDrive(1000, 0.5));
		auto.addObject("Turn", new Turn2Angle(90));
		auto.addObject("Demo", new Demo());
		SmartDashboard.putData("Autonomous Mode", auto);
		SmartDashboard.putNumber("DesiredHeading", RobotMap.heading);
		SmartDashboard.putNumber("Heading", RobotMap.imu.getAngleZ());
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("DesiredHeading", RobotMap.heading);
		SmartDashboard.putNumber("Heading", RobotMap.imu.getAngleZ());
	}
}
