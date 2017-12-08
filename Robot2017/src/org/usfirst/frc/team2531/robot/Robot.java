
package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.TimeDrive;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
import org.usfirst.frc.team2531.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveSystem drive;

	@SuppressWarnings("rawtypes")
	SendableChooser auto;
	Command autocommand;

	public static double angle = 0;

	@Override
	public void robotInit() {
		System.out.println("# Robot");
		drive = new DriveSystem();
		oi = new OI();
		RobotMap.imu.calibrate();
		RobotMap.imu.reset();
		initSmartDashboard();
	}

	@Override
	public void disabledInit() {
		System.out.println("# Disabled");
		RobotMap.imu.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		updateVar();
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
		updateVar();
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
		updateVar();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
		updateSmartDashboard();
		updateVar();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initSmartDashboard() {
		auto = new SendableChooser();
		auto.addDefault("No Auto", null);
		auto.addObject("Time Drive 1sec", new TimeDrive(1000, 0.5));
		auto.addObject("Turn 90deg", new Turn2Angle(90));
		SmartDashboard.putData("Autonomous Mode", auto);
		SmartDashboard.putNumber("AngleX", (RobotMap.imu.getAngleX() / 4) % 360);
		SmartDashboard.putNumber("AngleY", (RobotMap.imu.getAngleY() / 4) % 360);
		SmartDashboard.putNumber("AngleZ", (RobotMap.imu.getAngleZ() / 4) % 360);
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("AngleX", (RobotMap.imu.getAngleX() / 4) % 360);
		SmartDashboard.putNumber("AngleY", (RobotMap.imu.getAngleY() / 4) % 360);
		SmartDashboard.putNumber("AngleZ", (RobotMap.imu.getAngleZ() / 4) % 360);
	}

	public void updateVar() {
		angle = (RobotMap.imu.getAngleX() / 4) % 360;
	}

}