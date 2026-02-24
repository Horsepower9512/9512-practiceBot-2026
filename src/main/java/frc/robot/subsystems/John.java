package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class John extends SubsystemBase{
    private final Launcher launcher = new Launcher();
    private final Intake intake = new Intake();
    private final Indexer indexer = new Indexer();
    private final Climber climber  = new Climber();

    public John(){

    }
    public Command intake(){
        return Commands.startEnd(() -> {
            intake.startIntaking();
            indexer.index();
        }, () -> {
            intake.stopRollers();
            indexer.stopTank();
        }, indexer, intake);
    }

    public Command shoot(){
        return Commands.startEnd(() -> {
            intake.Stow();
            indexer.index();
            indexer.kick();
            launcher.shoot();
        }, () -> {
            indexer.stopKicker();
            launcher.stop();
        }, intake, launcher, indexer);
    }

    public Command climb(){
        return Commands.startEnd(() -> {
            indexer.stopKicker();
            indexer.stopTank();
            launcher.stop();
            intake.Stow();
            climber.goUp();
        }, () -> {
            climber.stop();
        }, climber, intake, launcher, indexer);
    }

        public Command descend(){
        return Commands.startEnd(() -> {
            indexer.stopKicker();
            indexer.stopTank();
            launcher.stop();
            intake.Stow();
            climber.goDown();
        }, () -> {
            climber.stop();
        }, climber, intake, launcher, indexer);
    }

    
}   
