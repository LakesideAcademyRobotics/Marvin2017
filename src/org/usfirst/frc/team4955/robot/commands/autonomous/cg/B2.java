package org.usfirst.frc.team4955.robot.commands.autonomous.cg;

import org.usfirst.frc.team4955.robot.commands.autonomous.AutonomousGear;
import org.usfirst.frc.team4955.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team4955.robot.commands.drive.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class B2 extends CommandGroup {

	public B2() {

		addSequential(new MoveDistance(70.3, 1));
		addSequential(new AutonomousGear());

		addSequential(new MoveDistance(-45, 1));
		addSequential(new TurnRobot(90, 0.75));
		addSequential(new MoveDistance(20, 1));
	}
}
