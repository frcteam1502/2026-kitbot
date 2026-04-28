package frc.robot.team1502;

import java.util.function.Function;

import org.team1502.configuration.builders.Builder;
import org.team1502.configuration.builders.IBuild;
import org.team1502.configuration.builders.Part;

import edu.wpi.first.units.measure.Voltage;

public class SimpleMotorFeedforwardBuilder extends Builder {
    private static final String NAME = "SimpleMotorFeedforward";
    public static Function<IBuild, SimpleMotorFeedforwardBuilder> Define = b->new SimpleMotorFeedforwardBuilder(b);
    public static SimpleMotorFeedforwardBuilder Wrap(Builder builder) { return builder == null ? null : new SimpleMotorFeedforwardBuilder(builder.getIBuild(), builder.getPart()); }
    public static SimpleMotorFeedforwardBuilder WrapPart(Builder builder) { return WrapPart(builder, NAME); }
    public static SimpleMotorFeedforwardBuilder WrapPart(Builder builder, String partName) { return Wrap(builder.getPart(partName)); }

    public SimpleMotorFeedforwardBuilder(IBuild build) { super(build, NAME); }
    public SimpleMotorFeedforwardBuilder(IBuild build, Part part) { super(build, part); }

    public SimpleMotorFeedforwardBuilder(double ks, double kv, double ka) {
    }

    public double kS() {return getDouble("kS",0); }
    public SimpleMotorFeedforwardBuilder kS(Voltage v) {return (SimpleMotorFeedforwardBuilder)setValue("kS", v.magnitude()); }
    public double kV() {return getDouble("kV", 0); }
    public SimpleMotorFeedforwardBuilder kV(double p) {return (SimpleMotorFeedforwardBuilder)setValue("kV", p); }
    public double kA() {return getDouble("kA", 0); }
    public SimpleMotorFeedforwardBuilder kA(double p) {return (SimpleMotorFeedforwardBuilder)setValue("kA", p); }
   
}
