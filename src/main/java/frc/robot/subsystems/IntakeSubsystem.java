package frc.robot.subsystems;
import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.IntakeCommands;
@SubsystemInfo(disabled = false)

@DefaultCommand(command = IntakeCommands.class)
public class IntakeSubsystem extends SubsystemBase{
    final SparkMax m_intakeMotor;
    final SparkMax m_intakeRotatorMotor;
    public IntakeSubsystem(RobotConfiguration robotConfiguration){       
        m_intakeMotor = robotConfiguration.MotorController("Intake Motor").buildSparkMax();
        m_intakeRotatorMotor = robotConfiguration.MotorController("Intake Rotator Motor").buildSparkMax();
    }
    public void setIntakePower(double speed){
        m_intakeMotor.set(speed);
    }
}
