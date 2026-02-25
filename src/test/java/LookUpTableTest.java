import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import frc.robot.subsystems.LookupTable;
import frc.robot.subsystems.LookupTable.LookupTablePoint;

public class LookUpTableTest {
    @Test
    public void lookuptest1(){
        LookupTable lookupTable = new LookupTable(List.of(
                new LookupTablePoint(0, 0),
                new LookupTablePoint(1, 1),
                new LookupTablePoint(2, 2),
                new LookupTablePoint(3, 8),
                new LookupTablePoint(4, 16),
                new LookupTablePoint(5, 32),
                new LookupTablePoint(6, 6),
                new LookupTablePoint(7, 7))
            );
        
        System.out.println(lookupTable.interpolate(3.5));
            assertEquals(3.5, lookupTable.interpolate(3.5), 0.01);
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
        assertEquals(3.5, lookupTable.interpolate(3.5), 0.01);
    }
}
