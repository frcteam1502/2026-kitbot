// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import org.team1502.configuration.annotations.DefaultCommand;
import org.team1502.configuration.annotations.SubsystemInfo;
import org.team1502.configuration.factory.RobotConfiguration;
import org.team1502.drivers.MecanumDriver;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.estimator.MecanumDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveInstruction;
import frc.robot.commands.DriverCommands;
import frc.robot.team1502.GyroIO;
import frc.robot.team1502.GyroIOPigeon2;

@SubsystemInfo(disabled = false)
@DefaultCommand(command = DriverCommands.class)
public class DriveSubsystem extends SubsystemBase {
    
    public static class DriveConstants {
      public static final double odometryFrequency = 100.0; // Hz
    
    }
    


    final Pigeon2 m_gyro;
    /** generic Angle in degrees CW */
    public /* final */ Supplier<Angle> m_gyroYaw;
    /** generic Rotation2d in radians */
    public /* final */ Supplier<Rotation2d> m_gyroRotation2d;
    public boolean drive_type = false; 
    final MecanumDriver m_drive;
    final RobotConfiguration m_robotConfiguration;

    public static final Lock odometryLock = new ReentrantLock();
    private final GyroIO gyroIO;
    //private final GyroIOInputsAutoLogged gyroInputs = new GyroIOInputsAutoLogged();
    private final MecanumDrivePoseEstimator poseEstimator;
    //private final Module[] modules = new Module[4]; // FL, FR, BL, BR
    //private final SysIdRoutine sysId;
    private final Alert gyroDisconnectedAlert = new Alert("Disconnected gyro, using kinematics as fallback.", AlertType.kError);

    /** Creates a new DriveSubsystem. */
    public DriveSubsystem(RobotConfiguration robotConfiguration) {
        m_robotConfiguration = robotConfiguration;
        
        // set up gyro and angle suppliers
        m_gyro = robotConfiguration.Pigeon2().buildPigeon2();
        gyroIO = new GyroIOPigeon2(m_gyro);
        m_gyroYaw = m_gyro.getYaw().asSupplier();
        m_gyroRotation2d = ()->new Rotation2d(m_gyroYaw.get().times(-1.0));

        MecanumDriveWheelPositions wheelPositions = new MecanumDriveWheelPositions();
        var kinematics = robotConfiguration.MecanumDrive().Chassis().getMecanumDriveKinematics();
        // TODO: update MecanumDrive to incorporate this poseEstimator
        poseEstimator = new MecanumDrivePoseEstimator(kinematics,  Rotation2d.kZero, wheelPositions, Pose2d.kZero);

        
        zeroHeading(); // whichever way we are pointing is 0 (+X direction)
        
        m_drive = robotConfiguration.MecanumDrive().buildDriver(m_gyroRotation2d);

    }
    int cycle = 0;
    DriveInstruction m_instruction;
    @Override
    public void periodic() {
        m_drive.update(); // Update the mecanum driver in the periodic block
        SmartDashboard.putNumber("cycle", cycle++);

        if (m_instruction != null) {
            SmartDashboard.putNumber("x-speed", m_instruction.x_speed());
            SmartDashboard.putNumber("y-speed", m_instruction.y_speed());
            SmartDashboard.putNumber("t-rot", m_instruction.t_rotation());
        }

    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    public Pose2d getPose() {
        return m_drive.getPose();
    }
    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    public void resetOdometry(Pose2d pose) {
        m_drive.resetOdometry(pose);
    }

    public void resetOdometry() {
        resetOdometry(new Pose2d());
        resetEncoders();
    }
    public void toggleFieldRelative(){
        drive_type = !drive_type;
    }
    /**
     * Drives the robot at given x, y and theta speeds. Speeds range from [-1, 1]
     * and the linear speeds have no effect on the angular speed.
     *
     * @param xSpeed        Speed of the robot in the x direction (forward/backwards).
     * @param ySpeed        Speed of the robot in the y direction (sideways).
     * @param rot
     * @param fieldRelative           Angular rate of the robot (theta). Clockwise!
     * Whether the provided x and y speeds are relative to the field.
     */
    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        m_drive.drive(xSpeed, ySpeed, rot, drive_type);
    }
    
    public void drive(DriveInstruction instruction) {
        m_instruction = instruction;
        drive(
            instruction.x_speed(),
            instruction.y_speed(),
            instruction.t_rotation(),
            instruction.field()
        );
    }
    public TrajectoryConfig getTrajectoryConfig() {
        return m_drive.getTrajectoryConfig();  
    }

    public MecanumControllerCommand buildMecanumControllerCommand(Trajectory trajectory) {
        return m_drive.buildMecanumControllerCommand(trajectory, this);        
    }

    /**
     * Sets the max output of the drive. Useful for scaling the drive to drive more
     * slowly.
     *
     * @param maxOutput the maximum output to which the drive will be constrained
     */
    public void setMaxOutput(double maxOutput) {
        m_drive.setMaxOutput(maxOutput);
    }

    public void initialize(){
        resetOdometry();
    }

    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        m_drive.resetEncoders();
    }

    /** Zeroes the heading of the robot. */
    public void zeroHeading() {
        m_gyro.reset();
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from -180 to 180
     */
    public double getHeading() {
        return m_gyro.getRotation2d().getDegrees();
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return m_gyro.getAngularVelocityZWorld().getValue().in(DegreesPerSecond);
    }

    public void stop() {
        m_instruction = new DriveInstruction(0,0,0, false, 0);
        drive(0,0,0,false);
    }

      /** Adds a new timestamped vision measurement. */
    public void addVisionMeasurement(Pose2d visionRobotPoseMeters, double timestampSeconds, Matrix<N3, N1> visionMeasurementStdDevs) {
        poseEstimator.addVisionMeasurement(visionRobotPoseMeters, timestampSeconds, visionMeasurementStdDevs);
    }

}
