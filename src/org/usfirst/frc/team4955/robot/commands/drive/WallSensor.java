package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WallSensor extends Command {

	private boolean hasClose = false; // last frame was close

	private static double AnalogToInchesMultiplier = 0.125;
	private static double SensorZeroDistance = 240 * AnalogToInchesMultiplier;

	@Override
	protected void initialize() {
		hasClose = isClose();
	}

	@Override
	protected void execute() {
		boolean close = isClose();
		if (hasClose != close) { // closeness limit hit
			if (close)
				Robot.driveSubsystem.SetMaxOutput(Constants.DRIVE_SLOWN_DOWN_MAXOUTPUT);
			else
				Robot.driveSubsystem.SetMaxOutput(Constants.DRIVE_NORMAL_MAXOUTPUT);
			hasClose = close;
		}
		SmartDashboard.putBoolean("Is too close", close);
	}

	private boolean isClose() {
		double distanceInInch = RobotMap.frontSensor.getValue() * AnalogToInchesMultiplier - SensorZeroDistance;
		return distanceInInch <= 0;
	}

	protected boolean isFinished() {

		return false;
	}

}
