package frc.robot.subsystems;
import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.IntakeCommands;
@SubsystemInfo(disabled = false)

@DefaultCommand(command = IntakeCommands.class)
public class IntakeSubsystem extends SubsystemBase{
    final SparkMax m_intakeMotor;
    final SparkMax m_intakeRotatorMotor;
    String intakepos = "down";
    public IntakeSubsystem(RobotConfiguration robotConfiguration){       
        m_intakeMotor = robotConfiguration.MotorController("Intake Motor").buildSparkMax();
        m_intakeRotatorMotor = robotConfiguration.MotorController("Intake Motor Rotator").buildSparkMax();
    }
    public void setIntakePower(double speed){
        m_intakeMotor.set(speed);
    }
    public void setIntakeLocation(){
        if (intakepos == "up"){
            m_intakeRotatorMotor.getClosedLoopController().setSetpoint(0.1, ControlType.kPosition);
            intakepos = "down";
        }
        else if(intakepos == "down") {
            m_intakeRotatorMotor.getClosedLoopController().setSetpoint(0, ControlType.kPosition);
            intakepos = ("up");
        }
        

    }

}
