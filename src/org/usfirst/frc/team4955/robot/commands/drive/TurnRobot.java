package org.usfirst.frc.team4955.robot.commands.drive;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnRobot extends Command {

	public enum SystemUsed {
		Gyro, Encoder, Time
	};

	double angle;

	double	encoderValueNeed;
	double	speed;

	public TurnRobot(double angle, double speed) {
		this.angle = angle;
		this.speed = speed;
	}

	SystemUsed usingSystem;

	protected void initialize() {

		if (RobotMap.gyro != null) {
			SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Turning " + angle + " with gyro");
			RobotMap.gyro.reset();
			Robot.driveSubsystem.ignoroGyro = true;
			usingSystem = SystemUsed.Gyro;
		} else if (RobotMap.leftEncoder != null && RobotMap.rightEncoder != null) {
			encoderValueNeed = Math.abs(angle) / 360.0;
			if (angle < 0) {
				RobotMap.rightEncoder.reset();
				encoderValueNeed *= Constants.RIGHT_ENCODER_ROTATIONS_PER_360;
			} else {
				RobotMap.leftEncoder.reset();
				encoderValueNeed *= Constants.LEFT_ENCODER_ROTATIONS_PER_360;
			}
			usingSystem = SystemUsed.Encoder;
			SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Turning " + angle + " with encoder");
		} else {
			usingSystem = SystemUsed.Time;
			// setTimeout(seconds);
		}
	}

	@Override
	protected void execute() {
		double s = speed * Math.signum(angle);
		double f = 1;

		if (usingSystem.equals(SystemUsed.Gyro)) {
			f = 1 - Math.abs(RobotMap.gyro.getAngle()) / Math.abs(angle);
			String str = String.format("Turning %f / %f with gyro", RobotMap.gyro.getAngle(), angle);
			SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, str);

		} else if (usingSystem.equals(SystemUsed.Encoder)) {
			f = (angle < 0) ? RobotMap.rightEncoder.get() : RobotMap.leftEncoder.get();
			f /= encoderValueNeed;
			String str = String.format("Turning %.2f / %.2f with encoder", f, angle);
			SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, str);
			f = 1 - f;
		}

		f = Constants.EaseTurn(f);
		// SmartDashboard.putString("Current Item", "F: " + f);
		RobotMap.driveTrain.arcadeDrive(0, s * f);
	}

	protected boolean isFinished() {

		if (SystemUsed.Gyro.equals(usingSystem) && Math.abs(RobotMap.gyro.getAngle()) > Math.abs(angle)) {
			return true;
		} else if (SystemUsed.Encoder.equals(usingSystem)) {
			if (angle < 0)
				return RobotMap.rightEncoder.get() >= encoderValueNeed;
			else
				return RobotMap.leftEncoder.get() >= encoderValueNeed;
		}

		return false;
	}

	@Override
	protected void end() {
		RobotMap.driveTrain.arcadeDrive(0, 0);
		Robot.driveSubsystem.ignoroGyro = false;
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Turned " + angle + " degre!");
	}

	@Override
	protected void interrupted() {
		RobotMap.driveTrain.arcadeDrive(0, 0);
		Robot.driveSubsystem.ignoroGyro = false;
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Turning " + angle + " degre interrupted");
	}
}
