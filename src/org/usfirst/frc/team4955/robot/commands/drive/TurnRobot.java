package org.usfirst.frc.team4955.robot.commands.drive;

import java.text.NumberFormat;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnRobot extends Command {

	public enum SystemUsed {
		Gyro, Encoder, Time
	};

	double angle;

	double encoderValueNeed;
	double speed;

	public TurnRobot(double angle, double speed) {
		this.angle = angle;
		this.speed = speed;
	}

	SystemUsed usingSystem;

	protected void initialize() {

		if (RobotMap.gyro != null) {
			SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Turning " + angle + " with gyro");
			RobotMap.gyro.reset();
			usingSystem = SystemUsed.Gyro;
		} else if (RobotMap.leftEncoder != null) {
			usingSystem = SystemUsed.Encoder;
			encoderValueNeed = RobotMap.leftEncoder.get() + angle / 360 * Constants.ENCODER_ROTATIONS_PER_360;
			SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Turning " + angle + " with encoder");
		} else {
			usingSystem = SystemUsed.Time;
			// setTimeout(seconds);
		}
	}

	@Override
	protected void execute() {
		double s = speed * Math.signum(angle);
		double f = 1 - Math.abs(RobotMap.gyro.getAngle()) / Math.abs(angle);
		f = f * f * 0.3 + 0.7;
		RobotMap.driveTrain.arcadeDrive(0, s * f);
	}

	protected boolean isFinished() {
		String v = NumberFormat.getInstance().format(Math.abs(RobotMap.gyro.getAngle()));
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, v + " >= " + Math.abs(angle));

		if (SystemUsed.Gyro.equals(usingSystem) && Math.abs(RobotMap.gyro.getAngle()) > Math.abs(angle)) {
			return true;
		} else if (SystemUsed.Encoder.equals(usingSystem) && RobotMap.leftEncoder.get() >= encoderValueNeed) {
			return true;
		}

		return false;
	}

	@Override
	protected void end() {
		// stopd driving
		// RobotMap.brushTalon.set(0);
		super.end();
	}
}
