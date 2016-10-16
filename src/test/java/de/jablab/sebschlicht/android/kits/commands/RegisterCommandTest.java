package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RegisterCommandTest {

    @Test
    public void testValid() {
        RegisterCommand cmd = new RegisterCommand();
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }
}
