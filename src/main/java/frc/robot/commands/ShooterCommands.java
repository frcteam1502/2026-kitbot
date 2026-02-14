package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.team1502.Operator;

public class ShooterCommands extends Command {
    private final ShooterSubsystem m_subsystem;
    public ShooterCommands(ShooterSubsystem subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize(){
        

        Operator.RightBumper
            .onTrue(new InstantCommand(() -> m_subsystem.setMotorRightSpeed(0.5)))
            .onFalse(new InstantCommand(() -> m_subsystem.setMotorRightSpeed(0)));
        Operator.LeftBumper
            .onTrue(new InstantCommand(() -> m_subsystem.setMotorLeftSpeed(0.5)))
            .onFalse(new InstantCommand(() -> m_subsystem.setMotorLeftSpeed(0)));
        Operator.A
            .onTrue(new InstantCommand(() -> m_subsystem.setTurretPos(90)))
            .onFalse(new InstantCommand(() -> m_subsystem.setTurretPos(0)));
        // TODO: finesse speed
    }

    @Override
    public void execute(){
        //We can use this for live stuff
    }
}
