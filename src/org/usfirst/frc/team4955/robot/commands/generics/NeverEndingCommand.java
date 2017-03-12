package org.usfirst.frc.team4955.robot.commands.generics;

import edu.wpi.first.wpilibj.command.Command;

public class NeverEndingCommand extends Command {

	@Override
	protected boolean isFinished() {
		return false;
	}

}
