package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

public class PlayCommandTest {

    @Test
    public void testManualEnumSerialization() throws IOException {
        String json =
                "{\"type\":\"plAy\",\"introType\":\"shoRt\",\"name\":\"Scooby Doo\"}";
        Command base = Command.parseString(json);
        assertNotNull(base);
    }

    @Test
    public void testNameNull() throws IOException {
        // TODO should we really allow name `null`?
        PlayCommand cmd = new PlayCommand(null, IntroType.FULL);
        String json = Command.serializeCommand(cmd);
        assertNotNull(json);

        Command base = Command.parseString(json);
        assertEquals(cmd, base);
    }

    @Test
    public void testIntroTypeFull() throws IOException {
        PlayCommand cmd = new PlayCommand("testName", IntroType.FULL);
        String json = Command.serializeCommand(cmd);
        assertNotNull(json);

        Command base = Command.parseString(json);
        assertEquals(cmd, base);
    }

    @Test
    public void testIntroTypeShort() throws IOException {
        PlayCommand cmd = new PlayCommand("testName", IntroType.SHORT);
        String json = Command.serializeCommand(cmd);
        assertNotNull(json);

        Command base = Command.parseString(json);
        assertEquals(cmd, base);
    }

    @Test
    public void testIntroTypeNull() throws IOException {
        // TODO should we really allow intro type `null`?
        PlayCommand cmd = new PlayCommand("testName", null);
        String json = Command.serializeCommand(cmd);
        assertNotNull(json);

        Command base = Command.parseString(json);
        assertEquals(cmd, base);
    }
}
