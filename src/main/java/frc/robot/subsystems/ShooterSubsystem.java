package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;
import org.team1502.game.GameState;
import frc.robot.commands.ShooterCommands;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SubsystemInfo(disabled = false)

@DefaultCommand(command = ShooterCommands.class)
public class ShooterSubsystem extends SubsystemBase{
    
    final SparkMax m_shooterRight;
    
    
    public ShooterSubsystem(RobotConfiguration robotConfiguration){
        
        m_shooterRight = robotConfiguration.MotorController("Shooter Motor Right").buildSparkMax();
           }
    

    @Override
    public void periodic(){
        
        SmartDashboard.putNumber("Encoder Velocity", m_shooterRight.getEncoder().getVelocity());
        SmartDashboard.putNumber("Encoder current", m_shooterRight.getOutputCurrent());
        SmartDashboard.updateValues();
    }

    
    //Shooter
    public void setShooterMotorLeftSpeed(double speed){
        //reversed
       
    }
    public void setShooterMotorRightSpeed(double speed){
        m_shooterRight.set(speed);
    }

    //Do not run setShooterSpeed until tested that motors run same directions
    public void setShooterSpeed(double speed){
        //Shooter Left reversed
        
        m_shooterRight.set(speed);
        
    }
    public void setShooterVelocity(double velocity){
        
        
        m_shooterRight.getClosedLoopController().setSetpoint(velocity, ControlType.kVelocity);
        
    }

    //turret
    

}