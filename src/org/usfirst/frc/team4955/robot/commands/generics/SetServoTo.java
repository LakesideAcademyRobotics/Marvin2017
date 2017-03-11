package org.usfirst.frc.team4955.robot.commands.generics;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class SetServoTo extends Command {

	Servo	servo;
	double	value;
	double	time;

	public SetServoTo(Servo servo, double value, double time) {
		super();
		this.servo = servo;
		this.value = value;
		this.time = time;
	}

	@Override
	protected void initialize() {
		servo.set(value);
		setTimeout(time);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

}