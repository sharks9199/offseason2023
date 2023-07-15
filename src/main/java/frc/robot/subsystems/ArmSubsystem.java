// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ArmConstants;


public class ArmSubsystem extends SubsystemBase {

    private final CANSparkMax neoJ1 = new CANSparkMax(ArmConstants.kNeoJoint1Id,MotorType.kBrushless);
    private final CANSparkMax neoJ2 = new CANSparkMax(ArmConstants.kNeoJoint2Id,MotorType.kBrushless);
    private final CANSparkMax CimJ2 = new CANSparkMax(ArmConstants.kCimJoint2Id,MotorType.kBrushed);

    private static final SparkMaxAlternateEncoder.Type kAltEncType = SparkMaxAlternateEncoder.Type.kQuadrature;
    private RelativeEncoder j1Encoder = neoJ1.getAlternateEncoder(kAltEncType, DriveConstants.kEncoderTick2Meter);
    private RelativeEncoder j2Encoder = neoJ2.getAlternateEncoder(kAltEncType, DriveConstants.kEncoderTick2Meter);


  public double getEncoderPosition(int jointNumber){
    if (jointNumber == 1){return (j1Encoder.getPosition()*DriveConstants.kEncoderTick2Meter);}
    if (jointNumber == 2){return (j2Encoder.getPosition()*DriveConstants.kEncoderTick2Meter);}
    else{return 0.0;}
  }
  
  public ArmSubsystem() {
    //CimJ2.follow(neoJ2);
    //CimJ2.setInverted(true);
    }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("J1 Position",getEncoderPosition(1));
    SmartDashboard.putNumber("J2 Position",getEncoderPosition(2));
  }

  public void setJ1Motor(double speed){neoJ1.set(speed);}
  public void setJ2Motor(double speed){CimJ2.set(speed);}

  public void setJ1State(String state){
    if (state=="up"){setJ1Motor(-DriveConstants.kJ1ManualControlVelocity);}
    if (state=="down"){setJ1Motor(DriveConstants.kJ1ManualControlVelocity);}
    if (state=="static"){setJ1Motor(0);}
  }

  public void setJ2State(String state){
    if (state=="up"){setJ2Motor(-DriveConstants.kJ2ManualControlVelocity);}
    if (state=="down"){setJ2Motor(DriveConstants.kJ2ManualControlVelocity);}
    if (state=="static"){setJ2Motor(0);}
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
