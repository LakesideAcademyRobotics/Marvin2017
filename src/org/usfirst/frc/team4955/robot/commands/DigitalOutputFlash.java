package org.usfirst.frc.team4955.robot.commands;

import org.usfirst.frc.team4955.robot.Constants;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DigitalOutputFlash extends CommandGroup {

	public DigitalOutputFlash(DigitalOutput digitalOutput, int nbFlashs, double flashtime, double waitTime) {

		for (int i = 0; i < nbFlashs; i++) {
			addSequential(new DigitalOutputSet(flashtime, digitalOutput, Constants.CAMERA_LIGHT_ON));
			addSequential(new WaitCommand(flashtime));
		}
	}
}
