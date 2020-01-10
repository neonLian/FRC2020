/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SensorSubsystem extends SubsystemBase {
  /*** This subsystem will hold all sensors for the robot. ***/
  
  public Encoder encoder = new Encoder(Constants.SensorPorts.EncoderPort1, Constants.SensorPorts.EncoderPort2);
  
}
