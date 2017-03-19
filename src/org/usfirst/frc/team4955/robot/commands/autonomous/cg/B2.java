package org.usfirst.frc.team4955.robot.commands.autonomous.cg;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.commands.autonomous.AutonomousGear;
import org.usfirst.frc.team4955.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team4955.robot.commands.drive.TurnRobot;
import org.usfirst.frc.team4955.robot.commands.gear.GearKickerCommand;
import org.usfirst.frc.team4955.robot.commands.generics.SetIgnioreGyroOnDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class B2 extends CommandGroup {

	public B2() {

		addSequential(new SetIgnioreGyroOnDrive(true));

		addSequential(new MoveDistance(30, 0.8));
		addSequential(new AutonomousGear());
		addSequential(new MoveDistance(28, 0.8));

		// At the airship! Take my gear please!
		addSequential(new GearKickerCommand(Constants.AUTO_KICKER_TIME));

		addSequential(new MoveDistance(-40, 0.8));

		addSequential(new TurnRobot(40, 0.9));

		// addParallel(new DigitalOutputFlash(RobotMap.frontCameraLight, 20,
		// 0.25, 0.25));
		// addParallel(new DigitalOutputFlash(RobotMap.backCameraLight, 20,
		// 0.25, 0.25));
	}
}
