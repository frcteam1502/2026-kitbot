package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.team1502.Driver;
import frc.robot.team1502.Operator;

public class ShooterCommands extends Command {
    public final ShooterSubsystem m_subsystem;
    public ShooterCommands(ShooterSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        

        Operator.A
            .onTrue(new InstantCommand(() -> m_subsystem.setShooterSpeed(0.5)))
            .onFalse(new InstantCommand(() -> m_subsystem.setShooterSpeed(0)));
        
        // TODO: finesse speed
    }

    @Override
    public void execute(){
        SmartDashboard.putBoolean("operater A", Operator.A.getAsBoolean());
        SmartDashboard.updateValues();
        
        //We can use this for live stuff
    }
}
