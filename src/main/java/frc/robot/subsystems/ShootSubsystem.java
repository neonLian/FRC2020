/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.PIDControl;

public class ShootSubsystem extends SubsystemBase {

  // CANSparkMax shootWheel = new CANSparkMax(Constants.MotorPorts.Shoot1, MotorType.kBrushless);
  // CANSparkMax shootWheel2 = new CANSparkMax(Constants.MotorPorts.Shoot2, MotorType.kBrushless);

  TalonFX shootWheel = new TalonFX(Constants.MotorPorts.Shoot1);
  // PIDControl pidControl = new PIDControl(0.05, 0.001, 0.03);
  double targetRPM = 600;

  public double P = 0.05;
  public double I = 0.001;
  public double D = 0.03;

  public ShootSubsystem()
  {

    int timeoutMs = 0;
    int pidIdx = 0;

    shootWheel.configFactoryDefault();

    shootWheel.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, pidIdx, timeoutMs);
    shootWheel.setSensorPhase(true);

    // Coast mode is preferred for shooting because brake mode can damage the motor at high speeds
    shootWheel.setNeutralMode(NeutralMode.Coast);

    shootWheel.config_kF(pidIdx, 1);
    shootWheel.config_kP(pidIdx, P);
    shootWheel.config_kI(pidIdx, I);
    shootWheel.config_kD(pidIdx, D);
  }

  @Override
  public void periodic()
  {
    if (isShooting)
    {
      double targetVelocity_UnitsPer100ms = targetRPM * 2048 / 600.0;
      shootWheel.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
    }
    else
    {
      shootWheel.set(ControlMode.PercentOutput, 0);
    }
  }

  private boolean isShooting = false;

  public void startShooting() { isShooting = true; }
  public void stopShooting() { isShooting = false; }

  /** Loading **/
  DigitalInput irSensor = new DigitalInput(Constants.SensorPorts.IRSensor);
  
  public boolean isLoaded()
  {
    return irSensor.get();
  }

  /** Helper methods **/

  private static double clamp (double x, double min, double max)
  {
    return Math.max(Math.min(x, max), min);
  }

}
