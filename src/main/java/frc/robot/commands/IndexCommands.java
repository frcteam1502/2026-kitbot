package frc.robot.commands;


import org.ejml.equation.Operation;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.team1502.Driver;
import frc.robot.team1502.Operator;

public class IndexCommands extends Command {
    public final IndexSubsystem m_subsystem;
    public IndexCommands(IndexSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    
    public void initialize(){
        Operator.LeftBumper
            .onTrue(new InstantCommand(() -> m_subsystem.setFeederPower(0.6)))
            .onTrue(new InstantCommand(() -> m_subsystem.setIndexPower(-1)))
            .onFalse(new InstantCommand(() -> m_subsystem.setFeederPower(0)))
            .onFalse(new InstantCommand(() -> m_subsystem.setIndexPower(0)));
        Operator.RightBumper
            .onTrue(new InstantCommand(() -> m_subsystem.setFeederPower(-0.6)))
            .onTrue(new InstantCommand(() -> m_subsystem.setIndexPower(1)))
            .onFalse(new InstantCommand(() -> m_subsystem.setIndexPower(0)))
            .onFalse(new InstantCommand(() -> m_subsystem.setFeederPower(0)));
        }

    @Override
    public void execute(){
        
        //We can use this for live stuff
    }
}
