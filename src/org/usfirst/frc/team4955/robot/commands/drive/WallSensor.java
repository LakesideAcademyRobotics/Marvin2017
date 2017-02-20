package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WallSensor extends Command {

	public double AnalogToInchesMultiplier = 0.125;

	private boolean hasClose = false; // last frame was close

	@Override
	protected void initialize() {
		hasClose = isClose();
	}

	@Override
	protected void execute() {
		boolean close = isClose();
		if (hasClose != close) { // closeness limit hit
			if (close)
				Robot.driveSubsystem.SetMaxOutput(Constants.DRIVE_SLOWN_DOWN_BY_WALL_NEW_MAX_SPEED);
			else
				Robot.driveSubsystem.SetMaxOutput(Constants.DRIVE_MAX_SPEED);
			hasClose = close;
		}
		SmartDashboard.putBoolean("Is too close", close);
	}

	private boolean isClose() {
		return (RobotMap.frontSensor.getValue() - Constants.DRIVE_SLOW_DOWN_BY_WALL_DISTANCE)
				* AnalogToInchesMultiplier <= 0;
	}

	protected boolean isFinished() {

		return false;
	}

}
