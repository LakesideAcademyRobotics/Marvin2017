package org.usfirst.frc.team4955.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PutValueInDashboard extends Command {

	private String key;
	private String value;

	public PutValueInDashboard(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	protected void initialize() {
		SmartDashboard.putString(key, value);
	}

	@Override
	protected boolean isFinished() {

		return true;
	}

}
