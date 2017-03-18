package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.MoveClimber;
import org.usfirst.frc.team2531.robot.commands.MoveTrackY;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick gamepad = new Joystick(0);
	public static Joystick axis = new Joystick(1);

	public static JoystickButton A = new JoystickButton(gamepad, 1);
	public static JoystickButton B = new JoystickButton(gamepad, 2);
	public static JoystickButton X = new JoystickButton(gamepad, 3);
	public static JoystickButton Y = new JoystickButton(gamepad, 4);
	public static JoystickButton A2 = new JoystickButton(axis, 2);
	/*
	 * public static JoystickButton A = new JoystickButton(axis, 1); public
	 * static JoystickButton B = new JoystickButton(axis, 2); public static
	 * JoystickButton X = new JoystickButton(axis, 3); public static
	 * JoystickButton Y = new JoystickButton(axis, 4);
	 */

	// public static JoystickButton L1 = new JoystickButton(left, 1);
	// public static JoystickButton L2 = new JoystickButton(left, 2);
	// public static JoystickButton L3 = new JoystickButton(left, 3);
	// public static JoystickButton L4 = new JoystickButton(left, 4);
	// public static JoystickButton L5 = new JoystickButton(left, 5);

	// public static JoystickButton R1 = new JoystickButton(right, 1);
	// public static JoystickButton R2 = new JoystickButton(right, 2);
	// public static JoystickButton R3 = new JoystickButton(right, 3);
	// public static JoystickButton R4 = new JoystickButton(right, 4);
	// public static JoystickButton R5 = new JoystickButton(right, 5);

	public OI() {
		X.whileHeld(new MoveClimber(true));
		A.whileActive(new MoveTrackY(true));

	}
}
