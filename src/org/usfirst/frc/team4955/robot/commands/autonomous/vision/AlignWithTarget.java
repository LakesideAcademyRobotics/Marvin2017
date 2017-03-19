package org.usfirst.frc.team4955.robot.commands.autonomous.vision;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.utils.MathUtils;
import org.usfirst.frc.team4955.robot.vision.VisionThread.VisionState;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AlignWithTarget extends VisionCenterBase {

	VisionState visionState;

	public AlignWithTarget(VisionState visionState, double centerOffset) {
		super(centerOffset);
		this.visionState = visionState;
	}

	protected void initialize() {
		Robot.cameraSubsystem.visionThread.setVisionState(visionState);
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Aliging with target");
	}

	@Override
	protected void execute() {
		double rotation = GetRotation(0.55);

		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Aliging with target with rotation : " + rotation);
		RobotMap.driveTrain.arcadeDrive(0, rotation);
	}

	@Override
	protected boolean isFinished() {
		// return false;
		return MathUtils.equalEpsilon(0.5 + centerOffset, GetCenter(), 0.03);
	}

}
