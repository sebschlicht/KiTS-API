package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

public class StopCommandTest {

    @Test
    public void testValid() throws IOException {
        StopCommand cmd = new StopCommand();
        String json = Command.serializeCommand(cmd);
        assertNotNull(json);

        Command base = Command.parseString(json);
        assertEquals(cmd, base);
    }
}
