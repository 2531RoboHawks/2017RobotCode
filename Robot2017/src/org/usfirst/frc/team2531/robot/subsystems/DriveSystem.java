package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.Drive3Axis;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSystem extends Subsystem {

	private CANTalon FL = new CANTalon(1);
	private CANTalon FR = new CANTalon(2);
	private CANTalon BL = new CANTalon(3);
	private CANTalon BR = new CANTalon(4);

	public void initDefaultCommand() {
		setDefaultCommand(new Drive3Axis());
	}

	public void axisdrive(double x, double y, double r) {
		double a = Math.atan2(y, x);
		FL.set(-(Math.abs(Math.sin(a)) * y) + (Math.abs(Math.cos(a)) * x) + r);
		FR.set((Math.abs(Math.sin(a)) * y) + (Math.abs(Math.cos(a)) * x) + r);
		BL.set(-(Math.abs(Math.sin(a)) * y) - (Math.abs(Math.cos(a)) * x) + r);
		BR.set((Math.abs(Math.sin(a)) * y) - (Math.abs(Math.cos(a)) * x) + r);
	}

	public void orientationdrive(double x, double y, double r, double angle) {
		double cos = Math.cos(Math.toRadians(angle));
		double sin = Math.sin(Math.toRadians(angle));
		double rotx = x * cos - y * sin;
		double roty = x * sin + y * cos;
		FL.set(rotx - roty + r);
		FR.set(rotx + roty + r);
		BL.set(-rotx - roty + r);
		BR.set(-rotx + roty + r);
	}

	public void stop() {
		FL.set(0);
		FR.set(0);
		BL.set(0);
		BR.set(0);
	}
}
