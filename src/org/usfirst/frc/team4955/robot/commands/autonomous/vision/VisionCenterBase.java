package org.usfirst.frc.team4955.robot.commands.autonomous.vision;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.vision.VisionThread;

import edu.wpi.first.wpilibj.command.Command;

public abstract class VisionCenterBase extends Command {

	protected VisionThread	vt;
	protected double		centerOffset;

	public VisionCenterBase(double centerOffset) {
		this.vt = Robot.cameraSubsystem.visionThread;
		this.centerOffset = centerOffset;
	}

	protected double GetRotation(double turnSpeed) {
		double cx = GetCenter();

		double rotation = 0;
		double centerCam = 0.5 + centerOffset;
		if (vt.targetCenterNormalised == null) {

		} else if (cx < centerCam) {
			double t = 2 * (centerCam - cx);
			rotation = Constants.EaseTurn(t, turnSpeed);
		} else {
			double t = 1 - 2 * (1 - cx);
			rotation = -Constants.EaseTurn(t, turnSpeed);
		}
		return rotation;
	}

	protected double GetCenter() {
		if (vt.targetCenterNormalised == null) {
			return -1;
		} else {
			return vt.targetCenterNormalised.x + centerOffset;
		}
	}
}
