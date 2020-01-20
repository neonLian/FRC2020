/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ColorType;
import frc.robot.subsystems.RotateSubsystem;

public class PositionalControl extends CommandBase {
  
  private char targetColor = 0;
  private boolean done = false;

	private RotateSubsystem rotateSubsystem;
  
	public PositionalControl(RotateSubsystem rotateSubsystem) {
		this.rotateSubsystem = rotateSubsystem;
		addRequirements(rotateSubsystem);
	}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;

    targetColor = rotateSubsystem.getTargetColor(); // targetColor will be 0 if stage 3 has not been reached
    if (targetColor == 0)
      done = true;
    else
      rotateSubsystem.setSpeed(1);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ColorType currentColor = rotateSubsystem.guessColor(rotateSubsystem.getColor());
    if (targetColor == 0)
      done = true;
    else if (targetColor == 'R' && currentColor == ColorType.Blue)
      done = true;
    else if (targetColor == 'B' && currentColor == ColorType.Red)
      done = true;
    else if (targetColor == 'G' && currentColor == ColorType.Yellow)
      done = true;
    else if (targetColor == 'Y' && currentColor == ColorType.Green)
      done = true;
    
    SmartDashboard.putString("Status", "Positional control");
    SmartDashboard.putString("Current color", currentColor.name());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    rotateSubsystem.setSpeed(0);
    SmartDashboard.putString("Status", "");
    SmartDashboard.putString("Current color", SmartDashboard.getString("Current color", "") + " (stopped)");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
