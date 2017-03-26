package org.usfirst.frc.team4955.robot.commands.drive.turn;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroConterTurn extends Command {

	double	wantedAngle;
	double	speed;
	double	angle;

	public GyroConterTurn(double wantedAngle, double speed) {
		this.speed = speed;
		this.wantedAngle = wantedAngle;
	}

	@Override
	protected void initialize() {
		angle = -(RobotMap.gyro.getAngle() - wantedAngle);
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Counter turning " + angle + " with gyro");
		RobotMap.gyro.reset();
	}

	@Override
	protected void execute() {
		double s = speed * Math.signum(angle);
		double f = 1;

		f = 1 - Math.abs(RobotMap.gyro.getAngle()) / Math.abs(angle);
		String str = String.format("Turning %f / %f with gyro", RobotMap.gyro.getAngle(), angle);
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, str);

		f = Constants.EaseTurn(f);
		// SmartDashboard.putString("Current Item", "F: " + f);
		RobotMap.driveTrain.arcadeDrive(0, s * f);
	}

	protected boolean isFinished() {
		return Math.abs(RobotMap.gyro.getAngle()) > Math.abs(angle);
	}

	@Override
	protected void end() {
		RobotMap.driveTrain.arcadeDrive(0, 0);
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Turned " + angle + " degre!");
	}

	@Override
	protected void interrupted() {
		RobotMap.driveTrain.arcadeDrive(0, 0);
		SmartDashboard.putString(DashboardKeys.AUTONOMOUS_STATUS, "Turning " + angle + " degre interrupted");
	}

}
