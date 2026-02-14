package frc.robot.Managers;

import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Limelight.LimelightHelpers;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
public class VisionManager extends SubsystemBase{
    private double ID;
    private SwerveDriveManager swerve;

    private double Tx;
    ChassisSpeeds speeds;



    private final TrapezoidProfile.Constraints thetaConstraints = new TrapezoidProfile.Constraints(.8, 2);
    private final ProfiledPIDController thetaController = new ProfiledPIDController(.03, .06, .03, thetaConstraints);

    public VisionManager(SwerveDriveManager _swerve){
        LimelightHelpers.setPipelineIndex("limelight-praccam", 9);
        Tx = LimelightHelpers.getTX("limelight-praccam");
        swerve = _swerve;
        
    }
    public Command check(){
        return Commands.runOnce(() -> {System.out.println(Tx);});
    }
    public Command autoRotate(){

        
         return Commands.runEnd(() -> swerve.startAutoRotating(speeds),() ->  swerve.stopAutoRotating(), this);
        };

    

    @Override
    public void periodic(){
        ID = LimelightHelpers.getFiducialID("limelight-praccam");
        Tx = LimelightHelpers.getTX("limelight-praccam");
        speeds = new ChassisSpeeds(0, 0, thetaController.calculate(Tx,0));



    }
}
