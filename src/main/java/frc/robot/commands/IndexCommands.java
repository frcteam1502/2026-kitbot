package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.team1502.Driver;

public class IndexCommands extends Command {
    public final IndexSubsystem m_subsystem;
    public IndexCommands(IndexSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        Driver.B   
            .onTrue(new InstantCommand(() -> m_subsystem.setFeederPower(0.4)))
            .onFalse(new InstantCommand(() -> m_subsystem.setFeederPower(0)));
    }

    @Override
    public void execute(){
        
        //We can use this for live stuff
    }
}
