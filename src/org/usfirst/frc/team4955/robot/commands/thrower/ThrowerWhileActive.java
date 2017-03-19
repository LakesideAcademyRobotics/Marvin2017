package org.usfirst.frc.team4955.robot.commands.thrower;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.Robot;
import org.usfirst.frc.team4955.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ThrowerWhileActive extends Command {

	CANTalon _talon;

	public ThrowerWhileActive() {
		requires(Robot.throwerSubsystem);
		_talon = RobotMap.throwingWheelTalon;
	}

	public void initialize() {
		// initSpeedTalon();
	}

	void initSpeedTalon() {
		_talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		_talon.reverseSensor(false);

		/* set the peak and nominal outputs, 12V means full */
		_talon.configNominalOutputVoltage(+0.0f, -0.0f);
		_talon.configPeakOutputVoltage(+12.0f, -12.0f);
		/* set closed loop gains in slot0 */
		_talon.setProfile(0);
		_talon.setF(0);
		_talon.setP(0.2);
		_talon.setI(0);
		_talon.setD(0);
	}

	@Override
	protected void execute() {
		// executeSpeedMod();
		_talon.set(Constants.THROWER_SPEED);
	}

	void executeSpeedMod() {
		/* Speed mode */
		_talon.changeControlMode(TalonControlMode.Speed);
		_talon.set(Constants.THROWER_RPM);

		double motorOutput = _talon.getOutputVoltage() / _talon.getBusVoltage();

		System.out.println("Speed : " + _talon.getSpeed() + "  Output : " + motorOutput);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
	}

	protected void end() {

	}
}
