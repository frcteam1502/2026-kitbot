package frc.robot.hardware;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.MetersPerSecondPerSecond;

import org.team1502.configuration.factory.RobotConfiguration;
import org.team1502.injection.RobotFactory;
import org.team1502.drivers.MecanumDriver;

import frc.robot.Robot;

import frc.robot.subsystems.DriveSubsystem;

public class Kitbot {
    public static RobotFactory Create() {
        return Create(buildRobot());
    }
    public static RobotFactory Create(RobotConfiguration config) {
        return RobotFactory.Create(Robot.class, config);
    }

    @SuppressWarnings("unchecked")
    public static RobotConfiguration buildRobot() {
        return RobotConfiguration.Create("1502_KitBot", fn->
        // include these parts
        Inventory.Parts(fn,
            Inventory::Motors, 
             Inventory::Sensors,
             Inventory::Kitbot
            )

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
                        // Gap + Frame + Gap + Wheel
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
                        .CanNumber(5)
                        .Abbreviation("FR"))
                    .MotorController("Rear Left", Inventory.Names.Motors.Mecanum, c->c
                        .PDH(19)
                        .CanNumber(4)
                        .Abbreviation("RL"))
                    .MotorController("Rear Right", Inventory.Names.Motors.Mecanum, c->c
                        .Reversed()
                        .PDH(0)
                        .CanNumber(14)
                        .Abbreviation("RR"))
                    .MotorController("Shooter Motor Left", Inventory.Names.Motors.ShooterMotor, c->c
                        .PDH(10)
                        .CanNumber(10)
                        .Abbreviation("SML"))
                    .MotorController("Shooter Motor Right", Inventory.Names.Motors.ShooterMotor, c->c
                        .PDH(9)
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
            )
        ));
            
            
            // 14 stage 1
            //  3 intake arm
            // 16 algae intake wheels
            // 15 algae intake lift

        
    }

}
