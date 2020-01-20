/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
  
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  
  public double getTx()
  {
    return table.getEntry("tx").getDouble(0);
  }

  public double getTy()
  {
    return table.getEntry("ty").getDouble(0);
  }

  @Override
  public void periodic() {
    // TODO Auto-generated method stub
    super.periodic();
    SmartDashboard.putNumber("TX", getTx());
  }
}
