package org.usfirst.frc.team4955.robot.vision;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4955.robot.utils.utils.Pair;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision {

	public void something() {

		BoilderGripPipeline boiler = new BoilderGripPipeline();

		ArrayList<MatOfPoint> contours = boiler.findContoursOutput();

		if (contours.size() < 2) {
			SmartDashboard.putString("Vision test", "Only 1 target");

		} else {
			process(contours.get(0), contours.get(1));
		}
	}

	public void ProcessBoiler() {
		BoilderGripPipeline boiler = new BoilderGripPipeline();

		Pair<Rect, Rect> topAndBottom = findRectPairTopBottom(boiler.findContoursOutput());

		if (topAndBottom == null) {
			// Found notthing
		} else {
			Rect bottom = topAndBottom.x;
			Rect top = topAndBottom.y;
			// TODO normalise that!
			// return bottom.x;
		}
	}

	private Pair<Rect, Rect> findRectPairTopBottom(ArrayList<MatOfPoint> contours) {
		if (contours.size() < 2)
			return null;

		boolean foundATop = false;
		boolean foundABottom = false;
		Rect top = new Rect(0, 0, 1, 1);
		Rect bottom = new Rect(0, Integer.MAX_VALUE, 1, 1);
		for (MatOfPoint matOfPoint : contours) {
			Rect r = Imgproc.boundingRect(matOfPoint);
			if (r.y > top.y) {
				top = r;
				foundATop = true;
			}
			if (r.y < bottom.y) {
				bottom = r;
				foundABottom = true;
			}
		}
		if (foundABottom && foundATop) {
			return new Pair<Rect, Rect>(bottom, top);
		} else {
			return null;
		}

	}

	private Pair<Rect, Rect> findRectPairLeftRight(ArrayList<MatOfPoint> contours) {
		if (contours.size() < 2)
			return null;

		boolean foundARight = false;
		boolean foundALeft = false;
		Rect left = new Rect(Integer.MAX_VALUE, 0, 1, 1);
		Rect right = new Rect(0, 0, 1, 1);
		for (MatOfPoint matOfPoint : contours) {
			Rect r = Imgproc.boundingRect(matOfPoint);
			if (r.x > right.x) {
				right = r;
				foundARight = true;
			}
			if (r.x < left.x) {
				left = r;
				foundALeft = true;
			}
		}
		if (foundALeft && foundARight) {
			return new Pair<Rect, Rect>(left, right);
		} else {
			return null;
		}

	}

	private void process(MatOfPoint top, MatOfPoint bottom) {
		process(Imgproc.boundingRect(top), Imgproc.boundingRect(bottom));
	}

	private void process(Rect top, Rect bottom) {

	}
}
