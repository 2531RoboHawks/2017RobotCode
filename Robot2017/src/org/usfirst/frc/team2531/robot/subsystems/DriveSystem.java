package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.Drive;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Jaguar FL = new Jaguar(0);
	private Jaguar FR = new Jaguar(1);
	private Jaguar BL = new Jaguar(2);
	private Jaguar BR = new Jaguar(3);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new Drive());
	}

	public void axisdrive(double x, double y, double r) {
		FL.set(-y + x + r);
		FR.set(y + x + r);
		BL.set(-y - x + r);
		BR.set(y - y + r);
	}

	public void axisdrive2(double x, double y, double r) {
		double a = Math.atan2(y, x);
		FL.set(-Math.sin(a) + Math.cos(a) + r);
		FR.set(Math.sin(a) + Math.cos(a) + r);
		BL.set(-Math.sin(a) - Math.cos(a) + r);
		BR.set(Math.sin(a) - Math.cos(a) + r);
	}
}
