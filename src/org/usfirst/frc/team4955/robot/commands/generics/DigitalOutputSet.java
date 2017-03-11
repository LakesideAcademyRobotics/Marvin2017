package org.usfirst.frc.team4955.robot.commands.generics;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Command;

public class DigitalOutputSet extends Command {

	private double			timeout;
	private DigitalOutput	digitalOutput;
	private boolean			value;

	public DigitalOutputSet(double timeout, DigitalOutput digitalOutput, boolean value) {
		this.timeout = timeout;
		this.digitalOutput = digitalOutput;
		this.value = value;
	}

	@Override
	protected void initialize() {
		digitalOutput.set(value);
		setTimeout(timeout);

	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		digitalOutput.set(!value);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
