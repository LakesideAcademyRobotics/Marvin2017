package org.usfirst.frc.team4955.robot;

import org.usfirst.frc.team4955.robot.commands.ball.StartPickup;
import org.usfirst.frc.team4955.robot.commands.ball.StopPickup;
import org.usfirst.frc.team4955.robot.commands.drive.InverseDriveTrainCommand;
import org.usfirst.frc.team4955.robot.commands.drive.SetDriveInputFactor;
import org.usfirst.frc.team4955.robot.commands.drive.Turn135;
import org.usfirst.frc.team4955.robot.commands.thrower.ThowerStartSquence;
import org.usfirst.frc.team4955.robot.commands.thrower.ThrowerStop;
import org.usfirst.frc.team4955.robot.commands.winch.WinchLower;
import org.usfirst.frc.team4955.robot.commands.winch.WinchRaise;
import org.usfirst.frc.team4955.robot.utils.input.DualAxisInput;
import org.usfirst.frc.team4955.robot.utils.input.JoystickInput;
import org.usfirst.frc.team4955.robot.utils.input.TeleopInput;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad.GamepadAxis;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad.GamepadButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick mainJoystick;
	public static TeleopInput controlerRotationInput;
	public static TeleopInput controlerMovementInput;
	public static TeleopInput winchInput;

	public static GamepadButton REVERSE_DRIVE_BUTTOM_NUMBER = GamepadButton.A;
	public static GamepadButton SLOW_DRIVE_OUTPUT_BUTTOM_NUMBER = GamepadButton.B;
	public static GamepadButton BALL_THROWER_START = GamepadButton.LB;
	public static GamepadButton BALL_THROWER_STOP = GamepadButton.RB;
	public static GamepadButton BALL_PICKUP = GamepadButton.X;
	public static GamepadButton STOP_BALL_PICKUP = GamepadButton.Y;
	public static GamepadButton WINCH_RAISE = GamepadButton.Start;
	public static GamepadButton WINCH_LOWER = GamepadButton.Back;
	public static GamepadButton THROWER_STOP = GamepadButton.LB;
	public static GamepadButton TURN_BUTTON = GamepadButton.Start;

	public static double LEFT_JOYSTICK_DEAD_ZONE = 0.14;
	public static double RIGHT_JOYSTICK_DEAD_ZONE = 0.14;

	public static void init() {

		// Set up the joystick
		mainJoystick = new Joystick(0);

		controlerRotationInput = new JoystickInput(mainJoystick, GamepadAxis.LeftX.value(), LEFT_JOYSTICK_DEAD_ZONE);
		winchInput = controlerRotationInput = new JoystickInput(mainJoystick, GamepadAxis.RightY.value(),
				RIGHT_JOYSTICK_DEAD_ZONE);
		controlerMovementInput = new DualAxisInput(mainJoystick, GamepadAxis.LeftTrigger.value(), mainJoystick,
				GamepadAxis.RightTrigger.value(), 0, 0);

		JoystickButton command = null;

		// Drive
		if (Robot.driveSubsystem.isPresent()) {
			command = new JoystickButton(mainJoystick, REVERSE_DRIVE_BUTTOM_NUMBER.value());
			command.whenPressed(new InverseDriveTrainCommand());
			command = new JoystickButton(mainJoystick, SLOW_DRIVE_OUTPUT_BUTTOM_NUMBER.value());
			command.whenPressed(new SetDriveInputFactor(Constants.DRIVE_SLOWER_SPEED_FACTOR));
		}

		// Ball pick-up
		if (Robot.ballPickUpSystem.isPresent()) {
			command = new JoystickButton(mainJoystick, BALL_PICKUP.value());
			command.whenActive(new StartPickup());
			command = new JoystickButton(mainJoystick, STOP_BALL_PICKUP.value());
			command.whenActive(new StopPickup());
		}

		// Ball Thrower
		if (Robot.throwerSubsystem.isPresent()) {
			command = new JoystickButton(mainJoystick, BALL_THROWER_START.value());
			command.whenActive(new ThowerStartSquence());
			command = new JoystickButton(mainJoystick, BALL_THROWER_STOP.value());
			command.whenActive(new ThrowerStop());
		}

		// Turn
		command = new JoystickButton(mainJoystick, TURN_BUTTON.value());
		command.whenActive(new Turn135());

		// Winch
		if (Robot.winchSystem.isPresent()) {
			command = new JoystickButton(mainJoystick, WINCH_RAISE.value());
			command.whenActive(new WinchRaise());

			command = new JoystickButton(mainJoystick, WINCH_LOWER.value());
			command.whenActive(new WinchLower());
		}

		SmartDashboard.putBoolean(DashboardKeys.INIT_OI, true);
	}
}
