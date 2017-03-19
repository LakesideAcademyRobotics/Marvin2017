package org.usfirst.frc.team4955.robot.commands;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class RobotInit extends Command {

	@Override
	protected void initialize() {
		RobotMap.frontCameraLight.set(false);
		RobotMap.backCameraLight.set(false);
		RobotMap.cameraFrontServo.set(Constants.CAMERA_TALON_OUT_VALUE);
		RobotMap.gearKicker.set(Constants.GEAR_KICKER_BACK_POSITION);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
