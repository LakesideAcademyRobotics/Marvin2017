package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.Constants;
import org.usfirst.frc.team4955.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class DriveGameSubsystem extends Subsystem {

	public RobotDrive robotDrive;
	public boolean reverseInput = false;
	public double MouvementInputFactor = 1;

	public Gyro gyro;

	public DriveGameSubsystem(RobotDrive robotDrive, Gyro gyro) {
		super();
		this.gyro = gyro;
		this.robotDrive = robotDrive;
	}

	public void SetMaxOutput(double maxOutput) {
		robotDrive.setMaxOutput(maxOutput);
	}

	public void Periodic(double movement, double rotation) {
		if (reverseInput) {
			movement = -movement;
			rotation = -rotation;
		}
		movement *= MouvementInputFactor;
		rotation *= MouvementInputFactor;

		if (gyro == null) {
			robotDrive.arcadeDrive(movement, rotation);
		} else {
			// We work with Gyro! We will use the correction and stuff
			PeriodicWithGyro(movement, rotation);
		}
	}

	private void PeriodicWithGyro(double movement, double rotation) {

		if (movement == 0) {
			gyro.reset();
			// Move as normal

		} else {
			if (rotation != 0) {
				// We don't apply correction when the inputed rotation wants to
				// turn. ( not zero )
				gyro.reset();

			} else {
				double correction = (gyro.getAngle() * Constants.CORRECTION_FACTOR);
				correction = CapValue(correction, -Constants.MAX_CORRECTION, Constants.MAX_CORRECTION);

				// We will apply the correction the correction
				rotation -= correction;
			}

		}

		robotDrive.arcadeDrive(movement, rotation);
	}

	private double CapValue(double value, double min, double max) {
		if (value > max) {
			return max;
		} else if (value < min) {
			return min;
		} else {
			return value;
		}
	}

	public boolean isPresent() {
		return RobotMap.driveTrain != null;
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void reverseInput() {
		reverseInput = !reverseInput;

	}

}
