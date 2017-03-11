package org.usfirst.frc.team4955.robot.commands.generics;

import org.usfirst.frc.team4955.robot.OneShotCommand;

import edu.wpi.first.wpilibj.DigitalOutput;

public class DigitalOutputSetNoUnset extends OneShotCommand {

	private DigitalOutput	digitalOutput;
	private boolean			value;

	public DigitalOutputSetNoUnset(DigitalOutput digitalOutput, boolean value) {
		this.digitalOutput = digitalOutput;
		this.value = value;
	}

	@Override
	protected void initialize() {
		digitalOutput.set(value);
	}
}
