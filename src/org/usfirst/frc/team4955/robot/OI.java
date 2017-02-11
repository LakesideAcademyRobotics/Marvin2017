package org.usfirst.frc.team4955.robot;

import org.usfirst.frc.team4955.robot.commands.WinchPull;
import org.usfirst.frc.team4955.robot.commands.ball.StartConveyor;
import org.usfirst.frc.team4955.robot.commands.ball.StopPickup;
import org.usfirst.frc.team4955.robot.commands.drive.Turn135;
import org.usfirst.frc.team4955.robot.utils.driveTrain.InverseDriveTrainCommand;
import org.usfirst.frc.team4955.robot.utils.driveTrain.SetDriveTrainMaxOutputCommand;
import org.usfirst.frc.team4955.robot.utils.input.GyroRatationInput;
import org.usfirst.frc.team4955.robot.utils.input.JoystickInput;
import org.usfirst.frc.team4955.robot.utils.input.TeleopInput;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad.GamepadAxis;
import org.usfirst.frc.team4955.robot.utils.utils.Gamepad.GamepadButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick mainJoystick;
	private static TeleopInput controlerXInput;
	public static GyroRatationInput controlerGyroInput;
	public static TeleopInput controlerYInput;
	
	public static GamepadButton REVERSE_DRIVE_BUTTOM_NUMBER = GamepadButton.A;
	public static GamepadButton SLOW_DRIVE_OUTPUT_BUTTOM_NUMBER = GamepadButton.B;
	public static GamepadButton DRIVE_OUTPUT_LOWER_BUTTOM_NUMBER = GamepadButton.LB;
	public static GamepadButton DRIVE_OUTPUT_RAISE_BUTTOM_NUMBER = GamepadButton.RB;
	public static GamepadButton BALL_PICKUP = GamepadButton.X;
	public static GamepadButton STOP_BALL_PICKUP = GamepadButton.Y;
	public static GamepadButton WINCH_PULL = GamepadButton.Start;
	public static GamepadButton WINCH_PUSH = GamepadButton.Back;
	public static GamepadButton THROWER_STOP = GamepadButton.LB; 
	public static GamepadButton TURN_BUTTON = GamepadButton.Start;

	public static void init(){
		mainJoystick = new Joystick(0);
		
		controlerXInput = new JoystickInput(mainJoystick, GamepadAxis.LeftX.value(), 0.14);
		//controlerGyroInput = new GyroRatationInput(controlerXInput);
		controlerYInput = new JoystickInput(mainJoystick, GamepadAxis.LeftY.value(), 0.14);
		//RobotMap.driveTrain.xInput = controlerGyroInput;
		//RobotMap.driveTrain.yInput = controlerYInput;
		
		
		JoystickButton command = null;
		
		
		//Drive
		//command = new JoystickButton(mainJoystick, REVERSE_DRIVE_BUTTOM_NUMBER.value());
        //command.whenPressed(new InverseDriveTrainCommand(RobotMap.driveTrain));
        //command = new JoystickButton(mainJoystick, SLOW_DRIVE_OUTPUT_BUTTOM_NUMBER.value());
        //command.toggleWhenActive(new SetDriveTrainMaxOutputCommand(RobotMap.driveTrain, 0.2));
        
        // Ball pick-up
        command = new JoystickButton(mainJoystick, BALL_PICKUP.value());
		command.toggleWhenActive(new StartConveyor());
		command = new JoystickButton(mainJoystick, STOP_BALL_PICKUP.value());
		command.toggleWhenActive(new StopPickup());
		
		//Turn 
        command = new JoystickButton(mainJoystick, TURN_BUTTON.value());
        command.toggleWhenActive(new Turn135());
        
		//Winch
		command = new JoystickButton(mainJoystick, WINCH_PULL.value());
		command.toggleWhenActive(new WinchPull());
	}
}
