// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants{
    public static final int kDriverControllerPort = 0;
    public static final int kDriverArmControllerPort = 1;

    //Controller Button ID's
    //Intake
    public static final int kIntakeInput = 1;
    public static final int kIntakeOutput = 3;

    //Arm
    public static final int kJ1Up = 8;
    public static final int kJ1Down = 7;
    public static final int kJ2Up = 6;
    public static final int kJ2Down = 5;

  }

  public static class DriveConstants {
    // SparkMax ID's
    public static final int kDriveNeoRightMotorId = 1;
    public static final int kDriveCimRightMotorId = 2;
    public static final int kDriveNeoLeftMotorId = 3;
    public static final int kDriveCimLeftMotorId = 4;

    // Encoder Constants
    public static final int kEncoderTick2Meter = 8192;

    // Drive Velocitys
    public static final double kDriveSpeedVelocity = 0.4;
    public static final double kDriveRotationVelocity = 0.4;

    // Arm Manual Control Velocitys
    public static final double kJ1ManualControlVelocity = 0.3;
    public static final double kJ2ManualControlVelocity = 0.4;
  }

  public static class IntakeConstants {
    public static final int kIntakeMotorId = 8;
    public static final double kIntakeVelocity = 0.7;
  }

  public static class ArmConstants{
    // SparkMax ID's
    public static final int kNeoJoint1Id = 7;
    public static final int kNeoJoint2Id = 5;
    public static final int kCimJoint2Id = 6;
  }

  public static class KinematicsConstants{
    public static final int kTrackWidth = 70;
    public static final double kLinearVelocity = 2.0;
    public static final double kAngularVelocity = Units.degreesToRadians(1);


  }




}
