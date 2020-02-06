/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightAutoAim extends CommandBase {
  
  private LimelightSubsystem limelight;
  private DriveSubsystem driveSubsystem;

  private int step = 1;

  private final double TX_THRESHOLD = 0.6;
  private final double TY_THRESHOLD = 0.6;


  public LimelightAutoAim(DriveSubsystem drive, LimelightSubsystem limelightSubsystem) {
    driveSubsystem = drive;
    limelight = limelightSubsystem;
    addRequirements(driveSubsystem, limelightSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveSubsystem.resetPID();
    SmartDashboard.putString("Limelight Status", "Starting Auto Aim");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("tv", limelight.getTv());
    if (step == 1)
    {
      double tx = limelight.getTx();
      double driveX = driveSubsystem.PIDCustom(tx);
      
      SmartDashboard.putString("Limelight Status", "Rotating to target");
      SmartDashboard.putNumber("tx", tx);
      SmartDashboard.putNumber("driveX", driveX);

      driveSubsystem.arcadeDrive(driveSubsystem.PIDCustom(tx), 0);
      if (Math.abs(tx) <= TX_THRESHOLD) 
      {
        driveSubsystem.resetPID();
        step++;
      }
    }
    else if (step == 2)
    {
      double ty = limelight.getTy();
      double driveY = -driveSubsystem.PIDCustom(ty);
     
      SmartDashboard.putString("Limelight Status", "Distancing");
      SmartDashboard.putNumber("ty", ty);
      SmartDashboard.putNumber("driveY", driveY);

      driveSubsystem.arcadeDrive(0, driveY);
      if (Math.abs(ty) <= TY_THRESHOLD)
      {
        driveSubsystem.resetPID();
        step++;
      }
    }
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putString("Limelight Status", "Stopped");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return step >= 3;
  }
}
