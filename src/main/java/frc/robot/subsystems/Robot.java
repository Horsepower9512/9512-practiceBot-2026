package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Robot extends SubsystemBase{
    private final Launcher launcher = new Launcher();
    private final Intake intake = new Intake();
    private final Indexer indexer = new Indexer();
    private final Climber climber  = new Climber();

    public Robot(){

    }
    public Command intake(){
        return Commands.startEnd(() -> {
            intake.startIntaking();
            indexer.
        }, () -> {

        }, indexer, intake);
    }
}   
