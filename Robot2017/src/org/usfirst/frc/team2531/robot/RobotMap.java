package org.usfirst.frc.team2531.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import frclib.sensors.ADIS16448;
import frclib.vision.Vision;

public class RobotMap {

	public static Vision cam0;
	public static Vision cam1;

	public static double heading = 0;

	public static ADIS16448 imu = new ADIS16448();

	public static DigitalInput hopperup = new DigitalInput(0);
	public static DigitalInput hopperdown = new DigitalInput(1);

}
