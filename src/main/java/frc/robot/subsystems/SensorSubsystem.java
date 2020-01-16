/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ColorType;
import frc.robot.Constants;

public class SensorSubsystem extends SubsystemBase {
  /*** This subsystem will hold all sensors for the robot. ***/
  
  public Encoder encoder = new Encoder(Constants.SensorPorts.EncoderPort1, Constants.SensorPorts.EncoderPort2);
  
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

}