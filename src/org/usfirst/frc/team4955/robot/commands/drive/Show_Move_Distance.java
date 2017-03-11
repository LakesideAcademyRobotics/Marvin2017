package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Show_Move_Distance extends Command {

	private double leftValue;
	private double rightValue;

	protected void initialize() {
		leftValue = RobotMap.leftEncoder.get();
		rightValue = RobotMap.rightEncoder.get();
		System.out.println("reset");
	}

	@Override
	protected void execute() {
		double left = (RobotMap.leftEncoder.get() - leftValue) / Constants.ENCODER_ROTATIONS_PER_INCH;
		double right = (RobotMap.rightEncoder.get() - rightValue) / Constants.ENCODER_ROTATIONS_PER_INCH;
		SmartDashboard.putNumber(DashboardKeys.MOVE_DISTANCE_LEFT, left);
		SmartDashboard.putNumber(DashboardKeys.MOVE_DISTANCE_RIGHT, right);
	}

	protected boolean isFinished() {
		return false;
	}

}
