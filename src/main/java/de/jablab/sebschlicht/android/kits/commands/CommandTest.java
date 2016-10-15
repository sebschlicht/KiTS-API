package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CommandTest {

    @Test
    public void testSerializeNull() {
        String json = Command.serializeCommand(null);
        assertEquals(null + "", json);
    }

    @Test
    public void testParseNull() {
        Command command = Command.parseString(null);
        assertNull(command);
    }

    @Test
    public void testParseEmpty() {
        Command command = Command.parseString("");
        assertNull(command);
    }

    @Test
    public void testParseMalformedJson() {
        String malformedJson = "{\"type\":\"\"";
        Command command = Command.parseString(malformedJson);
        assertNull(command);
    }

    @Test
    public void testParseIllegalJson() {
        String illegalJson = "{\"tpe\":\"PLAY\"}";
        Command command = Command.parseString(illegalJson);
        assertNull(command);
    }
}
