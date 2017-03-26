package org.usfirst.frc.team4955.robot.commands.drive.turn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TurnCommandGroup extends CommandGroup {

	public TurnCommandGroup(double angle, double speed) {
		addSequential(new GyroTurn(angle, speed));
		addSequential(new WaitCommand(1));
		addSequential(new GyroConterTurn(angle, speed));
	}
}
