package org.usfirst.frc.team4955.robot.commands.ball;

import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StopPickup extends Command {

	public StopPickup() {
		requires(Robot.ballPickUpSystem);
	}

	public void initialize() {
		RobotMap.brushTalon.setSpeed(0);
		setTimeout(2);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void interrupted() {
		SmartDashboard.putBoolean(DashboardKeys.BALL_PICKUP, false);
		RobotMap.elevator.setSpeed(0);
	}

	protected void end() {
		SmartDashboard.putBoolean(DashboardKeys.BALL_PICKUP, false);
		RobotMap.elevator.setSpeed(0);
	}
}
