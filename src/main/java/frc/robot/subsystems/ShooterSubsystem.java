package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;
import frc.robot.commands.ShooterCommands;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.units.Unit;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.VelocityUnit;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.List;

@SubsystemInfo(disabled = false)

@DefaultCommand(command = ShooterCommands.class)
public class ShooterSubsystem extends SubsystemBase{
    
    final SparkMax m_shooterRight;
    public static final class Pair{
        Distance m_distance; // feet
        AngularVelocity m_velocity; //rpm
        public Pair(double feet, double RevolutionsPerMinute){
            m_distance = Distance.ofBaseUnits(feet, Units.Feet);
            m_velocity = AngularVelocity.ofBaseUnits(RevolutionsPerMinute, Units.RPM);
        }
    }
    public static final List<Pair> LOOKUP_TABLE = List.of(
       new Pair(0, 0)
       //add more pairs that we have tested for
    );
    public ShooterSubsystem(RobotConfiguration robotConfiguration){       
        m_shooterRight = robotConfiguration.MotorController("Shooter Motor Right").buildSparkMax();
    }
    

    @Override
    public void periodic(){        
        SmartDashboard.putNumber("Encoder Velocity", m_shooterRight.getEncoder().getVelocity());
        SmartDashboard.putNumber("Encoder current", m_shooterRight.getOutputCurrent());
        SmartDashboard.putNumber("Motor voltage", m_shooterRight.getBusVoltage() * m_shooterRight.getAppliedOutput());
    }

    public void setShooterSpeed(double speed){
        m_shooterRight.set(speed);        
    }

    public void setShooterVelocity(double velocity){        
        m_shooterRight.getClosedLoopController().setSetpoint(velocity, ControlType.kVelocity);        
    }

    //turret
    
}