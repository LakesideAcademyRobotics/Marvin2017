package org.usfirst.frc.team4955.robot.commands.autonomous.cg;

import org.usfirst.frc.team4955.robot.commands.autonomous.AutonomousGear;
import org.usfirst.frc.team4955.robot.commands.autonomous.ballShooting.AutonomousBallShooting;
import org.usfirst.frc.team4955.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team4955.robot.commands.drive.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class B1 extends CommandGroup {

	public B1() {
		addSequential(new MoveDistance(93.3, 1));

		addSequential(new TurnRobot(55, 0.75));
		addSequential(new AutonomousGear());

		addSequential(new MoveDistance(-15, 1));
		addSequential(new TurnRobot(-10, 0.75));
		addSequential(new AutonomousBallShooting());

	}
}
