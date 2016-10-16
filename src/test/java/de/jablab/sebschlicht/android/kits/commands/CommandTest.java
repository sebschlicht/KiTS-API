package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class CommandTest {

    @Test
    public void testEquals() {
        // test basic equals cases
        Command cmdPlay1 = new Command(CommandType.PLAY) {
        };
        assertFalse(cmdPlay1.equals(null));
        assertTrue(cmdPlay1.equals(cmdPlay1));
        assertFalse(cmdPlay1.equals(""));

        // test commands not equal if type not equals
        Command cmdStop = new Command(CommandType.STOP) {
        };
        assertFalse(cmdPlay1.equals(cmdStop));

        // test commands equal if type equals
        Command cmdPlay2 = new Command(CommandType.PLAY) {
        };
        assertTrue(cmdPlay1.equals(cmdPlay2));
    }

    @Test
    public void testSerializeNull() throws JsonProcessingException {
        assertEquals("null", Command.serializeCommandQuietly(null));
        assertEquals("null", Command.serializeCommand(null));
    }

    @Test
    public void testParseNull() throws IOException {
        assertNull(Command.parseStringQuietly(null));
        assertNull(Command.parseString(null));
    }

    @Test(
            expected = JsonMappingException.class)
    public void testParseEmpty() throws IOException {
        assertNull(Command.parseStringQuietly(""));
        Command.parseString("");
    }

    @Test(
            expected = JsonParseException.class)
    public void testParseMalformedJson() throws IOException {
        String malformedJson = "{\"type\":\"\"";
        assertNull(Command.parseStringQuietly(malformedJson));
        Command.parseString(malformedJson);
    }

    @Test(
            expected = JsonMappingException.class)
    public void testParseIllegalJson() throws IOException {
        String illegalJson = "{\"tpe\":\"PLAY\"}";
        assertNull(Command.parseStringQuietly(illegalJson));
        Command.parseString(illegalJson);
    }
}
