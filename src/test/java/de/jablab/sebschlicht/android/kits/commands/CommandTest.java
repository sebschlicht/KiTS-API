package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;

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
        String malformedJson =
                "{\"type\":\"" + CommandType.STOP.getIdentifier() + "\"";
        assertNull(Command.parseStringQuietly(malformedJson));
        Command.parseString(malformedJson);
    }

    @Test(
            expected = JsonMappingException.class)
    public void testParseInvalidJson() throws IOException {
        String invalidJson =
                "{\"typ\":\"" + CommandType.STOP.getIdentifier() + "\"}";
        assertNull(Command.parseStringQuietly(invalidJson));
        Command.parseString(invalidJson);
    }

    @Test
    public void testMixedCaseType() throws IOException {
        String json = "{\"type\":\"sTop\"}";
        Command base = Command.parseString(json);
        assertNotNull(base);
    }

    @Test(
            expected = InvalidTypeIdException.class)
    public void testInvalidType() throws IOException {
        String invalidType = "blubb";
        for (CommandType type : CommandType.values()) {
            if (type.getIdentifier().equals(invalidType)) {
                fail("Type \"" + invalidType
                        + "\" is supposed to be invalid but is valid!");
            }
        }

        String invalidTypeJson = "{\"type\":\"" + invalidType + "\"}";
        assertNull(Command.parseStringQuietly(invalidTypeJson));
        Command.parseString(invalidTypeJson);
    }
}
