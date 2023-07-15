package frc.robot.subsystems;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{

    private final CANSparkMax intakeMotor = new CANSparkMax(IntakeConstants.kIntakeMotorId,MotorType.kBrushed);
   

    public IntakeSubsystem(){
        intakeMotor.setIdleMode(IdleMode.kBrake);
        intakeMotor.setSmartCurrentLimit(7);
    }

    public void setIntake(String state){
        if(state == "input"){
            intakeMotor.set(-IntakeConstants.kIntakeVelocity);
        }
        if(state == "output"){
            intakeMotor.set(IntakeConstants.kIntakeVelocity);
        }
        if(state == "static"){
            intakeMotor.set(0);
        }
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Intake Current", intakeMotor.getOutputCurrent());
    }

    
}
