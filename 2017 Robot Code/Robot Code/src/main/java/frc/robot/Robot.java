/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends IterativeRobot {
  private SpeedController liftmotor = new Talon(5);
  private Joystick liftstick = new Joystick(1); // initialize the joystick port for the liftstick
  
  RobotDrive myRobot = new RobotDrive(3, 2, 1, 0);
  Joystick stick = new Joystick(0);
  Timer timer = new Timer();


  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if(timer.get() < 2.0){
      myRobot.drive(-0.5, 0.0);
    } else {
      myRobot.drive(0.0, 0.0);
    }
  }

  @Override
  public void teleopInit() {
    
  }

  @Override
  public void teleopPeriodic() {
    myRobot.arcadeDrive(stick);
    if (liftstick.getY() <.25){
      liftmotor.set(0);
    } else{
      liftmotor.set(liftstick.getY());
    }
  }

  @Override
  public void testPeriodic() {
    LiveWindow.run();
  }

}
