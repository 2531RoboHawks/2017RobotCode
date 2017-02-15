package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.ComHopper;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick gamepad = new Joystick(0);
	public static JoystickButton A = new JoystickButton(gamepad, 1);

	public OI() {
		A.whileHeld(new ComHopper());
	}

}
