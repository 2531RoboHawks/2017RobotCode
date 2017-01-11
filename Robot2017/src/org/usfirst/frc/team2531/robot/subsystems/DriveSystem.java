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
		double a = Math.atan2(y, x);
		FL.set(-(Math.abs(Math.sin(a)) * y) + (Math.abs(Math.cos(a)) * x) + r);
		FR.set((Math.abs(Math.sin(a)) * y) + (Math.abs(Math.cos(a)) * x) + r);
		BL.set(-(Math.abs(Math.sin(a)) * y) - (Math.abs(Math.cos(a)) * x) + r);
		BR.set((Math.abs(Math.sin(a)) * y) - (Math.abs(Math.cos(a)) * x) + r);
	}
}
