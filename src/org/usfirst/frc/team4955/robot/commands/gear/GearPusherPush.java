package org.usfirst.frc.team4955.robot.commands.gear;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.OneShotCommand;
import org.usfirst.frc.team4955.robot.RobotMap;

public class GearPusherPush extends OneShotCommand {

	@Override
	protected void initialize() {
		RobotMap.gearPusher.set(Constants.GEAR_PUSHER_PUSH_POSITION);
	}
}
