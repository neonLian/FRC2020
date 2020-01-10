/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  
  private double speed = Constants.Speeds.DriveSpeed;

  // Talons
  public Talon frontRight = new Talon(Constants.MotorPorts.FrontRight);
  public Talon frontLeft = new Talon(Constants.MotorPorts.FrontLeft);
  public Talon backRight = new Talon(Constants.MotorPorts.BackRight);
  public Talon backLeft = new Talon(Constants.MotorPorts.BackLeft);

  // Sensors
  public Encoder encoder = new Encoder(Constants.SensorPorts.EncoderPort1, Constants.SensorPorts.EncoderPort2);

  public void arcadeDrive(double stickX, double stickY)
  {
      double lvalue = stickY - stickX;
      double rvalue = stickY + stickX;

      lvalue = clamp(lvalue, 0, 1);
      rvalue = clamp(rvalue, 0, 1);

      tankDrive(lvalue, rvalue);
      
      
  }

  public void tankDrive(double lvalue, double rvalue)
  {
    /*** Receives left and right side values between -1 and 1 and will set drive motor speeds accordingly ***/
    frontLeft.set(lvalue*speed);
    frontRight.set(rvalue*speed);
    backLeft.set(lvalue*speed);
    backRight.set(rvalue*speed);
  }

  public void swerveDrive(double forwardValue, double sideValue, double turnValue)
  {
    // complicated math stuff
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private static double clamp (double x, double min, double max)
  {
    return Math.max(Math.min(x, max), min);
  }
}
