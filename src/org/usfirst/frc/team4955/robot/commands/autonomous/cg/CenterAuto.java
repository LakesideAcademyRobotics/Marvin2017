package org.usfirst.frc.team4955.robot.commands.autonomous.cg;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team4955.robot.commands.gear.GearKickerCommand;
import org.usfirst.frc.team4955.robot.commands.generics.SetIgnioreGyroOnDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterAuto extends CommandGroup {

	public CenterAuto(double angleSignum) {

		addSequential(new SetIgnioreGyroOnDrive(true));

		addSequential(new MoveDistance(30, 0.8));
		// addSequential(new AutonomousGear());
		addSequential(new MoveDistance(28, 0.8));

		// At the airship! Take my gear please!
		addSequential(new GearKickerCommand(Constants.AUTO_KICKER_TIME));

		addSequential(new MoveDistance(-40, 0.8));

		// addSequential(new TurnRobot(angleSignum * 40, 0.9));

		addSequential(new SetIgnioreGyroOnDrive(false));
	}
}
