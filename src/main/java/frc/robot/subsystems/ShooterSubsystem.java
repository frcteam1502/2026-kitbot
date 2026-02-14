package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;
import org.team1502.game.GameState;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ShooterSubsystem extends SubsystemBase{
    final SparkMax m_shooterLeft;
    final SparkMax m_shooterRight;
    final SparkMax m_turret;

    public ShooterSubsystem(RobotConfiguration robotConfiguration){
        m_shooterLeft = robotConfiguration.MotorController("Shooter Motor Left").buildSparkMax();
        m_shooterRight = robotConfiguration.MotorController("Shooter Motor Right").buildSparkMax();
        m_turret = robotConfiguration.MotorController("Turret").buildSparkMax();
    }
    @Override
    public void periodic(){
        SmartDashboard.putNumber("Turret Pos", m_turret.getEncoder().getPosition());
    }

    public void init(){
        //Add things if we need it later
    }

    
    //Shooter
    public void setShooterMotorLeftSpeed(double speed){
        //reversed
        m_shooterLeft.set(-speed);
    }
    public void setShooterMotorRightSpeed(double speed){
        m_shooterRight.set(speed);
    }

    //Do not run setShooterSpeed until tested that motors run same directions
    public void setShooterSpeed(double speed){
        //Shooter Left reversed
        m_shooterLeft.set(-speed);
        m_shooterRight.set(speed);
    }

    //turret
    public void setTurretPos(double pos){
        var pid = m_turret.getClosedLoopController();
        pid.setSetpoint(pos, ControlType.kPosition);
        //If we change this ControlType to .kVelocity we can do velocity control.
    }

}