# FRC2020

Lian's code for the 2020 season robot in the game Infinite Recharge.

The code currently has the following features:

* Arcade drive
* Rotational control using color sensor
* Positional control using color sensor

There is also some placeholder code written for autonomous, hanging, and shooting.

# Setup

## General
* Clone this repo
* Run ./gradlew to download gradle and needed FRC/Vendor libraries
* Run ./gradlew tasks to see available options

## Visual Studio Code (Official IDE)
* Get the WPILib extension for easiest use from the VSCode Marketplace - Requires Java 11 or greater
* In .vscode/settings.json, set the User Setting, java.home, to the correct directory pointing to your JDK 11 directory

## Basic Gradle Commands
* Run ./gradlew deploy to deploy to the robot in Terminal (\*nix) or Powershell (Windows)
* Run ./gradlew build to build the code. Use the --info flag for more details
* Run ./gradlew test to run all of the JUnit tests


# Subsystems

## DriveSubsystem

`DriveSubsystem.java` contains code for both arcade and tank drives. `arcadeDrive()` utilizes the `tankDrive()`, so any changes made to the `tankDrive` function will also apply to `arcadeDrive`.

This subsystem also contains all of the talon/victor objects for the drive motors. The motor ports used are from `Constants.java`.

`DriveSubsystem` is currently being used by a `RunCommand()` defined in `RobotContainer`'s `configureButtonBindings()` function.

## HangSubsystem

This subsystem will be used for the motors or pneumatics systems that will help the robot hang on the shield generator in the endgame. This part of the robot has not been designed yet so this subsystem is incomplete.

`HangSubsystem` is currently not being used by any command.

## RotateSubsystem

`RotateSubsystem` contains code for reading the color value from the color sensor and classifying that color as Blue, Red, Green, Yellow, or Unknown. It also contains code for finding the target color to rotate the control panel to in stage 3. The color sensor object and encoder sensor object are stored in this subsystem. This subsystem contains the talon/victor object for rotating the control panel and a `setSpeed()` method for setting the speed of that motor.

`getColor()` returns a `edu.wpi.first.wpilibj.util.Color` object that contains the raw values of the color detected from the color sensor.

`guessColor(Color color)` returns a `ColorType` enum value that represents if the passed in color is closest to Blue, Red, Green, or Yellow (or Unknown if the current color is not similar to any of those). The `ColorType` enum is specified in `ColorType.java`. This will work if the color sensor's LED is on or off, but it is generally more accurate when the LED is on.

`getTargetColor()` returns `'R'`, `'B'`, `'G'`, or `'Y'` depending on which value was received by the DriverStation's game data. If no value was received (this will happen when stage 3 has not been reached) then `getTargetColor()` will return the integer `0` (not `'0'`)

`setSpeed(double speed)` will set the rotate motor to the speed specified multiplied by `Constants.Speeds.RotateSpeed`.

`RotateSubsystem` is currently used by the `RotationalControl` and `PositionalControl` commands. 

# Commands

## AutonomousCommand

`AutonomousCommand.java` currently has some placeholder code for an autonomous that will shoot three power cells at the target, back up, pick up more power cells, and launch those power cells at the target. This command has not been tested and requires more code for timing power cell shots, loading power cells, and turning the robot after shooting.

## RotationalControl

`RotationalControl.java` will enable the rotate motor in `RotateSubsystem` until the robot has seen the same color 8 times (each color appears twice on the color panel, there are an extra 2 times in case a color doesn't get detected). If the robot cannot recognize the color when the command is initialized, it will be automatically canceled.

`RotationalControl` is currently binded to a joystick button (the exact button is found in `Constants.OI.AutoRotationalControl`).

## PositionalControl

`PositionalControl.java` will enable the robot motor in `RotateSubsystem` until the robot has seen the color that is perpendicular to the target color returned from `RotateSubsystem::getTargetColor`. If there is no target color returned (this will happen if stage 3 has not been reached), the command will automatically be canceled.

`PositionalControl` is currently binded to a joystick button (the exact button is found in `Constants.OI.AutoPositionalControl`).

# Constants

All variables in `Constants.java` are `public static final`. Constants are organized in the following subclasses: `MotorPorts`, `OI`, `SensorPorts`, `Speeds`, and `Pneumatics`.

To get a value from `Constants`, use the format `Constants.<subclass>.<value>`.
Example: `Constants.MotorPorts.FrontRight`

# RobotContainer

`RobotContainer.java` contains all subsystem and command objects as well as OI objects and code. The OI code is written in `configureButtonBindings()`. It currently binds the joystick axis to arcade drive and binds buttons to the rotational and positional control commands. There is also a button that will output the current color detected to SmartDashboard.
