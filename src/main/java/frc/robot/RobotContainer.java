// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.team1502.configuration.factory.RobotConfiguration;
import org.team1502.injection.RobotFactory;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autos.Auto1;
import frc.robot.commands.autos.Wheel;
import frc.robot.hardware.Kitbot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autos.Auto1;
import frc.robot.commands.autos.Wheel;
import frc.robot.hardware.Kitbot;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.vision.Vision;
import static frc.robot.subsystems.vision.VisionConstants.camera0Name;
import static frc.robot.subsystems.vision.VisionConstants.camera1Name;
import static frc.robot.subsystems.vision.VisionConstants.robotToCamera0;
import static frc.robot.subsystems.vision.VisionConstants.robotToCamera1;
import frc.robot.subsystems.vision.VisionIO;
import frc.robot.subsystems.vision.VisionIOPhotonVision;
import frc.robot.subsystems.vision.VisionIOPhotonVisionSim;


public class RobotContainer {
    private final Vision vision;
    private final DriveSubsystem drive;
    /** The factory for the robot. Contains subsystems, IO devices, and commands. */
    public static RobotFactory robotFactory;
    /** the structure of the robot */
    public static RobotConfiguration robotConfiguration;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();


    public RobotContainer() {
        robotConfiguration = Kitbot.buildRobot();
        robotFactory = RobotFactory.Create(Robot.class, robotConfiguration);
        drive = robotFactory.getInstance(DriveSubsystem.class);

        switch (Constants.currentMode) {
            case REAL:
                // Real robot, instantiate hardware IO implementations
                vision = new Vision(
                    drive::addVisionMeasurement,
                    // new VisionIOLimelight(camera1Name, drive::getRotation), // UN-COMMENT-OUT when
                    // LimeLight is installed
                    new VisionIOPhotonVision(camera0Name, robotToCamera0));
                break;    
            case SIM:
                // Sim robot, instantiate physics sim IO implementations
                vision = new Vision(
                    drive::addVisionMeasurement,
                    new VisionIOPhotonVisionSim(camera0Name, robotToCamera0, drive::getPose),
                    new VisionIOPhotonVisionSim(camera1Name, robotToCamera1, drive::getPose));
                break;    
            default:
                // Replayed robot, disable IO implementations
                // (Use same number of dummy implementations as the real robot)
                vision = new Vision(drive::addVisionMeasurement, new VisionIO() {}, new VisionIO() {});
                break;
        }

        /*
        @SuppressWarnings("resource")
        PIDController aimController = new PIDController(0.2, 0.0, 0.0);
        aimController.enableContinuousInput(-Math.PI, Math.PI);
        keyboard
            .button(1)
            .whileTrue(
                Commands.startRun(
                    () -> {
                      aimController.reset();
                    },
                    () -> {
                      drive.run(0.0, aimController.calculate(vision.getTargetX(0).getRadians()));
                    },
                    drive));
        */
        
        m_chooser.setDefaultOption("Default", "Auto Right");
        m_chooser.addOption("Right Side", "Auto Right");
        m_chooser.addOption("Left Side", "Auto Left");
        m_chooser.addOption("Front Left", "0");
        m_chooser.addOption("Front Right", "1");
        m_chooser.addOption("Rear Left", "2");
        m_chooser.addOption("Rear Right", "3");
        SmartDashboard.putData("Auto choices", m_chooser);
     
    }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 
  
    public Command getAutonomousCommand() {          
        Command autonomousCommand;
        
        String autoSelected = m_chooser.getSelected();
        switch(autoSelected) {
            case "0": return new Wheel(robotConfiguration, 0, 0.2);
            case "1": return new Wheel(robotConfiguration, 1, 0.2);
            case "2": return new Wheel(robotConfiguration, 2, 0.2);
            case "3": return new Wheel(robotConfiguration, 3, 0.2);
            case "Auto Left":
                autonomousCommand = new Auto1(robotFactory, -1); break;
            case "Auto Right":
            case "Default":
            default: 
                autonomousCommand = new Auto1(robotFactory, 1); break;
        }
        return autonomousCommand;
        //return new ForwardAuto(m_robotDrive); 
        //return new ArmTestAuto(robotFactory); 
        //return new Auto1(robotFactory); 
    }

}
