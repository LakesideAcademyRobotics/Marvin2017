package org.usfirst.frc.team4955.robot.commands.ball;

import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StartConveyor extends Command {

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	@Override
	protected void interrupted() {
		RobotMap.brushTalon.set(0.2);
	}
	@Override
	protected void initialize() {
		RobotMap.elavatorTalon.set(0.2);
		setTimeout(0.5);
	}
	@Override
	protected void end() {
		SmartDashboard.putBoolean("Ball Pickup", true);
		RobotMap.brushTalon.set(0.2);
	}
	@Override
	protected void execute() {
		System.err.println("Something funny. " + timeSinceInitialized());
	}
}
