package org.scrumEscape.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JokerLimietTest {

    private boolean magJokerGebruiken(int aantalGebruikt) {
        return aantalGebruikt >= 0 && aantalGebruikt < 2;
    }

    @Test
    void testOnderGrens() {
        assertFalse(magJokerGebruiken(-1));
    }

    @Test
    void testRandOnder() {
        assertTrue(magJokerGebruiken(0));
    }

    @Test
    void testNormaal() {
        assertTrue(magJokerGebruiken(1));
    }

    @Test
    void testRandBoven() {
        assertFalse(magJokerGebruiken(2));
    }

    @Test
    void testBovenGrens() {
        assertFalse(magJokerGebruiken(3));
    }
}
