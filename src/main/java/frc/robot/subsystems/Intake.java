package frc.robot.subsystems;


import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.IntakeConstants.*;

public class Intake extends SubsystemBase {
    private double intakePosition = 0;
    private double intakeSpeed = 0;
    
    private final SparkMax rollersMotor = new SparkMax(kRollersID, MotorType.kBrushless);
    private final SparkMax wristMotor = new SparkMax(kwristID, MotorType.kBrushless);
    
    private final RelativeEncoder wristEncoder = wristMotor.getEncoder();

    private final SparkMaxConfig rollerConfig = new SparkMaxConfig();
    private final SparkMaxConfig wristConfig = new SparkMaxConfig();


    private final TrapezoidProfile.Constraints wristConstraints = new TrapezoidProfile.Constraints(kWristMaxSpeed, kWristMaxAcceleration);
    private final ProfiledPIDController wristController = new ProfiledPIDController(kWristP, kWristI, kWristD, wristConstraints);

    public Intake(){
        rollerConfig
        .idleMode(IdleMode.kCoast)
        .smartCurrentLimit(40)
        .closedLoop
        .pid(intakePosition, intakePosition, intakeSpeed)
        ;
        wristConfig
        .idleMode(IdleMode.kBrake)
        .smartCurrentLimit(40)
        ;
        rollersMotor.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        wristMotor.configure(wristConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void runRollersIn(double _speed){
        intakeSpeed = _speed;
    }
    public void runRollersOut(double _speed){
        intakeSpeed = _speed;

    }
    public void setPosition(double _angle){
        intakePosition = _angle;
    }
    public void startIntaking(){
        intakePosition = kIntakePosition;
        intakeSpeed = kIntakeSpeed;
    }
    public void Stow(){
        intakePosition = 0;
        intakeSpeed = 0;
    }


    @Override
    public void periodic(){
        wristMotor.setVoltage(wristController.calculate(wristEncoder.getPosition(), intakePosition));
        rollersMotor.set(intakeSpeed);

    }
}
