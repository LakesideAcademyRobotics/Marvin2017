package org.usfirst.frc.team4955.robot.commands.gear;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class GearPusherCommand extends Command {

	@Override
	protected void initialize() {
		RobotMap.gearPusher.set(Constants.GEAR_PUSHER_PUSH_POSITION);
	}

	@Override
	protected void interrupted() {
		RobotMap.gearPusher.set(Constants.GEAR_PUSHER_BACK_POSITION);
	}

	@Override
	protected void end() {
		RobotMap.gearPusher.set(Constants.GEAR_PUSHER_BACK_POSITION);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
