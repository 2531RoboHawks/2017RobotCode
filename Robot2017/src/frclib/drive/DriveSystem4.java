package frclib.drive;

import edu.wpi.first.wpilibj.SpeedController;

public class DriveSystem4 {

	private SpeedController FL, FR, BL, BR;

	public DriveSystem4(SpeedController frontleftmotor, SpeedController frontrightmotor, SpeedController backleftmotor,
			SpeedController backrightmotor) {
		FL = frontleftmotor;
		FL.setInverted(true);
		FR = frontrightmotor;
		FR.setInverted(false);
		BL = backleftmotor;
		BL.setInverted(true);
		BR = frontleftmotor;
		BR.setInverted(false);
	}

	public void axisDrive(double x, double y, double z) {
		FL.set(y + x - z);
		FR.set(y - x + z);
		BL.set(y - x - z);
		BR.set(y + x + z);
	}

	public void tankDrive(double left, double right) {
		FL.set(left);
		BL.set(left);
		FR.set(right);
		BR.set(right);
	}

	public void arcadeDrive(double forward, double rotation) {
		double left = forward - (rotation / 2);
		double right = forward + (rotation / 2);
		FL.set(left);
		BL.set(left);
		FR.set(right);
		BR.set(right);
	}

	public void stop() {
		FL.stopMotor();
		BL.stopMotor();
		FR.stopMotor();
		BR.stopMotor();
	}
}
