package org.usfirst.frc.team4955.robot;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Cameras {

	public static CvSource VisionSource; 
	public static UsbCamera LogitedHD1080p;
	
	public static void StartLogitechHd1080p(int channel){

        UsbCamera cam = new UsbCamera("Robert", 0);
        
        cam.setFPS(30);
        cam.setResolution(1920, 1080);
        CameraServer.getInstance().addCamera(cam);
        CameraServer.getInstance().startAutomaticCapture(cam);
        
        LogitedHD1080p = cam;
        //VisionSource = CameraServer.getInstance().putVideo("VisionSource", 1920, 1080);
	}
}
