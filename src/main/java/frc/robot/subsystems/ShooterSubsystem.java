package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;
import org.team1502.game.GameState;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ShooterSubsystem extends SubsystemBase{
    final SparkMax m_shooterLeft;
    final SparkMax m_shooterRight;

    public ShooterSubsystem(RobotConfiguration robotConfiguration){
        m_shooterLeft = robotConfiguration.MotorController("Shooter Motor Left").buildSparkMax();
        m_shooterRight = robotConfiguration.MotorController("Shooter Motor Right").buildSparkMax();
    }

    
    public void init(){
        //Add things if we need it later
    }

    public void setMotorLeftSpeed(double speed){
        m_shooterLeft.set(speed);
    }
    public void setMotorRightSpeed(double speed){
        m_shooterRight.set(speed);
    }
    
    //Do not run setShooterSpeed until tested that motors run same directions
    public void setShooterSpeed(double speed){
        m_shooterLeft.set(speed);
        m_shooterRight.set(speed);
    }

}