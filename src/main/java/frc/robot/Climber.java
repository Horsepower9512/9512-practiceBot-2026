package frc.robot;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj2.command.Command;

public class Climber extends SubsystemBase{
    private double climbarm = 0.0;
    private int climbarm_ID = 21;

   
    private final SparkMax climbarmmoter = new SparkMax(climbarm_ID, MotorType.kBrushless);

    private final SparkMaxConfig climbarmconfig = new SparkMaxConfig();

    private final SparkClosedLoopController climbarmcontroller = climbarmmoter.getClosedLoopController();

    public Climber(){
        climbarmconfig
        .smartCurrentLimit(40)
        .idleMode(IdleMode.kBrake)
        .closedLoop
        .pid(1,0,0)
        ;
        climbarmmoter.configure(climbarmconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    @Override
    public void periodic(){ 
        climbarmmoter.set(climbarm);

    }
    
}
