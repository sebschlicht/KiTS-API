package de.jablab.sebschlicht.android.kits.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CommandTypeTest {

    /**
     * Tests if all valid command type identifiers are parsed to the appropriate
     * type.
     */
    @Test
    public void testValidIdentifiers() {
        for (CommandType type : CommandType.values()) {
            assertEquals(type, CommandType.parseString(type.getIdentifier()));
        }
    }

    /**
     * Tests if an invalid command type identifier leads to the correct result.
     */
    @Test
    public void testInvalidIdentifier() {
        CommandType type =
                CommandType.parseString("invalidcommandtypeidentifier");
        assertNull(type);
    }
}
