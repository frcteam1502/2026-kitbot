package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.team1502.Driver;
import frc.robot.team1502.Operator;

public class IndexCommands extends Command {
    public final IndexSubsystem m_subsystem;
    public IndexCommands(IndexSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        Operator.A   
            .onTrue(new InstantCommand(() -> m_subsystem.setFeederPower(0.4)))
            .onFalse(new Instantcommand(() -> m_subsystem.setFeederPower(0)));
    }

    @Override
    public void setSmartDashboard(){
        SmartDashboard.putBoolean("operater A", Operator.A.getAsBoolean());
        //We can use this for live stuff
    }
}
