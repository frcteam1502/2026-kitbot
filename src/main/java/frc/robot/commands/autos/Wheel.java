package frc.robot.commands.autos;

import org.team1502.configuration.factory.RobotConfiguration;
import org.team1502.injection.RobotFactory;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class Wheel extends Command {
    Command m_command;
    SparkMax[] m_modules;
    SparkMax m_module;
    double m_speed;
    public Wheel(RobotConfiguration robotConfiguration, int motorNumber, double speed) {
        var builder = robotConfiguration.MecanumDrive().getModules().get(motorNumber);
        SmartDashboard.putString("SELECTED MOTOR", builder.Name());
        SmartDashboard.putNumber("SELECTED CAN", builder.CanNumber());
        
        m_modules = robotConfiguration.MecanumDrive().getModules()
            .stream()
            .map(mcb->(SparkMax)mcb.CANSparkMax())
            .toArray(SparkMax[]::new);
        m_module = m_modules[motorNumber];
        m_speed = speed;
    }

    @Override
    public void initialize(){
        SmartDashboard.putString("Wheel", "init");
    }

    @Override
    public void execute(){
        m_module.set(m_speed);
    }
    
    @Override 
    public void end(boolean interrupted) {
        m_speed = 0;
        m_module.set(m_speed);
    }

}
/*
 *8:08:32.914 PM
 	
 at frc.robot.RobotContainer.<init>(RobotContainer.java:38)  	
    at org.team1502.injection.RobotFactory.Create(RobotFactory.java:37)  	
    at org.team1502.injection.RobotFactory.start(RobotFactory.java:61)  	
    at org.team1502.injection.RobotFactory.build(RobotFactory.java:134)  	
    at org.team1502.injection.RobotFactory.buildParts(RobotFactory.java:141)  	
    at org.team1502.injection.RobotFactory.buildPart(RobotFactory.java:168) ERROR  1  The robot program quit unexpectedly. This is usually due to a code error.
  The above stacktrace can help determine where the error occurred.
  See https://wpilib.org/stacktrace for more information.
  edu.wpi.first.wpilibj.RobotBase.runRobot(RobotBase.java:383)  Error 
  at org.team1502.injection.RobotFactory.buildPart(RobotFactory.java:168): 
    Unhandled exception instantiating robot org.team1502.injection.RobotFactory java.lang.NullPointerException: 
    Cannot invoke "edu.wpi.first.wpilibj2.command.Subsystem.setDefaultCommand(edu.wpi.first.wpilibj2.command.Command)" because "subsystem" is null
     ERROR  1  Unhandled exception instantiating robot org.team1502.injection.RobotFactory java.lang.NullPointerException:
      Cannot invoke "edu.wpi.first.wpilibj2.command.Subsystem.setDefaultCommand(edu.wpi.first.wpilibj2.command.Command)" because
       "subsystem" is null  org.team1502.injection.RobotFactory.buildPart(RobotFactory.java:168)  	... 16 more  	
  at frc.robot.subsystems.DriveSubsystem.<init>(DriveSubsystem.java:50)  	
  at org.team1502.configuration.builders.drives.MecanumDriveBuilder.buildDriver(MecanumDriveBuilder.java:42)  	
  at org.team1502.drivers.MecanumDriver.<init>(MecanumDriver.java:40)  	
  at org.team1502.drivers.MecanumDriver$Modules.buildMotorControllers(MecanumDriver.java:152)  	
  at org.team1502.configuration.builders.motors.MotorControllerBuilder.buildSparkMax(MotorControllerBuilder.java:154) ERROR  7  [CAN SPARK] IDs: 3, The SPARK firmware version is too new for this version of REVLib. Refer to www.RevRobotics.com/REVLib for details.     	
  at com.revrobotics.spark.SparkMax.<init>(SparkMax.java:73)  	
  at com.revrobotics.spark.SparkBase.<init>(SparkBase.java:168)  	
  at com.revrobotics.spark.SparkLowLevel.<init>(SparkLowLevel.java:163)  	
  at com.revrobotics.jni.CANSparkJNI.c_Spark_Create(Native Method)  Caused by: java.lang.IllegalStateException: The firmware version of SPARK #3 is too new for this version of REVLib  	
  at frc.robot.Main.main(Main.java:23)  	
  at edu.wpi.first.wpilibj.RobotBase.startRobot(RobotBase.java:510)  	
  at edu.wpi.first.wpilibj.RobotBase.runRobot(RobotBase.java:370)  	
  at frc.robot.Robot.<init>(Robot.java:33)  	
  at frc.robot.RobotContainer.<init>(RobotContainer.java:38)  	
  at org.team1502.injection.RobotFactory.Create(RobotFactory.java:37)  	
  at org.team1502.injection.RobotFactory.start(RobotFactory.java:61)  	
  at org.team1502.injection.RobotFactory.build(RobotFactory.java:134)  	
  at org.team1502.injection.RobotFactory.buildParts(RobotFactory.java:141)  	
  at org.team1502.injection.RobotFactory.buildPart(RobotFactory.java:164)  	
  at org.team1502.injection.RobotPart.Build(RobotPart.java:97)  	
  at java.base/java.lang.reflect.Constructor.newInstance(Unknown Source)  	
  at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Unknown Source)  	
  at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)  	
  at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)  	
  at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)  java.lang.reflect.InvocationTargetException 

 */