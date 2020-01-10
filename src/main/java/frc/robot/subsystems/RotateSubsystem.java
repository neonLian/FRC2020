/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RotateSubsystem extends SubsystemBase {
  /*** This subsystem is for operating the robot mechanism to rotate the control panel. ***/
  
  Talon rotateMotor = new Talon(Constants.MotorPorts.RotateMotor);

  public void rotationalControl()
  {
    
  }

  public void positionalControl()
  {

  }

  public char getTargetColor()
  {
    /*** Returns a character representing the target color to rotate to ('R', 'B', 'G', or 'Y') or 0 if the color is currently unknown***/
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0)
    {
      return gameData.charAt(0);
    }
    else
    {
      return 0;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
