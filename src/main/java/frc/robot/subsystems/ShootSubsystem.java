/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShootSubsystem extends SubsystemBase {

  WPI_TalonSRX shootWheel1 = new WPI_TalonSRX(Constants.MotorPorts.Shoot1);
  WPI_TalonSRX shootWheel2 = new WPI_TalonSRX(Constants.MotorPorts.Shoot2);

  private boolean isShooting = false;

  public void startShooting()
  {
    isShooting = true;
  }

  public void stopShooting()
  {
    isShooting = false;
  }

  public void setShooting(boolean shoot) {
    isShooting = shoot;
  }

  @Override
  public void periodic()
  {
    if (isShooting)
    {
      shootWheel1.set(Constants.Speeds.ShootSpeed);
      shootWheel2.set(Constants.Speeds.ShootSpeed);
    }
    else
    {
      shootWheel1.set(0);
      shootWheel2.set(0);
    }
  }



  // Pneumatics
  // DoubleSolenoid doubleSolenoid = new DoubleSolenoid(1, 2);

  // public void startShooting()
  // {
  //   doubleSolenoid.set(Value.kForward);
  // }

  // @Override
  // public void periodic() {
  //   // This method will be called once per scheduler run
  //   doubleSolenoid.set(Value.kOff);
  // }
}
