package frc.robot.Managers;

import com.ctre.phoenix6.swerve.SwerveRequest;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import com.ctre.phoenix6.swerve.SwerveRequest.ApplyFieldSpeeds;
import com.ctre.phoenix6.swerve.SwerveRequest.ApplyRobotSpeeds;

public class SwerveDriveManager extends SubsystemBase {

    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); 
    private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond);
    

    private CommandSwerveDrivetrain drivetrain;
    private final SwerveRequest.FieldCentric fieldOrientedController = new SwerveRequest.FieldCentric()
    .withDeadband(MaxSpeed * .1)
    .withRotationalDeadband(MaxAngularRate * .1)
    .withDriveRequestType(DriveRequestType.OpenLoopVoltage);
    private final SwerveRequest.ApplyRobotSpeeds robotOrientedController = new ApplyRobotSpeeds();
    private CommandXboxController controller;
    private String swerveStatus = "NORMAL";
    private ChassisSpeeds rotatingSpeeds;
    private boolean check = false;

    public SwerveDriveManager(CommandSwerveDrivetrain _drivetrain, CommandXboxController _controller){
        drivetrain = _drivetrain;
        controller = _controller;
    }

    public void startAutoRotating(ChassisSpeeds _speeds){
        swerveStatus = "ROTATING";
        rotatingSpeeds = _speeds;

    }
    public void stopAutoRotating(){
        swerveStatus = "NORMAL";

    }

    public void update(){
        if(swerveStatus == "NORMAL"){
            check = false;
            //drivetrain.removeDefaultCommand();
            drivetrain.setControl( // NORMAL DRIVING STATE    
                fieldOrientedController.withVelocityX(-controller.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-controller.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-controller.getRightX() * MaxAngularRate
                        ) // Drive counterclockwise with negative X (left)
            
            );
        }else if(swerveStatus == "ROTATING"){
            //System.out.println(rotatingSpeeds.omegaRadiansPerSecond);
            check = true;
            
// NORMAL DRIVING STATE
                            
               drivetrain.setControl( fieldOrientedController.withVelocityX(0) // Drive forward with negative Y (forward)
                    .withVelocityY(0) // Drive left with negative X (left)
                    .withRotationalRate(rotatingSpeeds.omegaRadiansPerSecond
                        //-controller.getRightX() * MaxAngularRate
                        // Drive counterclockwise with negative X (left)
                    )
            );

        }
    }
    @Override
    public void periodic(){
        SmartDashboard.putBoolean("Check", check);
        SmartDashboard.putString(" Status ", swerveStatus);
        
    }
}
