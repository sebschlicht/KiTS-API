package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

public class SetVolumeCommandTest {

    @Test
    public void testVolumeValid() throws IOException {
        SetVolumeCommand cmd = new SetVolumeCommand(100);
        String json = Command.serializeCommand(cmd);
        assertNotNull(json);

        Command base = Command.parseString(json);
        assertEquals(cmd, base);
    }

    //@Test
    public void testVolumeMissing() throws IOException {
        // TODO test what happens if JSON is missing the volume
    }

    //@Test
    public void testVolumeInvalid() throws IOException {
        // TODO test what happens if constructor refuses volume
    }
}
