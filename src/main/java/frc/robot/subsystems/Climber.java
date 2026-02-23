package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import static frc.robot.Constants.ClimbConstants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase{
    private double climbarm = 0.0;


   
    private final SparkMax climbarmmoter = new SparkMax(kClimbID, MotorType.kBrushless);

    private final SparkMaxConfig climbarmconfig = new SparkMaxConfig();


    public Climber(){
        climbarmconfig
        .smartCurrentLimit(40)
        .idleMode(IdleMode.kBrake)
        .closedLoop
        .pid(1,0,0)
        ;
        climbarmmoter.configure(climbarmconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void goUp(){
        climbarm = kClimbSpeed; 
    }
    public void goDown(){
        climbarm = kFallSpeed;
    }
    public void stop(){
        climbarm = 0;
    }
    @Override
    public void periodic(){ 
        climbarmmoter.set(climbarm);

    }
    
}
