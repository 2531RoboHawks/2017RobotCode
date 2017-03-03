package org.usfirst.frc.team2531.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hopper extends Subsystem {

	private CANTalon m = new CANTalon(5);

	public void initDefaultCommand() {
	}

	public void set(double pow) {
		m.set(pow);
	}

	public void stop() {
		m.set(0);
	}
}
