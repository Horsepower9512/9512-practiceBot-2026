package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import static frc.robot.Constants.LauncherConstants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Launcher extends SubsystemBase {
    private double shootingSpeeds = 0;
    
    private final SparkMax shooter = new SparkMax(kShooterID, MotorType.kBrushless);

    private final SparkMaxConfig shooterConfig =  new SparkMaxConfig();

    public Launcher(){
        shooterConfig
        .smartCurrentLimit(40)
        .idleMode(IdleMode.kCoast)
        ;

        shooter.configure(shooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    public void setShooterSpeed(double _speed){
        shootingSpeeds = _speed;
    }


    @Override

    public void periodic(){
        shooter.set(shootingSpeeds);
    }
}
