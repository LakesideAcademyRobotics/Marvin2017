package org.usfirst.frc.team4955.robot.commands.gear;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class GearKickerCommand extends Command {

	double timeout;

	public GearKickerCommand(double timeout) {
		this.timeout = timeout;
	}

	@Override
	protected void initialize() {

		setTimeout(timeout);

		RobotMap.gearKicker.set(Constants.GEAR_KICKER_PUSH_POSITION);
	}

	@Override
	protected void interrupted() {
		RobotMap.gearKicker.set(Constants.GEAR_KICKER_BACK_POSITION);
	}

	@Override
	protected void end() {
		RobotMap.gearKicker.set(Constants.GEAR_KICKER_BACK_POSITION);
	}

	@Override
	protected boolean isFinished() {
		return (isTimedOut());
	}

}
