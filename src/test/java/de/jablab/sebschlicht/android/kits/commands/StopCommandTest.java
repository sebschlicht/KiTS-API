package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class StopCommandTest {

    @Test
    public void testValid() {
        StopCommand cmd = new StopCommand();
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }
}
