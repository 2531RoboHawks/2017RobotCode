package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.DeployGear;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearMechanisim extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Solenoid off = new Solenoid(0);
	Solenoid on = new Solenoid(1);

	public void set(boolean b) {
		on.set(b);
		off.set(!b);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DeployGear());
	}
}
