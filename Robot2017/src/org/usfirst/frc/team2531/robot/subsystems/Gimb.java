package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frclib.time.Time;

/**
 *
 */
public class Gimb extends Subsystem {
	private Servo serv = new Servo(1);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setPosUP(boolean pressed) {
		while (pressed == true) {
			serv.set(serv.get() + .01);
			Time.delayMiliseconds(100);
		}
	}

	public void setPosDOWN(boolean pressed) {
		while (pressed == true) {
			serv.set(serv.get() - .01);
			Time.delayMiliseconds(100);
		}
	}

	public void setAngUP(boolean pressed) {
		while (pressed == true) {
			serv.setAngle(serv.getAngle() + 1);
			Time.delayMiliseconds(1000);
		}

	}

	public void setAngDOWN(boolean pressed) {
		while (pressed == true) {
			serv.setAngle(serv.getAngle() - 1);
			Time.delayMiliseconds(1000);
		}
	}
}