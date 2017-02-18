package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.MoveGDU;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GDU extends Subsystem {

	Solenoid in = new Solenoid(0);
	Solenoid out = new Solenoid(1);
	Solenoid off = new Solenoid(2);
	Solenoid on = new Solenoid(3);

	public void set(boolean b, boolean o) {
		on.set(b);
		off.set(!b);
		in.set(!o);
		out.set(o);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new MoveGDU());
	}
}
