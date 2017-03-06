package org.usfirst.frc.team4955.robot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class OneShotCommand extends Command {

	@Override
	protected boolean isFinished() {
		return true;
	}

}
