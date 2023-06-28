// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class ArcadeDriveSubsystem extends SubsystemBase {
  /** Creates a new ArcadeDriveSubsystem. */
  private static final int LeftNeoId  = 1;
  private static final int LeftCIMId  = 2;
  private static final int RightNeoId = 3;
  private static final int RightCIMId = 4;
  private final CANSparkMax driveLeftMotorNeo = new CANSparkMax(LeftNeoId,MotorType.kBrushless);
  private final CANSparkMax driveLeftMotorCIM = new CANSparkMax (LeftCIMId,MotorType.kBrushed);
  private final CANSparkMax driveRightMotorNeo = new CANSparkMax(RightNeoId,MotorType.kBrushless);
  private final CANSparkMax driveRightMotorCIM = new CANSparkMax(RightCIMId,MotorType.kBrushed);
  private final DifferentialDrive tankRobot;

  public ArcadeDriveSubsystem() 
  {
    //Inverting the rotation on robot's right motors
    driveLeftMotorCIM.setInverted(false);
    driveLeftMotorNeo.setInverted(false);
    driveRightMotorCIM.setInverted(true);
    driveRightMotorNeo.setInverted(true);

    //Restoring FactoryDefaults for the SparkMax Motor Controllers
    driveLeftMotorCIM.restoreFactoryDefaults();
    driveLeftMotorNeo.restoreFactoryDefaults();
    driveRightMotorCIM.restoreFactoryDefaults();
    driveRightMotorNeo.restoreFactoryDefaults();

    //Configuring followerMode to CIM Motors
    driveLeftMotorCIM.follow(driveLeftMotorNeo);
    driveRightMotorCIM.follow(driveRightMotorNeo);

    //Configuring arcade mode
    tankRobot = new DifferentialDrive(driveLeftMotorNeo, driveRightMotorNeo);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getEncoder(int MotorId)
  {
    RelativeEncoder encoderValue;
    double position = 0 ;
    // 1 - EsquerdaNeo, 2 - EsquerdaCIM, 3 - DireitaNEO, 4 - DireitaCIM
    // Lembre-se que o motor CIM não tem encoder integrado. Só é possível usar os ID 1 e 3.
   
    switch(MotorId)
    {
      case 1: 
        encoderValue = driveLeftMotorNeo.getEncoder(); 
        encoderValue.setPositionConversionFactor(47.88/360);
        position = encoderValue.getPosition();
        break;
      case 3: 
        encoderValue = driveRightMotorNeo.getEncoder();
        encoderValue.setPositionConversionFactor(47.88/360);
        position = encoderValue.getPosition();
        break;
      default: System.out.print("Leitura de encoder inválida para este item.");
    }
    
    if(MotorId != 1 && MotorId != 3)
      return 0;
    else
      return position; 
  }
}
