package frclib.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.CameraServer;

public class Camera {

	public Camera() {
		CameraServer.getInstance().startAutomaticCapture();
	}

	public Mat getRawImage() {
		Mat mat = new Mat();
		CameraServer.getInstance().getVideo().grabFrame(mat);
		return mat;
	}

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
}
