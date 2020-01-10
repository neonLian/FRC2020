/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    public static class MotorPorts
    {
        public static final int FrontRight = 0;
        public static final int FrontLeft = 1;
        public static final int BackRight = 2;
        public static final int BackLeft = 3;
        public static final int RotateMotor = 4;

        public static final int Shoot1 = 5;
        public static final int Shoot2 = 6;

        public static final int Hang1 = 7;
    }

    public static class OI
    {
        public static final int RotateButton = 0;
        public static final int ShootButton = 1;
    }

    public static class SensorPorts
    {
        public static final int EncoderPort1 = 0;
        public static final int EncoderPort2 = 1;
    }

    public static class Speeds
    {
        public static final double ShootSpeed = 0.8;
        public static final double DriveSpeed = 0.3;
        public static final double HangSpeed = 0.4;
    }
}
