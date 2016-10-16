package de.jablab.sebschlicht.android.kits.commands;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

/**
 * Base command specifying basic properties and providing methods to transform
 * any derived Command from and into JSON.
 *
 * @author sebschlicht
 *
 */
@JsonTypeInfo(
        use = Id.CUSTOM,
        include = As.EXISTING_PROPERTY,
        property = "type")
@JsonTypeIdResolver(CommandTypeIdResolver.class)
public abstract class Command {

    /**
     * data client sends to initialize first contact with server
     */
    public static final String SERVER_SEARCH_REQUEST_STRING = "WHEREISKITS";

    /**
     * data server responds with to make the client aware of it
     */
    public static final String SERVER_SEARCH_RESPONSE_STRING = "HEREISKITS";

    /**
     * object mapper for JSON (de-)serialization
     */
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    private CommandType type;

    /**
     * Initializes the base command with a type.
     *
     * @param type
     *            command type specifying the intended action
     */
    protected Command(
            CommandType type) {
        this.type = type;
    }

    /**
     *
     * @return command type specifying the intended action
     */
    public CommandType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Command)) {
            return false;
        }
        Command other = (Command) o;
        return (type == null && other.getType() == null)
                || type.equals(other.getType());
    }

    /**
     * Serializes a command into a JSON string.
     *
     * @param command
     *            command to be serialized
     * @return JSON string representing the command passed
     * @throws JsonProcessingException
     *             if the serialization fails
     */
    public static String serializeCommand(Command command)
            throws JsonProcessingException {
        return MAPPER.writeValueAsString(command);
    }

    /**
     * Serializes a command into a JSON string suppressing any errors.
     *
     * @param command
     *            command to be serialized
     * @return JSON string representing the command passed<br>
     *         or <code>null</code> if the serialization fails (use
     *         {@link #serializeCommand(Command)} if you need information about
     *         the cause)
     */
    public static String serializeCommandQuietly(Command command) {
        try {
            return serializeCommand(command);
        } catch (JsonProcessingException e) {
            // ignore
        }
        return null;
    }

    /**
     * Deserializes a command from a JSON string.
     *
     * @param value
     *            JSON string representing a command
     * @return command instance that is equivalent to the JSON string passed<br>
     *         or <code>null</code> if the JSON is <code>null</code>
     * @throws IOException
     *             if the deserialization fails due to low-level IO problems
     * @throws JsonMappingException
     *             if the deserialization fails due to illegal JSON
     * @throws JsonParseException
     *             if the deserialization fails due to malformed JSON
     */
    public static Command parseString(String value)
            throws JsonParseException, JsonMappingException, IOException {
        if (value == null) {
            return null;
        }
        return MAPPER.readValue(value, Command.class);
    }

    /**
     * Deserializes a command from a JSON string suppressing any errors.
     *
     * @param value
     *            JSON string representing a command
     * @return command instance that is equivalent to the JSON string passed<br>
     *         or <code>null</code> if the JSON is <code>null</code> or the
     *         deserialization fails
     */
    public static Command parseStringQuietly(String value) {
        try {
            return parseString(value);
        } catch (JsonParseException e) {
            // ignore
        } catch (JsonMappingException e) {
            // ignore
        } catch (IOException e) {
            // ignore
        }
        return null;
    }
}
