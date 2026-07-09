package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommands extends Command {
    public final IntakeSubsystem m_subsystem;
    public IntakeCommands(IntakeSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }
    
    @Override
    
    public void initialize(){
        
        }

    @Override
    public void execute(){
        
        //We can use this for live stuff
    }
}
