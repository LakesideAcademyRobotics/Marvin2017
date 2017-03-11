package org.usfirst.frc.team4955.robot.commands.ball;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallPickupCommands extends CommandGroup {

	public BallPickupCommands() {
		addSequential(new StartPickup());
	}

	@Override
	protected void interrupted() {
		new StopPickup().start();
	}

	@Override
	protected void end() {
		new StopPickup().start();
	}
}