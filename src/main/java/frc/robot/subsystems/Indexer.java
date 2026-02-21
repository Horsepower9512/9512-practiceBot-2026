package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.IndexerConstants.*;

public class Indexer extends SubsystemBase {
    private double tankSpeeds = 0;
    private double kickerSpeeds = 0;

    private final SparkMax tankMotor = new SparkMax(kTankID, MotorType.kBrushless);
    private final SparkMax kickerMotor = new SparkMax(kKickerID, MotorType.kBrushless);

    private final SparkMaxConfig tankConfig = new SparkMaxConfig();
    private final SparkMaxConfig kickerConfig = new SparkMaxConfig();
    
    public Indexer(){
        tankConfig
        .smartCurrentLimit(40)
        .idleMode(IdleMode.kCoast)
        ;
        kickerConfig
        .smartCurrentLimit(40)
        .idleMode(IdleMode.kBrake)
        ;
        tankMotor.configure(tankConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        kickerMotor.configure(kickerConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    }


    public void stopTank(){
        tankSpeeds = 0;
    }

    public void stopKicker(){
        kickerSpeeds = 0;
    }
    
    public void kick(){
        kickerSpeeds = kKickingSpeed;
    }

    public void index

    @Override

    public void periodic(){
        kickerMotor.set(kickerSpeeds);
        tankMotor.set(tankSpeeds);
    }
}
