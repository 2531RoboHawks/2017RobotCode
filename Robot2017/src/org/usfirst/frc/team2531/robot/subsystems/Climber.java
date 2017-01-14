package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private Talon m1 = new Talon(4);
	private Talon m2 = new Talon(5);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void set(double p) {
		m1.set(p);
		m2.set(p);
	}
}
