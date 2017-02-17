package org.usfirst.frc.team4955.robot.commands.Thrower;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThowerStartSquence extends CommandGroup {

	public ThowerStartSquence() {
		addSequential(new ThrowerStart());
		addSequential(new ThrowerWhileActive());
	}
}
