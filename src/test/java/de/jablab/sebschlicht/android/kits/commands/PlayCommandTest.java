package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PlayCommandTest {

    @Test
    public void testNameNull() {
        // TODO should we really allow name `null`?
        PlayCommand cmd = new PlayCommand(null, IntroType.FULL);
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }

    @Test
    public void testIntroTypeFull() {
        PlayCommand cmd = new PlayCommand("testName", IntroType.FULL);
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }

    @Test
    public void testIntroTypeShort() {
        PlayCommand cmd = new PlayCommand("testName", IntroType.SHORT);
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }

    @Test
    public void testIntroTypeNull() {
        // TODO should we really allow intro type `null`?
        PlayCommand cmd = new PlayCommand("testName", null);
        String json = Command.serializeCommandQuietly(cmd);
        assertNotNull(json);

        Command base = Command.parseStringQuietly(json);
        assertEquals(cmd, base);
    }
}
