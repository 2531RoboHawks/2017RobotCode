package frclib.vision;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Vision {

	private int min1 = 0, min2 = 0, min3 = 0;
	private int max1 = 0, max2 = 0, max3 = 0;
	private int threash = 0, canny1 = 0, canny2 = 0;

	private CvSink sink;
	private CvSource source;

	public Vision(String name, int dev) {
		UsbCamera cam = new UsbCamera(name, dev);
		cam.setResolution(320, 240);
		sink = CameraServer.getInstance().getVideo(cam);
		source = CameraServer.getInstance().putVideo(name, 320, 240);
	}

	public Vision(String name, int dev, int w, int h) {
		UsbCamera cam = new UsbCamera(name, dev);
		cam.setResolution(w, h);
		sink = CameraServer.getInstance().getVideo(cam);
		source = CameraServer.getInstance().putVideo(name, w, h);
	}

	public Mat getImage() {
		Mat mat = new Mat();
		sink.grabFrame(mat);
		return mat;
	}

	public void putImage(Mat mat) {
		source.putFrame(mat);
		System.gc();
	}

	public void showLive() {
		putImage(getImage());
	}

	public void setColor(int min1, int max1, int min2, int max2, int min3, int max3) {
		this.min1 = min1;
		this.max1 = max1;
		this.min2 = min2;
		this.max2 = max2;
		this.min3 = min3;
		this.max3 = max3;
	}

	public void setCanny(int v1, int v2) {
		this.canny1 = v1;
		this.canny2 = v2;
	}

	public void setThreash(int v) {
		this.threash = v;
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

	public ArrayList<Rect> TRGBgetBlobs(Mat src) {
		Mat mat = src.clone();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2RGB);
		Imgproc.threshold(mat, mat, threash, 255, Imgproc.THRESH_BINARY);
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

	public ArrayList<Rect> TSCGgetBlobs(Mat src) {
		Mat mat = src.clone();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		ArrayList<Mat> split = new ArrayList<Mat>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2RGB);
		Imgproc.threshold(mat, mat, threash, 255, Imgproc.THRESH_BINARY);
		Core.split(mat, split);
		Mat matg = split.get(1);
		Imgproc.Canny(matg, mat, canny1, canny2);
		Imgproc.findContours(mat, c, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		for (int i = 0; i < c.size(); i++) {
			MatOfPoint mop = c.get(i);
			if (mop != null) {
				blobs.add(Imgproc.boundingRect(mop));
			}
		}
		return blobs;
	}

	public ArrayList<Rect> TSCRgetBlobs(Mat src) {
		Mat mat = src.clone();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		ArrayList<Mat> split = new ArrayList<Mat>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2RGB);
		Imgproc.threshold(mat, mat, threash, 255, Imgproc.THRESH_BINARY);
		Core.split(mat, split);
		Mat matr = split.get(0);
		Imgproc.Canny(matr, mat, canny1, canny2);
		Imgproc.findContours(mat, c, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		for (int i = 0; i < c.size(); i++) {
			MatOfPoint mop = c.get(i);
			if (mop != null) {
				blobs.add(Imgproc.boundingRect(mop));
			}
		}
		return blobs;
	}

	public ArrayList<Rect> TSCBgetBlobs(Mat src) {
		Mat mat = src.clone();
		ArrayList<Rect> blobs = new ArrayList<Rect>();
		ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		ArrayList<Mat> split = new ArrayList<Mat>();
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2RGB);
		Imgproc.threshold(mat, mat, threash, 255, Imgproc.THRESH_BINARY);
		Core.split(mat, split);
		Mat matb = split.get(2);
		Imgproc.Canny(matb, mat, canny1, canny2);
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

	public Mat showBlobs(Mat src, ArrayList<Rect> blobs, Scalar color) {
		Mat mat = src.clone();
		for (int i = 0; i < blobs.size(); i++) {
			Rect r = blobs.get(i);
			if (r != null) {
				Imgproc.rectangle(mat, r.tl(), r.br(), color, 1);
			}
		}
		return mat;
	}
}
