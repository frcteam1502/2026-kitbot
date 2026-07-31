package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.team1502.Driver;

public class IntakeCommands extends Command {
    public final IntakeSubsystem m_subsystem;
    public IntakeCommands(IntakeSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }
    
    @Override
    
    public void initialize(){
            Driver.B   
            .onTrue(new InstantCommand(() -> m_subsystem.setIntakePower(0.4)))
            .onFalse(new InstantCommand(() -> m_subsystem.setIntakePower(0)));
            Driver.X
            .onTrue(new InstantCommand(() -> m_subsystem.setIntakeLocation(0.1)))
            .onFalse(new InstantCommand(() -> m_subsystem.setIntakeLocation(0)));
        }

    @Override
    public void execute(){
        
        //We can use this for live stuff
    }
}
