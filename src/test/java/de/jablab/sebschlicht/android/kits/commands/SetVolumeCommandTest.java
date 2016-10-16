package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SetVolumeCommandTest {

    @Test
    public void testValidVolume() {
        SetVolumeCommand cmd = new SetVolumeCommand(100);
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }

    @Test
    public void testVolumeZero() {
        SetVolumeCommand cmd = new SetVolumeCommand(0);
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }

    @Test
    public void testVolumeNegative() {
        // TODO should we really allow negative volume?
        SetVolumeCommand cmd = new SetVolumeCommand(-2);
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }

    @Test
    public void testVolumeTooHigh() {
        // TODO should we really allow too high volume?
        SetVolumeCommand cmd = new SetVolumeCommand(1000);
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }
}
