/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveDrive extends CommandBase {

	public final double L = 1; // distance between each wheel axle (length)
	public final double W = 1; // distance between each wheel axle (width)

	// x1 and y1 are from strafing joystick
	// x2 is from the rotation joystick
	public void drive (double x1, double y1, double x2)
	{
		double r = Math.sqrt((L * L) + (W * W));
		y1 *= -1;

		// a, b, c, and d are between 0 and 1
		double a = x1 - x2 * (L / r);
		double b = x1 + x2 * (L / r);
		double c = y1 - x2 * (W / r);
		double d = y1 + x2 * (W / r);

		// Compute motor speeds
		double backRightSpeed = Math.sqrt((a * a) + (d * d));
		double backLeftSpeed = Math.sqrt((a * a) + (c * c));
		double frontRightSpeed = Math.sqrt((b * b) + (d * d));
		double frontLeftSpeed = Math.sqrt((b * b) + (c * c));

		// Compute wheel angles
		double backRightAngle = Math.atan2(a, d) / Math.PI;
		double backLeftAngle = Math.atan2(a, c) / Math.PI;
		double frontRightAngle = Math.atan2(b, d) / Math.PI;
		double frontLeftAngle = Math.atan2(b, c) / Math.PI;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
