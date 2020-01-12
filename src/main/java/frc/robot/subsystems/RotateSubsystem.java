/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RotateSubsystem extends SubsystemBase {
  /*** This subsystem is for operating the robot mechanism to rotate the control panel. ***/
  
  // WPI_TalonSRX rotateMotor = new WPI_TalonSRX(Constants.MotorPorts.RotateMotor);

  public void setSpeed(double speed)
  {
    // TODO: Rotate motor
    // rotateMotor.set(speed);
  }

  public char getTargetColor()
  {
    /*** Returns a character representing the target color to rotate to ('R', 'B', 'G', or 'Y') or 0 if the color is currently unknown***/
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0)
    {
      SmartDashboard.putString("Game data", String.valueOf(gameData.charAt(0)));
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
