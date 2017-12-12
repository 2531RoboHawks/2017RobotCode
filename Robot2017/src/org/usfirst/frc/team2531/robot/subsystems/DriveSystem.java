package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.Drive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSystem extends Subsystem {

	private CANTalon FL = new CANTalon(1);
	private CANTalon FR = new CANTalon(2);
	private CANTalon BL = new CANTalon(3);
	private CANTalon BR = new CANTalon(4);

	private double powFL = 0, powFR = 0, powBL = 0, powBR = 0;

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void axisdrive(double x, double y, double r) {
		double a = Math.atan2(y, x);
		powFL = -(Math.abs(Math.sin(a)) * y) + (Math.abs(Math.cos(a)) * x) + r;
		powFR = (Math.abs(Math.sin(a)) * y) + (Math.abs(Math.cos(a)) * x) + r;
		powBL = -(Math.abs(Math.sin(a)) * y) - (Math.abs(Math.cos(a)) * x) + r;
		powBR = (Math.abs(Math.sin(a)) * y) - (Math.abs(Math.cos(a)) * x) + r;
		FL.set(powFL);
		FR.set(powFR);
		BL.set(powBL);
		BR.set(powBR);
	}

	public void orientationdrive(double x, double y, double r, double angle) {
		double cos = Math.cos(Math.toRadians(angle));
		double sin = Math.sin(Math.toRadians(angle));
		double rotx = x * cos - y * sin;
		double roty = x * sin + y * cos;
		powFL = rotx - roty + r;
		powFR = rotx + roty + r;
		powBL = -rotx - roty + r;
		powBR = -rotx + roty + r;
		FL.set(powFL);
		FR.set(powFR);
		BL.set(powBL);
		BR.set(powBR);
	}

	public void stop() {
		powFL = 0;
		powFR = 0;
		powBL = 0;
		powBR = 0;
		FL.set(powFL);
		FR.set(powFR);
		BL.set(powBL);
		BR.set(powBR);
	}

	public void print() {
		SmartDashboard.putNumber("FL", powFL);
		SmartDashboard.putNumber("FR", powFR);
		SmartDashboard.putNumber("BL", powBL);
		SmartDashboard.putNumber("BR", powBR);
	}
}
