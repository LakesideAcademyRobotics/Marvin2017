package org.usfirst.frc.team4955.robot.commands;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Command;

public class DigitalOutputSet extends Command {

	private double time;
	private DigitalOutput digitalOutput;
	private boolean value;

	public DigitalOutputSet(double time, DigitalOutput digitalOutput, boolean value) {
		this.time = time;
		this.digitalOutput = digitalOutput;
		this.value = value;
	}

	@Override
	protected void initialize() {
		setTimeout(time);
		digitalOutput.set(value);

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
