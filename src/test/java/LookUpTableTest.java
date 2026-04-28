import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import frc.robot.subsystems.LookupTable;
import frc.robot.subsystems.LookupTable.LookupTablePoint;

public class LookUpTableTest {
    @Test
    public void LookupTest00() {
        var l0 = LookupTable.Lookup(0);
    }

    @Test
    public void lookuptest1(){
        LookupTable lookupTable = new LookupTable(List.of(
                new LookupTablePoint(0, 0),
                new LookupTablePoint(1, 1),
                new LookupTablePoint(2, 2),
                new LookupTablePoint(3, 8),
                new LookupTablePoint(4, 16),
                new LookupTablePoint(5, 32),
                new LookupTablePoint(6, 33),
                new LookupTablePoint(7, 33 + 24.0/7.0))
            );
        
        System.out.println(lookupTable.interpolate(3.5));
        assertEquals(12, lookupTable.interpolate(3.5), 0.01);
    }

    @Test
    public void lookuptest2(){
            
        LookupTable lookupTable = new LookupTable(List.of(
                new LookupTablePoint(0, 5),
                new LookupTablePoint(1, 2),
                new LookupTablePoint(2, 4),
                new LookupTablePoint(3, 8),
                new LookupTablePoint(4, 16),
                new LookupTablePoint(5, 32),
                new LookupTablePoint(6, 64),
                new LookupTablePoint(7, 128))
            );
        System.out.println(lookupTable.interpolate(3.5));
        //assertEquals(12, lookupTable.interpolate(3.5), 0.01);
    }
}
