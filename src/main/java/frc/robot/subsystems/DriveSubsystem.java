/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  
  private double speed = Constants.Speeds.DriveSpeed;

  // Talons
  // public WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.MotorPorts.FrontRight);
  // public WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.MotorPorts.FrontLeft);
  // public WPI_TalonSRX backRight = new WPI_TalonSRX(Constants.MotorPorts.BackRight);
  // public WPI_TalonSRX backLeft = new WPI_TalonSRX(Constants.MotorPorts.BackLeft);

  // Victors
  public WPI_VictorSPX frontRight = new WPI_VictorSPX(Constants.MotorPorts.FrontRight);
  public WPI_VictorSPX frontLeft = new WPI_VictorSPX(Constants.MotorPorts.FrontLeft);
  public WPI_VictorSPX backRight = new WPI_VictorSPX(Constants.MotorPorts.BackRight);
  public WPI_VictorSPX backLeft = new WPI_VictorSPX(Constants.MotorPorts.BackLeft);

  // Sensors
  public Encoder encoder = new Encoder(Constants.SensorPorts.EncoderPort1, Constants.SensorPorts.EncoderPort2);

  public void arcadeDrive(double stickX, double stickY)
  {
      SmartDashboard.putNumber("Stick X", stickX);
      SmartDashboard.putNumber("Stick Y", stickY);

      double lvalue = -stickY + stickX;
      double rvalue = -stickY - stickX;
      rvalue *= -1; // remove if the right side does not need to be inverted

      lvalue = clamp(lvalue, -1, 1);
      rvalue = clamp(rvalue, -1, 1);

      tankDrive(lvalue, rvalue);
      
      
  }

  public void tankDrive(double lvalue, double rvalue)
  {
    lvalue = clamp(lvalue, -1, 1);
    rvalue = clamp(rvalue, -1, 1);
    /*** Receives left and right side values between -1 and 1 and will set drive motor speeds accordingly ***/
    frontLeft.set(lvalue*speed);
    frontRight.set(rvalue*speed);
    backLeft.set(lvalue*speed);
    backRight.set(rvalue*speed);

    SmartDashboard.putNumber("Left Speed", lvalue);
    SmartDashboard.putNumber("Right Speed", rvalue);
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
