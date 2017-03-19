package org.usfirst.frc.team4955.robot.commands.autonomous.vision;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.vision.VisionThread.VisionState;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ApprocheTarget extends VisionCenterBase {

	VisionState	visionState;
	double		distance;
	double[]	lastDistances;
	int			distanceIndex;

	public ApprocheTarget(VisionState visionState, double distance, double centerOffset) {
		super(centerOffset);
		this.visionState = visionState;
		this.distance = distance;
	}

	protected void initialize() {
		Robot.cameraSubsystem.visionThread.setVisionState(visionState);
		lastDistances = new double[10];
	}

	@Override
	protected void execute() {
		double rotation = GetRotation(0.55);

		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS,
				"Approching target at " + vt.targetDistance + "\" getting to " + distance + "\"");

		RobotMap.driveTrain.arcadeDrive(0.6, rotation);
	}

	@Override
	protected boolean isFinished() {
		return vt.targetCenterNormalised != null && vt.targetDistance < distance;
	}

	@Override
	protected void end() {
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Target at " + distance + "\"");
	}
}
