package frc.robot.hardware;

import org.team1502.configuration.factory.RobotConfiguration;
import org.team1502.drivers.MecanumDriver;
import org.team1502.injection.RobotFactory;

import edu.wpi.first.apriltag.AprilTagFields;

import static edu.wpi.first.units.Units.*;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.team1502.SimpleMotorFeedforwardBuilder;

public class Kitbot {
    public static RobotFactory Create() {
        return Create(buildRobot());
    }
    public static RobotFactory Create(RobotConfiguration config) {
        return RobotFactory.Create(Robot.class, config);
    }
    public static RobotConfiguration testRobot() {
        return RobotConfiguration.Test("1502_KitBot", cfg -> build(cfg));
    }
    public static RobotConfiguration buildRobot() {
        return RobotConfiguration.Create("1502_KitBot", cfg -> build(cfg));
    }

    public static RobotConfiguration build(RobotConfiguration configuration)
    {
        // include these parts
        return Inventory.Parts(configuration,
            Inventory::Motors, 
            Inventory::Sensors,
            Inventory::Kitbot
        )
        
       // return configuration
        // build the robot from parts
        .Build(builder->builder
            // We need to invert one side of the drivetrain so that positive voltages
            // result in both sides moving forward. Depending on how your robot's
            // gearbox is constructed, you might have to invert the left side instead.
            .Subsystem(DriveSubsystem.class, sys->sys
                .Pigeon2(g->g
                    .CanNumber(14))

                .MecanumDrive(m->m
                    .Chassis(c->c.Rectangular(
                        Inches.of(19.5),
                        Inches.of(16))
                    )
                    .MotorController("Front Left", Inventory.Names.Motors.Mecanum, c->c
                        .PDH(18)
                        .CanNumber(3)
                        .Abbreviation("FL"))
                    .MotorController("Front Right", Inventory.Names.Motors.Mecanum, c->c
                        .Reversed()
                        .PDH(1)
                        .CanNumber(4)
                        .Abbreviation("FR"))
                    .MotorController("Rear Left", Inventory.Names.Motors.Mecanum, c->c
                        .PDH(19)
                        .CanNumber(14)
                        .Abbreviation("RL"))
                    .MotorController("Rear Right", Inventory.Names.Motors.Mecanum, c->c
                        .Reversed()
                        .PDH(0)
                        .CanNumber(16)
                        .Abbreviation("RR"))
                    .MotorController("Feeder Motor", Inventory.Names.Motors.FeederMotor, c->c                        
                        .PDH(4)
                        .CanNumber(2)
                        .Abbreviation("FM"))
                    .MotorController("Turret Motor", Inventory.Names.Motors.Turret, c->c
                        .PID(0.13,0.00005,0)
                        .PDH(5)
                        .CanNumber(5)
                        .Abbreviation("TR")
                        
                    )    
                    .MotorController("Shooter Motor Right", Inventory.Names.Motors.ShooterMotor, c->c
                        .Follower(Inventory.Names.Motors.ShooterMotor, f->f
                            .Reversed()
                            .PDH(17)
                            .CanNumber(10)
                            .Abbreviation("SML")
                        )
                        .PID(0.001,0.00001,0)
                        .PDH(3)
                        .CanNumber(9)
                        .Abbreviation("SMR"))
                        
                        
                        
                    // MecanumComtroller Command Information
                    .TrajectoryConfig(MetersPerSecond.of(1.0), MetersPerSecondPerSecond.of(3.0))
                    .PIDController(MecanumDriver.XController, p->p.Gain(0.5, 0.0, 0.0))
                    .PIDController(MecanumDriver.YController, p->p.Gain(0.5, 0.0, 0.0))
                    .PIDController(MecanumDriver.ThetaController, p->p
                        .Gain(0.5, 0.0, 0.0)
                        .Constraints(Math.PI, Math.PI))
                )
                .Part("FF", b->SimpleMotorFeedforwardBuilder.Wrap(b)
                    .kS(Volts.of(1.0))
                    .kV(0.8)
                    .kA(0.15)
                )
            )
                .Subsystem(VisionSubsystem.class, s->s
                    .Vision(v->v
                        .MaxAmbiguity(.25)
                        .MaxZError(Centimeters.of(97.5))
                        .LinearStdDevBaseline(0.2)
                        .AngularStdDevBaseline(6)
                        .LinearStdDevMegatag2Factor(0.5) // More stable than full 3D solve
                        .AngularStdDevMegatag2Factor(Double.POSITIVE_INFINITY) // No rotation data available
                        .AprilTagLayout(AprilTagFields.kDefaultField)

                        .PhotonVision("Camera 1", p->p
                            .X(Inches.of(1.0))
                            .Y(Inches.of(1.0))
                            .Z(Inches.of(1.0))  
                            .Roll(Degrees.of(0.0))
                            .Pitch(Degrees.of(0.0))
                            .Yaw(Degrees.of(0.0))
                            .StdDevFactor(1.0)
                        )
                        .PhotonVision("Camera 2", p->p
                            .X(Inches.of(1.0))
                            .Y(Inches.of(-1.0))
                            .Z(Inches.of(1.0))  
                            .Roll(Degrees.of(0.0))
                            .Pitch(Degrees.of(0.0))
                            .Yaw(Degrees.of(0.0))
                            .StdDevFactor(1.0)
                        )
                    )
                )
        );
    }

}
