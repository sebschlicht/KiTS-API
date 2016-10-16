package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

public class RegisterCommandTest {

    @Test
    public void testValid() throws IOException {
        RegisterCommand cmd = new RegisterCommand();
        String json = Command.serializeCommand(cmd);
        assertNotNull(json);

        Command base = Command.parseString(json);
        assertEquals(cmd, base);
    }
}
