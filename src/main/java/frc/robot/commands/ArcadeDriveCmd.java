// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.DriveConstants;

/** An example command that uses an example subsystem. */
public class ArcadeDriveCmd extends CommandBase {

  private final DriveSubsystem driveSubsystem;
  private final Supplier<Double> speedFunction, turnFunction;

  public ArcadeDriveCmd(DriveSubsystem driveSubsystem,
          Supplier<Double> speedFunction, Supplier<Double> turnFunction){
    this.speedFunction = speedFunction;
    this.turnFunction = turnFunction;
    this.driveSubsystem = driveSubsystem;
    addRequirements(driveSubsystem);
  }

  @Override
  public void initialize(){
    System.out.println("ArcadeDriveCmd Started");
  }

  @Override
  public void execute(){
    double realTimeSpeed = speedFunction.get() * DriveConstants.kDriveSpeedVelocity;
    double realTimeTurn = turnFunction.get() * DriveConstants.kDriveRotationVelocity;
    //Arcade Drive
    double left = realTimeSpeed + realTimeTurn;
    double right = realTimeSpeed - realTimeTurn;
    driveSubsystem.setMotors(left,right);
  }

  @Override
  public void end(boolean interrupted){

    System.out.println("ArcadeDriveCmd Ended");
  }

  @Override
  public boolean isFinished(){
    return false;
  }
}
