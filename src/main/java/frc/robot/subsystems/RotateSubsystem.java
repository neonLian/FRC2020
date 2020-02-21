/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import frc.robot.ColorType;
import frc.robot.Constants;

public class RotateSubsystem extends SubsystemBase {
  /*** This subsystem is for operating the robot mechanism to rotate the control panel. ***/
  
  WPI_TalonSRX rotateMotor = new WPI_TalonSRX(Constants.MotorPorts.RotateMotor);

  public RotateSubsystem()
  {
    rotateMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void setSpeed(double speed)
  {
    rotateMotor.set(speed*Constants.Speeds.RotateSpeed);
  }

  public ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

  public Color getColor()
  {
    /*** Gets the raw color values from the color sensor ***/
    return colorSensor.getColor();
  }

  public ColorType guessColor(Color color)
  {
    /*** Attempts to determine if the given color is red, green, blue or yellow. ***/
    if (color.red < 0.35 && color.green >= 0.4 && color.blue >= 0.3)
      return ColorType.Blue;
    else if (color.red < 0.3 && color.green >= 0.5 && color.blue >= 0.2)
      return ColorType.Green;
    else if (color.red >= 0.4 && color.green < 0.45 && color.blue <= 0.2)
      return ColorType.Red;
    else if (color.red >= 0.3 && color.green >= 0.45 && color.blue < 0.2)
      return ColorType.Yellow;
    else
      return ColorType.Unknown;
  }

  public boolean isControlPanelColor(Color color)
  {
    return guessColor(color) != ColorType.Unknown;
  }

  public boolean isControlPanelColor(ColorType colorType)
  {
    return colorType != ColorType.Unknown;
  }

  public String colorString(Color color)
  {
    return String.format("(%.2f, %.2f, %.2f)", color.red, color.green, color.blue);
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
