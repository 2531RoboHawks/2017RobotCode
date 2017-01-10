package frclib.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class Camera {

	private VideoCapture cam = null;

	public Camera() {
		cam = new VideoCapture();
	}

	public Mat getRawImage() {
		Mat mat = new Mat();
		cam.read(mat);
		return mat;
	}

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
}
