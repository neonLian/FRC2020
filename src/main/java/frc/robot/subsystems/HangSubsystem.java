/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HangSubsystem extends SubsystemBase {
  
  private boolean isRising = false;
  private boolean isLowering = false;

  public void startRising()
  {
    isRising = true;
    isLowering = false;
  }

  public void stopRising()
  {
    isRising = false;
    isLowering = false;
  }

  public void startLowering()
  {
    isLowering = true;
    isRising = false;
  }

  public void stopLowering()
  {
    isRising = false;
    isLowering = false;
  }

  public boolean isRising()
  {
    return isRising;
  }

  public boolean isLowering()
  {
    return isLowering;
  }


  /*** Motor version ***/
  Talon hangMotor1 = new Talon(Constants.MotorPorts.Hang1);
  
  @Override
  public void periodic()
  {
    if (isRising())
    {
      hangMotor1.set(Constants.Speeds.HangSpeed);
    }
    else if (isLowering())
    {
      hangMotor1.set(-Constants.Speeds.HangSpeed);
    }
    else
    {
      hangMotor1.set(0);
    }
  }

  /** Pneumatics version **/

}
