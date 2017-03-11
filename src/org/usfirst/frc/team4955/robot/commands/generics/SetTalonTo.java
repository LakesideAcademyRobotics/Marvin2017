package org.usfirst.frc.team4955.robot.commands.generics;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;

public class SetTalonTo extends Command {

	Talon	talon;
	double	value;
	double	time;

	public SetTalonTo(Talon talon, double value, double time) {
		super();
		this.talon = talon;
		this.value = value;
		this.time = time;
	}

	@Override
	protected void initialize() {
		talon.set(value);
		setTimeout(time);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}
