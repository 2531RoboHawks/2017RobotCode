package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private Talon m1 = new Talon(4);
	private Talon m2 = new Talon(5);

	public void initDefaultCommand() {
	}

	public void set(double p) {
		m1.set(p);
		m2.set(p);
	}

	public void stop() {
		m1.set(0);
		m2.set(0);
	}

}
