package frclib.vision;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;

public class RobotVision {

	private int min1 = 0, min2 = 0, min3 = 0;
	private int max1 = 0, max2 = 0, max3 = 0;

	public RobotVision() {
		CameraServer.getInstance().startAutomaticCapture();
	}

	public void setColor(int min1, int max1, int min2, int max2, int min3, int max3) {
		this.min1 = min1;
		this.max1 = max1;
		this.min2 = min2;
		this.max2 = max2;
		this.min3 = min3;
		this.max3 = max3;
	}

	public ArrayList<Rect> HLSgetBlobs(Mat src) {
		Mat mat = src.clone();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HLS);
		Core.inRange(mat, new Scalar(min1, min2, min3), new Scalar(max1, max2, max3), mat);
		Imgproc.findContours(mat, c, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		for (int i = 0; i < c.size(); i++) {
			MatOfPoint mop = c.get(i);
			if (mop != null) {
				blobs.add(Imgproc.boundingRect(mop));
			}
		}
		return blobs;
	}

	public ArrayList<Rect> HSVgetBlobs(Mat src) {
		Mat mat = src.clone();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HSV);
		Core.inRange(mat, new Scalar(min1, min2, min3), new Scalar(max1, max2, max3), mat);
		Imgproc.findContours(mat, c, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		for (int i = 0; i < c.size(); i++) {
			MatOfPoint mop = c.get(i);
			if (mop != null) {
				blobs.add(Imgproc.boundingRect(mop));
			}
		}
		return blobs;
	}

	public ArrayList<Rect> RGBgetBlobs(Mat src) {
		Mat mat = src.clone();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2RGB);
		Core.inRange(mat, new Scalar(min1, min2, min3), new Scalar(max1, max2, max3), mat);
		Imgproc.findContours(mat, c, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		for (int i = 0; i < c.size(); i++) {
			MatOfPoint mop = c.get(i);
			if (mop != null) {
				blobs.add(Imgproc.boundingRect(mop));
			}
		}
		return blobs;
	}

	public ArrayList<Rect> HLSgetBlobs() {
		Mat mat = getRawImage();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HLS);
		Core.inRange(mat, new Scalar(min1, min2, min3), new Scalar(max1, max2, max3), mat);
		Imgproc.findContours(mat, c, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		for (int i = 0; i < c.size(); i++) {
			MatOfPoint mop = c.get(i);
			if (mop != null) {
				blobs.add(Imgproc.boundingRect(mop));
			}
		}
		return blobs;
	}

	public ArrayList<Rect> HSVgetBlobs() {
		Mat mat = getRawImage();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2HSV);
		Core.inRange(mat, new Scalar(min1, min2, min3), new Scalar(max1, max2, max3), mat);
		Imgproc.findContours(mat, c, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		for (int i = 0; i < c.size(); i++) {
			MatOfPoint mop = c.get(i);
			if (mop != null) {
				blobs.add(Imgproc.boundingRect(mop));
			}
		}
		return blobs;
	}

	public ArrayList<Rect> RGBgetBlobs() {
		Mat mat = getRawImage();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2RGB);
		Core.inRange(mat, new Scalar(min1, min2, min3), new Scalar(max1, max2, max3), mat);
		Imgproc.findContours(mat, c, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		for (int i = 0; i < c.size(); i++) {
			MatOfPoint mop = c.get(i);
			if (mop != null) {
				blobs.add(Imgproc.boundingRect(mop));
			}
		}
		return blobs;
	}

	public static double getDistance(Rect rect, double fov, int objectwidth, int imagewidth) {
		if (rect != null) {
			double d = objectwidth * imagewidth / (2 * rect.width * Math.tan(fov));
			return d;
		}
		return 0;
	}

	public static Mat getRawImage() {
		Mat mat = new Mat();
		CameraServer.getInstance().getVideo().grabFrame(mat);
		return mat;
	}

	public static void putImage(Mat mat) {
		CvSource out = CameraServer.getInstance().putVideo("Vision Tracking", 640, 480);
		out.putFrame(mat);
	}
}
