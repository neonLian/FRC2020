/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
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
  public AnalogInput gyro = new AnalogInput(Constants.SensorPorts.Gyro);

  // PID Control
  private double P = 0.05;
  private double I = 0.02;
  private double D = 0.03;
  private double derivative, integral, error, previous_error = 0;
  private double setpoint = 0;
  

  public double PIDRotate()
  {
    error = Math.abs(setpoint - gyro.getValue());
    integral += error * 0.2;
    derivative = (error - previous_error) / .02;
    previous_error = error;
    return P * error + I * integral + D * derivative;
  }

  public double PIDDrive()
  {
    error = Math.abs(setpoint - encoder.get());
    integral += error * 0.2;
    derivative = (error - previous_error) / .02;
    previous_error = error;
    return P * error + I * integral + D * derivative;
  }

  public double PIDCustom(double error)
  {
    integral += error * 0.2;
    derivative = (error - previous_error) / .02;
    previous_error = error;
    return P * error + I * integral + D * derivative;
  }

  public void setSetpoint(double sp)
  {
    setpoint = sp;
  }

  public void resetPID()
  {
    error = 0;
    previous_error = 0;
    integral = 0;
  }



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
    P = SmartDashboard.getNumber("Editable: kP", P);
    I = SmartDashboard.getNumber("Editable: kI", I);
    D = SmartDashboard.getNumber("Editable: kD", D);
  }

  private static double clamp (double x, double min, double max)
  {
    return Math.max(Math.min(x, max), min);
  }
}
