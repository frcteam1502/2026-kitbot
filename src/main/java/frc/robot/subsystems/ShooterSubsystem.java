package frc.robot.subsystems;
import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ShooterCommands;

@SubsystemInfo(disabled = false)

@DefaultCommand(command = ShooterCommands.class)
public class ShooterSubsystem extends SubsystemBase{
    
    final SparkMax m_shooterRight;
    final SparkMax m_turret;

    public ShooterSubsystem(RobotConfiguration robotConfiguration){       
        m_shooterRight = robotConfiguration.MotorController("Shooter Motor Right").buildSparkMax();
        m_turret = robotConfiguration.MotorController("Turret Motor").buildSparkMax();
    }

   
    @Override
    public void periodic(){        
        SmartDashboard.putNumber("Encoder Velocity", m_shooterRight.getEncoder().getVelocity());
        SmartDashboard.putNumber("Encoder current", m_shooterRight.getOutputCurrent());
        SmartDashboard.putNumber("Motor voltage", m_shooterRight.getBusVoltage() * m_shooterRight.getAppliedOutput());
        SmartDashboard.putNumber("Turret Angle", m_turret.getEncoder().getPosition());
    }

    public void setShooterSpeed(double speed){
        m_shooterRight.set(speed);        
    }

    public void setShooterVelocity(double velocity){        
        m_shooterRight.getClosedLoopController().setSetpoint(velocity, ControlType.kVelocity);        
    }
    public void setTurretAngle(double pos){
        m_turret.getClosedLoopController().setSetpoint(pos*3, ControlType.kPosition);
        // dividing by 3 is the same as 1 full rotation
    }


    //turret
    
}