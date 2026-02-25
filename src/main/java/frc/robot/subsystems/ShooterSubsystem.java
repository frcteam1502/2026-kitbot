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

    public final class LookupTable{
        
        public double[] inputs;
        public double[] outputs;

        public static final class Pair{

            double m_distance; // feet
            double m_velocity; //rpm

            public Pair(double feet, double revolutionsPerMinute){
                this.m_distance = feet;
                this.m_velocity = revolutionsPerMinute;
            }
            
        }

        public static final List<Pair> LOOKUP_TABLE = List.of(
            //dummy points

            new Pair(0, 0),
            new Pair(1, 1)
            
            //must be ordered least to greatest
            //must have more than 2
            //must not repeat x values, aka distance
            );
            
            /**make inputs and outputs to interpolate between */
        public LookupTable(){
            
            //(untested)
            inputs = new double[LOOKUP_TABLE.size()];
            outputs = new double[LOOKUP_TABLE.size()];

            for (int i = 0; i < LOOKUP_TABLE.size(); i++){

                double x = LOOKUP_TABLE.get(i).m_distance;
                double y = LOOKUP_TABLE.get(i).m_velocity;

                inputs[i] = x;
                outputs[i] = y;
            }
        }
        public double interpolate(double distance){
            
            double x_greater;
            double y_greater;

            double x_less;
            double y_less;

            double m;
            double b;

            double velocity = 0;

            for(int i = 0; i >= LOOKUP_TABLE.size(); i++){
                
                if (inputs[i] > distance && i !=0){
                    x_greater = inputs[i];
                    y_greater = outputs[i];
                    
                    x_less = inputs[i-1];
                    y_less = outputs[i-1];

                    m = (y_greater-y_less)/(x_greater-x_less);
                    velocity = distance * m;

                

                }
            }

            return velocity;
            
        }
    }

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