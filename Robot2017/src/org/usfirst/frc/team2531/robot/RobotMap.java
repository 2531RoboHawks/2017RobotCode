package org.usfirst.frc.team2531.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import frclib.sensors.ADIS16448_IMU;
import frclib.vision.Vision;

public class RobotMap {

	public static Vision cam0;
	public static Vision cam1;

	public static double heading = 0;

	public static ADIS16448_IMU imu = new ADIS16448_IMU();

	public static DigitalInput hopperup = new DigitalInput(0);
	public static DigitalInput hopperdown = new DigitalInput(1);

}
