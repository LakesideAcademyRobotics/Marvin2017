package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveDistance extends Command {

	private double distanceInRotation;
	private double distanceToFinishRight;
	private double distanceToFinishLeft;
	private double speed;

	public MoveDistance(double distanceInFeet, double speed) {
		this.speed = speed;
		this.distanceInRotation = distanceInFeet * Constants.ENCODER_ROTATIONS_PER_FOOT;
	}

	protected void initialize() {
		i = 0;
		distanceToFinishRight = RobotMap.rightEncoder.get() + distanceInRotation;
		distanceToFinishLeft = RobotMap.leftEncoder.get() + distanceInRotation;
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS,
				"Moving " + (distanceInRotation / Constants.ENCODER_ROTATIONS_PER_FOOT) + " \"");
	}

	int i = 0;

	@Override
	protected void execute() {
		RobotMap.driveTrain.arcadeDrive(speed, 0);
	}

	protected boolean isFinished() {

		if (RobotMap.leftEncoder.get() >= distanceToFinishLeft
				|| RobotMap.rightEncoder.get() >= distanceToFinishRight) {
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		RobotMap.driveTrain.arcadeDrive(0, 0);
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Moving DONE");
	}

	@Override
	protected void interrupted() {
		RobotMap.driveTrain.arcadeDrive(0, 0);
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Moving interrupted");
	}
}
