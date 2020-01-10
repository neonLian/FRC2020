/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.SensorSubsystem;
import frc.robot.subsystems.ShootSubsystem;

public class AutonomousCommand extends CommandBase {

  private DriveSubsystem driveSubsystem;
  private ShootSubsystem shootSubsystem;
  private SensorSubsystem sensorSubsystem;

  private boolean autonomousFinished = false;

  private int step = 1;
  
  public AutonomousCommand(DriveSubsystem driveSubsystem, ShootSubsystem shootSubsystem, SensorSubsystem sensorSubsystem) {
    this.driveSubsystem = driveSubsystem;
    this.shootSubsystem = shootSubsystem;
    this.sensorSubsystem = sensorSubsystem;
    addRequirements(driveSubsystem, shootSubsystem, sensorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Shoot balls
    if (step <= 3)
    {
      shootSubsystem.startShooting();
      step++; // TODO: Add extra conditions to determine when to stop shooting
    }
    // Reverse
    else if (step == 4)
    {
      shootSubsystem.stopShooting();
      sensorSubsystem.encoder.reset();
      sensorSubsystem.encoder.setDistancePerPulse(1);
      driveSubsystem.tankDrive(-1, -1);
      step++;
    }
    else if (step == 5)
    {
      if (sensorSubsystem.encoder.getDistance() > 5) // will probably be replaced by sensor code
      {
        sensorSubsystem.encoder.reset();
        driveSubsystem.tankDrive(1, 1);
        step++;
      } 
      // Activate loading mechanism
    }
    else if (step == 6)
    {
      if (sensorSubsystem.encoder.getDistance() > 5)
      {
        step++;
      }
    }
    else if (step == 7)
    {
      driveSubsystem.tankDrive(0, 0);
      step++;
    }
    else if (step >= 8 && step <= 10)
    {
      shootSubsystem.startShooting();
      step++; // TODO: Add extra conditions to determine when to stop shooting
    }
    else
    {
      autonomousFinished = true;
    }
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return autonomousFinished;
  }
}
