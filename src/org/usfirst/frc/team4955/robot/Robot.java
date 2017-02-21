
package org.usfirst.frc.team4955.robot;

import org.usfirst.frc.team4955.robot.commands.TestCanTalonCommand;
import org.usfirst.frc.team4955.robot.commands.autonomous.LeftMoveGear;
import org.usfirst.frc.team4955.robot.commands.autonomous.RightMoveGear;
import org.usfirst.frc.team4955.robot.commands.drive.JoystickDrive;
import org.usfirst.frc.team4955.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team4955.robot.commands.drive.WallSensor;
import org.usfirst.frc.team4955.robot.subsystems.BallPickUpSubsystem;
import org.usfirst.frc.team4955.robot.subsystems.DriveGameSubsystem;
import org.usfirst.frc.team4955.robot.subsystems.ThrowerSubsystem;
import org.usfirst.frc.team4955.robot.subsystems.WinchSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// Subsystems
	public static DriveGameSubsystem driveSubsystem;
	public static BallPickUpSubsystem ballPickUpSystem;
	public static WinchSubsystem winchSystem;
	public static ThrowerSubsystem throwerSubsystem;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	///
	/// INIT
	///
	@Override
	public void robotInit() {
		// Pre init
		Constants.initForRobotR1();// REMOVE ME ON COMPETITION ROBOT R1
		DashboardKeys.init();

		// Robot init
		RobotMap.init();
		subsystemInit();
		OI.init();
		initAutonomousCommands();
	}

	private void subsystemInit() {
		driveSubsystem = new DriveGameSubsystem(RobotMap.driveTrain, RobotMap.gyro);
		ballPickUpSystem = new BallPickUpSubsystem();
		winchSystem = new WinchSubsystem();
		throwerSubsystem = new ThrowerSubsystem();

		SmartDashboard.putBoolean(DashboardKeys.DRIVE, driveSubsystem.isPresent());
		SmartDashboard.putBoolean(DashboardKeys.GYRO, RobotMap.gyro != null);
		SmartDashboard.putBoolean(DashboardKeys.PICKUP, ballPickUpSystem.isPresent());
		SmartDashboard.putBoolean(DashboardKeys.THROWER, throwerSubsystem.isPresent());
		SmartDashboard.putBoolean(DashboardKeys.WINCH, winchSystem.isPresent());
	}

	private void initAutonomousCommands() {
		
		chooser.addDefault("Left move and gear", new LeftMoveGear());
		chooser.addObject("Right move and gear", new RightMoveGear());
		chooser.addObject("Move 20 feet", new MoveDistance(20));
		SmartDashboard.putData("Auto mode", chooser);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	///
	/// AUTONOMOUS
	///

	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	///
	/// TELEOP
	///

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		
		Command WallSensor = new WallSensor();
		WallSensor.start();
		

		
		if (driveSubsystem.isPresent()) {
			Command drive = new JoystickDrive();
			Scheduler.getInstance().add(drive);
			drive.start();
		}

		Command geneva = new TestCanTalonCommand(RobotMap.genevaWheelTalon, 6, 1);
		Scheduler.getInstance().add(geneva);
		geneva.start();
	}

	@Override
	public void teleopPeriodic() {

		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro Value", RobotMap.gyro.getAngle());

		SmartDashboard.putNumber("Distance From Back", RobotMap.backSensor.getValue());

		SmartDashboard.putNumber("Distance From Front", RobotMap.frontSensor.getValue());
		
	}

	@Override
	public void disabledPeriodic() {

	}

	///
	/// Other
	///
	@Override
	public void robotPeriodic() {

	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
