package org.usfirst.frc.team4955.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public class TestCanTalonCommand extends Command {

	private CANTalon talon;
	private double duration;
	private double talonValue;

	public TestCanTalonCommand(CANTalon talon, double duration, double talonValue) {
		this.talon = talon;
		this.duration = duration;
		this.talonValue = talonValue;
	}

	protected void initialize() {
		setTimeout(duration);
		talon.set(talonValue);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		talon.set(0);
	}

	@Override
	protected void interrupted() {
		talon.set(0);
	}

}
