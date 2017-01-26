package frclib.drive;

import edu.wpi.first.wpilibj.SpeedController;

public class DriveSystem2 {

	private SpeedController L, R;

	public DriveSystem2(SpeedController leftmotor, SpeedController rightmotor) {
		L = leftmotor;
		L.setInverted(true);
		R = rightmotor;
		R.setInverted(false);
	}

	public void tankDrive(double left, double right) {
		L.set(left);
		R.set(right);
	}

	public void arcadeDrive(double forward, double rotation) {
		double left = forward - (rotation / 2);
		double right = forward + (rotation / 2);
		L.set(left);
		R.set(right);
	}
	
	public void stop(){
		L.stopMotor();
		R.stopMotor();
	}
}
