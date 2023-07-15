// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.IntakeSetCmd;
import frc.robot.commands.Joint1SetCmd;
import frc.robot.commands.Joint2SetCmd;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ArmSubsystem armSubsystem = new ArmSubsystem();
  private final Joystick joystick1 = new Joystick(OperatorConstants.kDriverControllerPort);
  private final Joystick joystick2 = new Joystick(OperatorConstants.kDriverArmControllerPort);

  public RobotContainer(){
    driveSubsystem.setDefaultCommand(
      new ArcadeDriveCmd(driveSubsystem, () -> -joystick1.getY(), ()-> joystick1.getZ()));
    configureButtonBindings();
  }

  private void configureButtonBindings(){

    // ===== CONTROLES DO INTAKE =====
    new JoystickButton(joystick2, OperatorConstants.kIntakeInput).whileTrue(new IntakeSetCmd(intakeSubsystem, "input"));
    new JoystickButton(joystick2, OperatorConstants.kIntakeOutput).whileTrue(new IntakeSetCmd(intakeSubsystem, "output"));
    // ====================================================================================================================

    // ===== CONTROLE DOS AJUSTES MANUAIS DA GARRA - J1 =====
    new JoystickButton(joystick2, OperatorConstants.kJ1Up).whileTrue(new Joint1SetCmd(armSubsystem, "up"));
    new JoystickButton(joystick2, OperatorConstants.kJ1Down).whileTrue(new Joint1SetCmd(armSubsystem, "down"));
    // ===== CONTROLE DOS AJUSTES MANUAIS DA GARRA - J2 =====
    new JoystickButton(joystick2, OperatorConstants.kJ2Up).whileTrue(new Joint2SetCmd(armSubsystem, "up"));
    new JoystickButton(joystick2, OperatorConstants.kJ2Down).whileTrue(new Joint2SetCmd(armSubsystem, "down"));
    // ====================================================================================================================



  }

}
