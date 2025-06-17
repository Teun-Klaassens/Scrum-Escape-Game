
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

class Kamer6ToegangTest {

    private boolean isKamer6Toegankelijk(Set<Integer> afgerondeKamers) {
        return afgerondeKamers.containsAll(Set.of(1, 2, 3, 4, 5));
    }

    @Test
    void testOnderGrens() {
        Set<Integer> afgerond = Set.of(1, 2, 3, 4);
        assertFalse(isKamer6Toegankelijk(afgerond));
    }

    @Test
    void testRandOnder() {
        Set<Integer> afgerond = Set.of(1, 2, 3, 4, 5);
        assertTrue(isKamer6Toegankelijk(afgerond));
    }

    @Test
    void testRandBoven() {
        Set<Integer> afgerond = Set.of(1, 2, 3, 4, 5, 6);
        assertTrue(isKamer6Toegankelijk(afgerond));
    }

    @Test
    void testBovenGrens() {
        Set<Integer> afgerond = Set.of(1, 2, 3, 4, 5, 6, 7);
        assertTrue(isKamer6Toegankelijk(afgerond));
    }

    @Test
    void testLegeSet() {
        Set<Integer> afgerond = Set.of();
        assertFalse(isKamer6Toegankelijk(afgerond));
    }
}
