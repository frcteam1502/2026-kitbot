import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import frc.robot.subsystems.LookupTable.LookupTablePoint;
import frc.robot.subsystems.LookupTable;

public class LookUpTableTest {
    LookupTable lookupTable = new LookupTable(List.of(
            new LookupTablePoint(0, 0),
            new LookupTablePoint(1, 1),
            new LookupTablePoint(2, 2),
            new LookupTablePoint(3, 3),
            new LookupTablePoint(4, 4),
            new LookupTablePoint(5, 5),
            new LookupTablePoint(6, 6),
            new LookupTablePoint(7, 7))
        );

    @Test
    public void lookuptest1(){
        System.out.println(lookupTable.interpolate(3.5));
        assertEquals(3.5, lookupTable.interpolate(3.5), 0.01);
    }
}
