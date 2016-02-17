package org.usfirst.frc.team1165.robot.subsystems;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;

import org.usfirst.frc.team1165.robot.commands.ProcessCameraFrames;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem to access USB camera.
 */
public class Camera extends Subsystem implements Runnable 
{
	public enum CameraMode { SUBSYSTEM, THREAD };
	
	private CameraMode cameraMode;

	// The following are used to access the camera and data from the camera:
	private int session;
	private Image frame;
	
	// The directory in which to put data files:
	private final static String dataDirectory = "/home/lvuser/data";
	
	// This file on the roboRIO file system is used to store a list of the supported video modes:
	private final static String videoModesFile = "NIVision_VideoModes.txt";
	
	// This file on the roboRIO file system is used to store a list of the various vision attributes:
	private final static String visionAttributesFile = "NIVision_Attributes.txt";
	
	// The default video mode. To see what modes are supported, load the robot code at
	// least once and look at the file indicated by videoModesFile above.
	private final static String videoMode = "640 x 480 YUY 2 30.00 fps";
	
	// Milliseconds between frames processed by the stand alone thread.
	private final static int interFrameTimeMillis = 20;

	/**
	 * Constructor.
	 * 
	 * @param cameraName Name of the camera, from the roboRIO WebDash
	 * @param cameraMode Indicates if should run Camera as a SUBSYSTEM or in a separate THREAD
	 */
	public Camera(String cameraName, CameraMode cameraMode)
	{
		this.cameraMode = cameraMode;
		
		// Create a frame in which to receive images:
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		// Create session used to access camera:
		session = NIVision.IMAQdxOpenCamera(cameraName,
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxSetAttributeString(session, "AcquisitionAttributes::VideoMode", videoMode);
		
		try
		{
			// Make sure the directory that will hold the data files exists:
			new File(dataDirectory).mkdirs();
			
			// Dump the supported video modes to a file:
			PrintWriter pw = new PrintWriter(Paths.get(dataDirectory, videoModesFile).toString());
			NIVision.dxEnumerateVideoModesResult result = NIVision.IMAQdxEnumerateVideoModes(session);
			pw.println("Current: \"" + result.videoModeArray[result.currentMode].Name + '"');
			pw.println();
			for (NIVision.IMAQdxEnumItem item : result.videoModeArray)
			{
				pw.println('"' + item.Name + '"');
			}
			pw.close();
			
			// Dump the supported vision attributes to a file:
			NIVision.IMAQdxWriteAttributes(session, Paths.get(dataDirectory, visionAttributesFile).toString());
		}
		catch (Exception ex)
		{
			// do nothing
		}
		
		// Configure NI Vision to use created camera session:
		NIVision.IMAQdxConfigureGrab(session);
		NIVision.IMAQdxStartAcquisition(session);
		
		// Start camera server that SmartDashboard will use:
		CameraServer.getInstance().setQuality(50);
		
		if (cameraMode == CameraMode.THREAD)
		{
			new Thread(this).start();
		}
	}

	/**
	 * Sets up the command used to process camera frames in the main robot execution loop.
	 */
	public void initDefaultCommand()
	{
		if (cameraMode == CameraMode.SUBSYSTEM)
		{
			setDefaultCommand(new ProcessCameraFrames(this));
		}
	}

	/**
	 * Grab the current frame from the camera and give it to the camera server.
	 */
	public void report()
	{
		NIVision.IMAQdxGrab(session, frame, 1);
		CameraServer.getInstance().setImage(frame);
	}
	
	/**
	 * Entry point used to process camera frames in a separate thread.
	 */
	@Override
	public void run()
	{
		while (true)
		{
			report();
			try
			{
				Thread.sleep(interFrameTimeMillis);
			}
			catch (InterruptedException e)
			{
			}
		}
	}
}