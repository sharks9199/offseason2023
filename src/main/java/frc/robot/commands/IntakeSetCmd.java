package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeSetCmd extends CommandBase{
 
    private final IntakeSubsystem intakeSubsystem;
    private String state;

    public IntakeSetCmd(IntakeSubsystem intakeSubsystem, String state){
        this.intakeSubsystem = intakeSubsystem;
        this.state = state;
    }

    @Override
    public void initialize(){  
        System.out.println("IntakeSetCmd Iniciado!");
    }

    @Override
    public void execute(){   
        intakeSubsystem.setIntake(state);
    }

    @Override
    public void end(boolean interrupted){   
        System.out.println("IntakeSetCmd Finalizado!");
        intakeSubsystem.setIntake("static");
    }

    @Override
    public boolean isFinished(){   
        return false;
    }



}
