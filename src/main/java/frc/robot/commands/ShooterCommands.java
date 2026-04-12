package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.team1502.Operator;

public class ShooterCommands extends Command {
    public final ShooterSubsystem m_subsystem;
    public double velocity = 100;
    public ShooterCommands(ShooterSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    
    public void initialize(){
        
        
        Operator.RightBumper
            .onTrue(new InstantCommand(() -> velocity += 10));
        Operator.LeftBumper
            .onTrue(new InstantCommand(() -> velocity -= 10));
        
            
        Operator.A   
            .onTrue(new InstantCommand(() -> m_subsystem.setShooterSpeed(10)));
        // TODO: finesse speed
    }

    @Override
    public void execute(){
        m_subsystem.setShooterVelocity(velocity);
        //We can use this for live stuff
    }
}
