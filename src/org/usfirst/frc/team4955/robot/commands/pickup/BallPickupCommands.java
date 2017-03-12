package org.usfirst.frc.team4955.robot.commands.pickup;

import org.usfirst.frc.team4955.robot.commands.generics.NeverEndingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallPickupCommands extends CommandGroup {

	public BallPickupCommands() {
		addSequential(new StartPickup());
		addSequential(new NeverEndingCommand());
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