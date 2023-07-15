package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class Joint1SetCmd extends CommandBase{
 
    private final ArmSubsystem armSubsystem;
    private String state;

    public Joint1SetCmd(ArmSubsystem armSubsystem, String state){
        this.armSubsystem = armSubsystem;
        this.state = state;
    }

    @Override
    public void initialize(){  
        System.out.println("Joint1SetCmd Iniciado!");
    }

    @Override
    public void execute(){   
        armSubsystem.setJ1State(state);
    }

    @Override
    public void end(boolean interrupted){   
        System.out.println("Joint1SetCmd Finalizado!");
        armSubsystem.setJ1State("static");
    }

    @Override
    public boolean isFinished(){   
        return false;
    }



}
