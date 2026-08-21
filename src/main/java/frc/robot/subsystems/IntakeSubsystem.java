package frc.robot.subsystems;
import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.IntakeCommands;

@SubsystemInfo(disabled = false)

@DefaultCommand(command = IntakeCommands.class)
public class IntakeSubsystem extends SubsystemBase{
    final SparkMax m_intakeMotor;
    final SparkMax m_intakeRotatorMotor;
    Boolean intakeup = true;
    public IntakeSubsystem(RobotConfiguration robotConfiguration){       
        m_intakeMotor = robotConfiguration.MotorController("Intake Motor").buildSparkMax();
        m_intakeRotatorMotor = robotConfiguration.MotorController("Intake Motor Rotator").buildSparkMax();
    }
    public void setIntakePower(double speed){
        m_intakeMotor.set(speed);
    }
    public void setIntakeLocation(){
        if (intakeup == true){
            
            m_intakeRotatorMotor.getClosedLoopController().setSetpoint(-1.3, ControlType.kPosition);
            intakeup = false;
        }
        if(intakeup == false) {
            
            m_intakeRotatorMotor.getClosedLoopController().setSetpoint(-0.1, ControlType.kPosition);
            intakeup = true;
        }
        

    }
    public double getPos(){
        return m_intakeRotatorMotor.getEncoder().getPosition();//getClosedLoopController().getSetpoint();
    }

    public Command runArmCommand(){
        return this.runOnce(
        () -> {this.m_intakeRotatorMotor.getClosedLoopController().setSetpoint(-1.3, ControlType.kPosition); 
               intakeup = false;});
    }

}
