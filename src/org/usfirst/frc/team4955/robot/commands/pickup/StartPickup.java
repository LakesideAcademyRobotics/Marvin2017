package org.usfirst.frc.team4955.robot.commands.pickup;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.DashboardKeys;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StartPickup extends Command {

	public StartPickup() {
		requires(Robot.ballPickUpSystem);
	}

	protected void initialize() {
		RobotMap.elevator.set(Constants.CONVEYOR_BELT_SPEED);
		setTimeout(0.5);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void interrupted() {
		SmartDashboard.putBoolean(DashboardKeys.BALL_PICKUP, true);
		RobotMap.brushTalon.set(Constants.BRUSH_SPEED);
		
	}

	protected void end() {
		SmartDashboard.putBoolean(DashboardKeys.BALL_PICKUP, true);
		RobotMap.brushTalon.set(Constants.BRUSH_SPEED);
		
	}
}
