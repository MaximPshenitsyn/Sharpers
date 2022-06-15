package tests;

import sharpers.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for non-thread
 * functions of the app
 */
public class Tests {
    /**
     * Testing functions of honest player
     */
    @Test
    void honestPlayerTest() {
        HonestPlayer p = new HonestPlayer("79");
        assertEquals(p.getBalance() + p.steal(), 0);
        assertEquals(p.getName(), "79");
    }

    /**
     * Testing functions of sharper
     */
    @Test
    void sharperTest() {
        Sharper sh = new Sharper("57");
        assertEquals(sh.getBalance(), 0);
        assertEquals(sh.getName(), "57");
    }
}
