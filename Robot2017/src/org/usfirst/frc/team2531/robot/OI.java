package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.Climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick gamepad = new Joystick(0);

	public static JoystickButton A = new JoystickButton(gamepad, 1);
	public static JoystickButton B = new JoystickButton(gamepad, 2);
	public static JoystickButton X = new JoystickButton(gamepad, 3);
	public static JoystickButton Y = new JoystickButton(gamepad, 4);

	public OI() {
		A.whileHeld(new Climb(true));
		Y.whileHeld(new Climb(false));
	}
}
