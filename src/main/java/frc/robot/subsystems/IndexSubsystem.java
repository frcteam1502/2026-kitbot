package frc.robot.subsystems;
import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.IndexCommands;
@SubsystemInfo(disabled = false)

@DefaultCommand(command = IndexCommands.class)
public class IndexSubsystem extends SubsystemBase {
    final SparkMax m_feederMotor;
    final SparkMax m_indexMotor;
    public IndexSubsystem(RobotConfiguration robotConfiguration){       
        m_feederMotor = robotConfiguration.MotorController("Feeder Motor").buildSparkMax();
        m_indexMotor = robotConfiguration.MotorController("Index Motor").buildSparkMax();
    }

    public void setFeederPower(double power){
        m_feederMotor.set(power);
        //add the grumble mecanum wheels if neccasary 
    }
    public void setIndexPower(double power){
        m_indexMotor.set(power);
    }
    
}