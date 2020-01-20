/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  private DriveSubsystem driveSubsystem = new DriveSubsystem();
  private ShootSubsystem shootSubsystem = new ShootSubsystem();
  private RotateSubsystem rotateSubsystem = new RotateSubsystem();
  private LimelightSubsystem limelightSubsystem = new LimelightSubsystem();

  private Command autonomousCommand = new AutonomousCommand(driveSubsystem, shootSubsystem);
  
  private Joystick joystick = new Joystick(0);
  private JoystickButton shootButton = new JoystickButton(joystick, Constants.OI.ShootButton);
  private JoystickButton autoRotateControlButton = new JoystickButton(joystick, Constants.OI.AutoRotationalControl);
  private JoystickButton autoPositionControlButton = new JoystickButton(joystick, Constants.OI.AutoPositionalControl);
  private JoystickButton autoAimButton = new JoystickButton(joystick, Constants.OI.AutoAimButton);
  
  private JoystickButton debugButton = new JoystickButton(joystick, 3);

  // Constructor
  public RobotContainer()
  {
      configureButtonBindings();
  }

  private void configureButtonBindings()
  {
    // Map the joystick axis to drive
    RunCommand rc = new RunCommand(() -> driveSubsystem.arcadeDrive(joystick.getX(), joystick.getY()), driveSubsystem);
    driveSubsystem.setDefaultCommand(rc);

    // Map buttons
    shootButton.whileHeld(new StartEndCommand(shootSubsystem::startShooting, shootSubsystem::stopShooting));
    autoRotateControlButton.whenPressed(new RotationalControl(rotateSubsystem));
    autoPositionControlButton.whenPressed(new PositionalControl(rotateSubsystem));

    debugButton.whileHeld(new InstantCommand(() -> {
      SmartDashboard.putString("Debug color", rotateSubsystem.colorString(rotateSubsystem.getColor()));
      SmartDashboard.putString("Color guess", rotateSubsystem.guessColor(rotateSubsystem.getColor()).name());
    }));

    autoAimButton.whenPressed(new LimelightAutoAim(driveSubsystem, limelightSubsystem));


  }

  // Add any code for selecting which autonomous command is used in this method.
  public Command getAutonomousCommand() {
    return autonomousCommand;
  }
}
