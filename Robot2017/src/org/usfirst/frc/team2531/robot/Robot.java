
package org.usfirst.frc.team2531.robot;

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
		RobotMap.cam1 = new Vision("cam1", 1);
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
