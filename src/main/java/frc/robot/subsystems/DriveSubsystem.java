// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.kinematics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.KinematicsConstants;
import frc.robot.subsystems.GyroSubsystem;


public class DriveSubsystem extends SubsystemBase {
  
  private final CANSparkMax leftMotor = new CANSparkMax(DriveConstants.kDriveNeoLeftMotorId,MotorType.kBrushless);
  private final CANSparkMax rightMotor = new CANSparkMax(DriveConstants.kDriveNeoRightMotorId,MotorType.kBrushless);
  private final CANSparkMax followerLeftMotor = new CANSparkMax(DriveConstants.kDriveCimLeftMotorId,MotorType.kBrushed);
  private final CANSparkMax followerRightMotor = new CANSparkMax(DriveConstants.kDriveCimRightMotorId,MotorType.kBrushed);
  private static final SparkMaxAlternateEncoder.Type kAltEncType = SparkMaxAlternateEncoder.Type.kQuadrature;
  private RelativeEncoder leftEncoder = leftMotor.getAlternateEncoder(kAltEncType, DriveConstants.kEncoderTick2Meter);
  private RelativeEncoder rightEncoder = rightMotor.getAlternateEncoder(kAltEncType, DriveConstants.kEncoderTick2Meter);
  
  private GyroSubsystem gyro = new GyroSubsystem();

  //================== Kinematics ==================
  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(KinematicsConstants.kTrackWidth);
  ChassisSpeeds chassisSpeeds = new ChassisSpeeds(KinematicsConstants.kLinearVelocity,0, KinematicsConstants.kAngularVelocity);
  DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(chassisSpeeds);
  double leftVelocity = wheelSpeeds.leftMetersPerSecond;
  double rightVelocity = wheelSpeeds.rightMetersPerSecond;

  //================== Odometry ==================
  Pose2d pose;
  DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
  gyro.getRotation2d(),
  leftEncoder.getPosition(), rightEncoder.getPosition(),
  new Pose2d(5.0, 13.5, new Rotation2d()));
  //==============================================
 
  public double getEncoderMeters(){
    return(leftEncoder.getPosition()+ -rightEncoder.getPosition())/2*DriveConstants.kEncoderTick2Meter;
  }

  public DriveSubsystem() {
    followerLeftMotor.follow(leftMotor);
    followerLeftMotor.setInverted(true);
    followerRightMotor.follow(rightMotor);
    followerRightMotor.setInverted(true);
  }
  

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Cim Right", followerRightMotor.getOutputCurrent());
    SmartDashboard.putNumber("Cim Left", followerLeftMotor.getOutputCurrent());
    SmartDashboard.putNumber("Neo Right", rightMotor.getOutputCurrent());
    SmartDashboard.putNumber("Neo Left", leftMotor.getOutputCurrent());

    var gyroAngle = gyro.getRotation2d();
    pose = odometry.update(gyroAngle,
    leftEncoder.getPosition(),
    rightEncoder.getPosition());
  }

  public void setMotors(double LeftSpeed, double RightSpeed){
    leftMotor.set(LeftSpeed);
    rightMotor.set(RightSpeed); 
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }






}
