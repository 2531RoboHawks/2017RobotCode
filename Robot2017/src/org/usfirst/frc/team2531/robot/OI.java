package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.MoveGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick gamepad = new Joystick(0);

	public OI() {
		JoystickButton on = new JoystickButton(gamepad, 1);
		JoystickButton off = new JoystickButton(gamepad, 4);
		on.whileHeld(new MoveGear(true));
		off.whileHeld(new MoveGear(false));
	}
}
