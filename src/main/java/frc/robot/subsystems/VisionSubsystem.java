package frc.robot.subsystems;

import org.team1502.configuration.builders.vision.VisionBuilder.VisionConsumer;
import org.team1502.configuration.builders.vision.VisionSubsystemBase;
import org.team1502.configuration.factory.RobotConfiguration;

public class VisionSubsystem extends VisionSubsystemBase{
    public VisionSubsystem(VisionConsumer visionConsumer, RobotConfiguration robotConfiguration){
        super(visionConsumer, robotConfiguration);
    }
}
