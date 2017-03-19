package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.utils.SensorEncoderUtils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WallSensor extends Command {

	private boolean hasClose = false; // last frame was close

	@Override
	protected void initialize() {
		hasClose = false;
	}

	@Override
	protected void execute() {
		boolean close = false;
		SmartDashboard.putNumber("Front sensor", 5 - SensorEncoderUtils.calculateDistance(RobotMap.frontSensor));
		SmartDashboard.putNumber("Back sensor", 5 - SensorEncoderUtils.calculateDistance(RobotMap.backSensor));
		if (Robot.driveSubsystem.reverseInput) {
			close = isClose(RobotMap.backSensor);
		} else {
			close = isClose(RobotMap.frontSensor);
		}

		if (hasClose != close) { // closeness limit hit
			if (close)
				Robot.driveSubsystem.SetMaxOutput(Constants.DRIVE_SLOWN_DOWN_MAXOUTPUT);
			else
				Robot.driveSubsystem.SetMaxOutput(Constants.DRIVE_NORMAL_MAXOUTPUT);
			hasClose = close;
		}
	}

	private boolean isClose(AnalogInput sensor) {
		return SensorEncoderUtils.calculateDistance(sensor) < 4;
	}

	protected boolean isFinished() {

		return false;
	}

}
