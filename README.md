# FRC2020

Lian's code for the 2020 season robot in the game Infinite Recharge.

The code currently has the following features:

* Arcade drive
* Rotational control using color sensor
* Positional control using color sensor

There is also some placeholder code written for autonomous, hanging, and shooting.

# Subsystems

## DriveSubsystem

`DriveSubsystem.java` contains code for both arcade and tank drives. `arcadeDrive()` utilizes the `tankDrive()`, so any changes made to the `tankDrive` function will also apply to `arcadeDrive`.

This subsystem also contains all of the talon/victor objects for the drive motors. The motor ports used are from `Constants.java`.

`DriveSubsystem` is currently being used by a `RunCommand()` defined in `RobotContainer`'s `configureButtonBindings()` function.

## HangSubsystem

This subsystem will be used for the motors or pneumatics systems that will help the robot hang on the shield generator in the endgame. This part of the robot has not been designed yet so this subsystem is incomplete.

`HangSubsystem` is currently not being used by any command.

## RotateSubsystem

This subsystem contains the talon/victor object for rotating the control panel. It also contains a `setSpeed()` method for setting the speed of that motor.

`RotateSubsystem` is currently used by the `RotationalControl` and `PositionalControl` commands.

## SensorSubsystem

`SensorSubsystem.java` contains code for reading the color value from the color sensor and classifying that color as Blue, Red, Green, Yellow, or Unknown. It also contains code for finding the target color to rotate the control panel to in stage 3. The color sensor object and encoder sensor object are stored in this subsystem.

`getColor()` returns a `edu.wpi.first.wpilibj.util.Color` object that contains the raw values of the color detected from the color sensor.

`guessColor(Color color)` returns a `ColorType` enum value that represents if the passed in color is closest to Blue, Red, Green, or Yellow (or Unknown if the current color is not similar to any of those). The `ColorType` enum is specified in `ColorType.java`. This will work if the color sensor's LED is on or off, but it is generally more accurate when the LED is on.

`getTargetColor()` returns `'R'`, `'B'`, `'G'`, or `'Y'` depending on which value was received by the DriverStation's game data. If no value was received (this will happen when stage 3 has not been reached) then `getTargetColor()` will return the integer `0` (not `'0'`)

`SensorSubsystem` is currently used by the `RotationalControl` and `PositionalControl` commands.
