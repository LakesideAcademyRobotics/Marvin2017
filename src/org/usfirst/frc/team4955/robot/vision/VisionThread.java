package org.usfirst.frc.team4955.robot.vision;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.utils.MathUtils;
import org.usfirst.frc.team4955.robot.utils.utils.SmarterDashboard;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionThread extends Thread {

	GripPipeline	gripPipeline;
	Mat				mat;
	CvSource		outputStream;
	long			nextProcess;
	CvSink			cvSink;

	Scalar	GearColor	= new Scalar(0, 255, 0);
	Scalar	BoilerColor	= new Scalar(0, 255, 255);

	public boolean	showSimpleRects	= true;
	public boolean	showMergedRects	= true;
	public boolean	showTarget		= true;

	public Rect		target;
	public Point	targetCenterNormalised;
	public double	targetDistance;

	public enum VisionState {
		None, Boiler, Gear, Both
	}

	private VisionState state;

	public void setVisionState(VisionState newState) {
		if (newState.equals(VisionState.Boiler)) {
			cvSink = CameraServer.getInstance().getVideo(RobotMap.backCamera);
		} else if (newState.equals(VisionState.Gear)) {
			cvSink = CameraServer.getInstance().getVideo(RobotMap.frontCamera);
		}
	}

	public VisionThread() {
		gripPipeline = new GripPipeline();
		mat = new Mat();
		nextProcess = 0;
		state = VisionState.Gear;
	}

	@Override
	public void run() {
		// Get a CvSink. This will capture Mats from the camera
		cvSink = CameraServer.getInstance().getVideo(RobotMap.frontCamera);

		// Setup a CvSource. This will send images back to the Dashboard
		outputStream = CameraServer.getInstance().putVideo("Vision", 640, 480);

		// This cannot be 'true'. The program will never exit if it is. This
		// lets the robot stop this thread when restarting robot code or
		// deploying.
		while (!Thread.interrupted()) {
			// System.out.println(System.currentTimeMillis() + " - " +
			// nextProcess);

			if (System.currentTimeMillis() > nextProcess) {
				nextProcess = System.currentTimeMillis() + 250;

				if (cvSink.grabFrame(mat) == 0) {
					outputStream.notifyError(cvSink.getError());
					continue;
				}

				processImage();

				target = null;
				targetDistance = 0;
				if (state.equals(VisionState.Gear)) {
					target = findGearSlot();
					showPrint(target, GearColor);
				} else if (state.equals(VisionState.Boiler)) {
					target = findBoiler();
					showPrint(target, BoilerColor);
				} else if (state.equals(VisionState.Both)) {
					showPrint(findGearSlot(), GearColor);
					showPrint(findBoiler(), BoilerColor);
				} else {
					SmarterDashboard.putNumber(DashboardKeys.VISION_DISTANCE, 0, "%.2f%n");
				}
				if (target != null) {
					targetCenterNormalised = new Point((target.x + target.width / 2.0) / 640.0 * 2,
							(target.y + target.height / 2.0) / 480.0 * 2);
					targetDistance = Constants.VISION_DISTANCE_RATIO / target.height;
					SmartDashboard.putString(DashboardKeys.VISION_DISTANCE, MathUtils.formatInch(targetDistance));
				} else
					targetCenterNormalised = null;

				// Give the output stream a new image to display
				outputStream.putFrame(mat);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}

		}
	}

	private void processImage() {
		gripPipeline.process(mat);

		if (showSimpleRects) {
			int i = 0;
			for (MatOfPoint mop : gripPipeline.filterContoursOutput()) {
				int bob = 255 / gripPipeline.filterContoursOutput().size();
				draw(mop, new Scalar(255 - i * bob, 0, i * bob), 2);
				i++;
			}
		}

	}

	public void showPrint(Rect rect, Scalar color) {
		if (rect == null) {
			return;
		}
		draw(rect, color, 5);
	}

	public Rect findBoiler() {
		ArrayList<MatOfPoint> mops = gripPipeline.filterContoursOutput();
		System.out.println(mops.size());
		for (int i = 0; i < mops.size() - 1; i++) {
			for (int j = i + 1; j < mops.size(); j++) {
				Rect recti = Imgproc.boundingRect(mops.get(i));
				Rect rectj = Imgproc.boundingRect(mops.get(j));
				if (MathUtils.equalEpsilon(recti.x, rectj.x, 15)
						&& MathUtils.equalEpsilon(recti.width, rectj.width, 15)) {

					Rect merged = mergeRect(recti, rectj);
					if (showMergedRects)
						draw(merged, new Scalar(255, 255, 255), 3);
					if (MathUtils.equalEpsilon(1.0 * merged.width / merged.height, 1, 0.4)) {
						return merged;
					}
				}
			}
		}
		return null;
	}

	public Rect findGearSlot() {
		ArrayList<MatOfPoint> mops = gripPipeline.filterContoursOutput();

		for (int i = 0; i < mops.size() - 1; i++) {
			for (int j = i + 1; j < mops.size(); j++) {
				Rect recti = Imgproc.boundingRect(mops.get(i));
				Rect rectj = Imgproc.boundingRect(mops.get(j));
				// Igniore top %
				if (recti.y < 480 / 2 * Constants.VISION_IGNORE_TOP_FOR_GEAR)
					continue;

				if (MathUtils.equalEpsilon(recti.y, rectj.y, 15)
						&& MathUtils.equalEpsilon(recti.width, rectj.width, 15)) {
					Rect merged = mergeRect(recti, rectj);
					if (showMergedRects)
						draw(merged, new Scalar(255, 255, 255), 3);
					if (MathUtils.equalEpsilon(1.0 * merged.width / merged.height, 1.9, 0.25)) {
						return merged;
					}
				}
			}
		}
		return null;
	}

	private void draw(Rect r1, Rect r2, Scalar color, int size) {
		draw(mergeRect(r1, r2), color, size);
	}

	private Rect mergeRect(Rect r1, Rect r2) {

		// int x = (r1.x + r2.x) / 2;
		// int y = Math.min(r1.y, r2.y);
		// int width = (r1.width + r2.width) / 2;
		// int height = (r1.height + r2.height) / 2 + Math.abs(r1.y - r2.y);

		int x = Math.min(r1.x, r2.x);
		int y = Math.min(r1.y, r2.y);
		int width = Math.max(r1.x + r1.width, r2.x + r1.width) - x;
		int height = Math.max(r1.y + r1.height, r2.y + r1.height) - y;
		return new Rect(x, y, width, height);
	}

	private void draw(Rect r, Scalar color, int size) {
		int x = r.x * 2;
		int y = r.y * 2;
		int width = r.width * 2;
		int height = r.height * 2;
		Point bottomLeft = new Point(x, y);
		Point topRight = new Point(x + width, y + height);
		Imgproc.rectangle(mat, bottomLeft, topRight, color, size);
	}

	private void draw(MatOfPoint mop, Scalar color, int size) {
		Rect r = Imgproc.boundingRect(mop);
		int x = r.x * 2;
		int y = r.y * 2;
		int width = r.width * 2;
		int height = r.height * 2;
		Point bottomLeft = new Point(x, y);
		Point topRight = new Point(x + width, y + height);
		Imgproc.rectangle(mat, bottomLeft, topRight, color, size);

	}

}
