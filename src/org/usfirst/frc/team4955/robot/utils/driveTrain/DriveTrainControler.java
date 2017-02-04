package org.usfirst.frc.team4955.robot.utils.driveTrain;

import java.util.HashMap;
import java.util.Map.Entry;

import org.usfirst.frc.team4955.robot.utils.input.TeleopInput;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrainControler {

	public RobotDrive robotDrive;
	private HashMap<MotorType, Boolean> motorsInvertedValue;
	private DriveType currentDriveType;
	
	public TeleopInput xInput;
	public TeleopInput yInput;
	
	private double maxOutput;
	
	private boolean reversed = false;
	
	public DriveTrainControler(int LeftMotor, int RightMotor){
		 this(-1, LeftMotor, -1, RightMotor);
	}
	public DriveTrainControler(int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor){
		try{
			if(frontLeftMotor == -1)
				robotDrive = new RobotDrive(rearLeftMotor,rearRightMotor);
			else
				robotDrive = new RobotDrive(frontLeftMotor,rearLeftMotor,frontRightMotor,rearRightMotor);
				
		}catch(RuntimeException re){
			if(re.getMessage().contains("-1029")){
				System.err.println("A motor is initialised twice");
				throw re;
			}
		}
		motorsInvertedValue = new HashMap<>();
		if(frontLeftMotor != -1)
		{
			motorsInvertedValue.put(MotorType.kFrontLeft, false);
			motorsInvertedValue.put(MotorType.kFrontRight, false);
		}
		motorsInvertedValue.put(MotorType.kRearLeft, false);
		motorsInvertedValue.put(MotorType.kRearRight, false);

		
		currentDriveType = DriveType.Arcade;
		
		maxOutput = 0.4;
		robotDrive.setMaxOutput(maxOutput);
	}
	
	public void setMaxOuput(double value){
		maxOutput = value;
		robotDrive.setMaxOutput(value);
	}
	public double getMaxOutput(){
		return maxOutput;
	}
	
	public void inverse(){
		for (Entry<MotorType, Boolean> keyset : motorsInvertedValue.entrySet()) {
			reverse(keyset.getKey());
		}
		reversed = !reversed;
	}
	
	public void reverse(MotorType motor){
		Boolean currentValue = motorsInvertedValue.get(motor).booleanValue();
		motorsInvertedValue.put(motor, !currentValue);
		robotDrive.setInvertedMotor(motor, !currentValue);
	}
	
	public void setDriveType(DriveType type){
		if(type.equals(DriveType.Mecanum)){
			reverse(MotorType.kFrontLeft);
			reverse(MotorType.kRearLeft);
		}
		
		if(currentDriveType.equals(DriveType.Mecanum)){
			reverse(MotorType.kFrontLeft);
			reverse(MotorType.kRearLeft);
		}
		
		currentDriveType = type;
	}
	
	public void Periodic(){
		
		SmartDashboard.putBoolean("Drive forward", !reversed);
		
		
		if(xInput == null || yInput == null){
			System.err.println("Drive train controler needs X and Y TeleInput");
			return;
		}
		
		switch(currentDriveType){
			case Arcade:
				robotDrive.arcadeDrive(yInput.getInput(), xInput.getInput());
				SmartDashboard.putNumber("X", xInput.getInput());
				SmartDashboard.putNumber("Y", yInput.getInput());
				break;
			case Mecanum:
	            //robotDrive.mecanumDrive_Cartesian(stick1.getX(), stick1.getY(), stick1.getZ(), 0);
				break;
			case Tank:
				//double yLeft = stick1.getRawAxis(1);
				//double yRight = stick1.getRawAxis(5);
				//robotDrive.tankDrive(yLeft, yRight, true);
				break;
		}
	}
	
	public enum DriveType{Arcade,Tank,Mecanum};
}
