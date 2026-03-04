package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.team1502.Driver;
import frc.robot.team1502.Operator;

public class ShooterCommands extends Command {
    public final ShooterSubsystem m_subsystem;
    public double velocity = 10;
    public ShooterCommands(ShooterSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        
        Driver.A
            .onTrue(new InstantCommand(() -> m_subsystem.setTurretAngle(90)))
            .onFalse(new InstantCommand(() -> m_subsystem.setTurretAngle(0)));
        Operator.X
            .onTrue(new InstantCommand(() -> velocity += 10));
        Operator.Y
            .onTrue(new InstantCommand(() -> velocity -= 10));
        Operator.B
            .onTrue(new InstantCommand(() -> m_subsystem.setShooterVelocity(velocity)));
        Operator.A   
            .onTrue(new InstantCommand(() -> m_subsystem.setShooterSpeed(0)));
        // TODO: finesse speed
    }

    @Override
    public void execute(){
        
        //We can use this for live stuff
    }
}
