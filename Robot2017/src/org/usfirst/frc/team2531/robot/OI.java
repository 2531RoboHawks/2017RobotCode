package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.Climb;
import org.usfirst.frc.team2531.robot.commands.Track;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick gamepad = new Joystick(0);

	public OI() {
		JoystickButton up = new JoystickButton(gamepad, 1);
		JoystickButton down = new JoystickButton(gamepad, 4);
		JoystickButton track = new JoystickButton(gamepad, 6);
		up.whileHeld(new Climb(true));
		down.whileHeld(new Climb(false));
		track.whileHeld(new Track(false));
	}
}
