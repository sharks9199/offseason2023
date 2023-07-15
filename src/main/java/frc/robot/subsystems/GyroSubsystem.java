package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GyroSubsystem extends SubsystemBase{

    AHRS gyro = new AHRS(SPI.Port.kMXP);

    public GyroSubsystem(){
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Yaw",gyro.getYaw());
    }

    public float getPitch(){return gyro.getPitch();}
    public float getRoll(){return gyro.getRoll();}
    public double getYaw(){return gyro.getYaw();}
    public Rotation2d getRotation2d(){return gyro.getRotation2d();}

    
}
