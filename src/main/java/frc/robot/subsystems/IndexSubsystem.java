import java.util.function.Supplier;

import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;
import org.team1502.drivers.MecanumDriver;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveInstruction;

public class IndexSubsystem {
    public ShooterSubsystem(RobotConfiguration robotConfiguration){       
        m_feederMotor = robotConfiguration.MotorController("Feeder Motor").buildSparkMax();
    }

    public void setFeederPower(double power){
        m_feederMotor.setShooterSpeed(power);
        //add the grumble mecanum wheels if neccasary 
    }
}