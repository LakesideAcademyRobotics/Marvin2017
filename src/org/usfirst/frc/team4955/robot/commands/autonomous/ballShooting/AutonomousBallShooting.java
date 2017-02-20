package org.usfirst.frc.team4955.robot.commands.autonomous.ballShooting;

import org.usfirst.frc.team4955.robot.commands.thrower.ThrowerStart;
import org.usfirst.frc.team4955.robot.commands.thrower.ThrowerWhileActive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousBallShooting extends CommandGroup {

	public AutonomousBallShooting() {
		addSequential(new RaiseCamera());
		addSequential(new AlignWithBoiler());
		addSequential(new ApprochingBoiler());
		addSequential(new ThrowerStart());
		addSequential(new ThrowerWhileActive());
		addSequential(new LowerFrontCamera());
	}
}
