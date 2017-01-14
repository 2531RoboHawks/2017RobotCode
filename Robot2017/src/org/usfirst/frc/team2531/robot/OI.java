package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.Climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick gamepad = new Joystick(0);

	public OI() {
		JoystickButton up = new JoystickButton(gamepad, 1);
		JoystickButton down = new JoystickButton(gamepad, 4);
		up.whileHeld(new Climb(true));
		down.whileHeld(new Climb(false));
	}
}
