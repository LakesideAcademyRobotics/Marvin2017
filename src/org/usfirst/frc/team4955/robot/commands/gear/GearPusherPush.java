package org.usfirst.frc.team4955.robot.commands.gear;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.OneShotCommand;
import org.usfirst.frc.team4955.robot.RobotMap;

public class GearPusherPush extends OneShotCommand {

	@Override
	protected void initialize() {
		RobotMap.gearKicker.set(Constants.GEAR_KICKER_PUSH_POSITION);
	}
}
