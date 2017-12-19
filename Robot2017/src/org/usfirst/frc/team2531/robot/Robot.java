package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.DriveSquare;
import org.usfirst.frc.team2531.robot.commands.TimeDrive;
import org.usfirst.frc.team2531.robot.commands.TimeDriveGyro;
import org.usfirst.frc.team2531.robot.commands.TrackR;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
import org.usfirst.frc.team2531.robot.subsystems.DriveSystem;

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

	public static int min1 = 100, min2 = 0, min3 = 0, max1 = 255, max2 = 20, max3 = 20, w = 720, h = 480,
			minsize = 1000;

	SendableChooser<Command> auto;
	Command autocommand;

	@Override
	public void robotInit() {
		System.out.println("# Robot");
		drive = new DriveSystem();
		oi = new OI();
		RobotMap.imu.calibrate();
		RobotMap.imu.reset();
		initSmartDashboard();
		RobotMap.cam0 = new Vision("cam0", 0, w, h);
	}

	@Override
	public void disabledInit() {
		System.out.println("# Disabled");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void autonomousInit() {
		System.out.println("# Autonomous");
		RobotMap.imu.reset();
		autocommand = (Command) auto.getSelected();
		if (autocommand != null) {
			autocommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
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
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
		updateSmartDashboard();
	}

	public void initSmartDashboard() {
		auto = new SendableChooser<Command>();
		auto.addDefault("No Auto", null);
		auto.addObject("Time Drive 1sec", new TimeDrive(1000, 0.5));
		auto.addObject("Time Drive Gyro 2sec", new TimeDriveGyro(2000, 0.5));
		auto.addObject("Turn 90deg", new Turn2Angle(90));
		auto.addObject("Drive Square", new DriveSquare(2000, 0.5));
		auto.addObject("VT", new TrackR(false));
		SmartDashboard.putData("Autonomous Mode", auto);
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());
		drive.print();
	}

}