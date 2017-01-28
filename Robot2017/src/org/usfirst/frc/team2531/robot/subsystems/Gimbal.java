package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.MoveGimbal;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gimbal extends Subsystem {

	private Servo rotate = new Servo(6);

	public void initDefaultCommand() {
		setDefaultCommand(new MoveGimbal());
	}

	public void setAngles(double pan) {
		rotate.set(pan);
	}
}
