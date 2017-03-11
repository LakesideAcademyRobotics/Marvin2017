package org.usfirst.frc.team4955.robot.commands.thrower;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThowerStartCommands extends CommandGroup {

	public ThowerStartCommands() {
		addSequential(new ThrowerStart());
		addSequential(new ThrowerWhileActive());
	}

	@Override
	protected void interrupted() {
		new ThrowerStop().start();
	}

	@Override
	protected void end() {
		new ThrowerStop().start();
	}
}
