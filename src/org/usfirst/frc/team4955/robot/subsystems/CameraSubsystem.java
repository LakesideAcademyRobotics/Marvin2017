package org.usfirst.frc.team4955.robot.subsystems;

import org.usfirst.frc.team4955.robot.RobotMap;
import org.usfirst.frc.team4955.robot.utils.utils.CameraManager;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSubsystem extends Subsystem {
	
	public enum CameraSide{ FRONT,BACK }
	
	
	public static CvSource VisionSource;
	
	private CameraSide LookingSide;
	private boolean visionLocked = false;
	
	private static CameraServer CS = CameraServer.getInstance();
		
	
	public CameraSubsystem() {
		LookingSide = LookingSide.FRONT;
		// CameraServer.getInstance().startAutomaticCapture(cam);
       // VisionSource = CameraServer.getInstance().putVideo("VisionSource", 1920, 1080);
	}
	
	public void StartVision(CameraSide side){
		visionLocked = true;
	}
	
	public void StopVision(){
		visionLocked = false;
	}
	
	public void SetCameraSide(CameraSide side){
		//Si on n'est pas en vision, alors on change la vision
		if(!visionLocked){
			//On s'assure que l'autre est ferme
			
			if(CameraSide.FRONT.equals(LookingSide)){
				
			}else{
				
			}
			LookingSide = side;
			
			
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	
	public boolean isPresent(){
		return RobotMap.brushTalon != null && RobotMap.elavatorTalon != null;
	}
}
