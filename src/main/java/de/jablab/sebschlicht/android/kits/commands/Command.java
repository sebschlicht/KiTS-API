package de.jablab.sebschlicht.android.kits.commands;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTypeInfo(
        use = Id.CLASS,
        include = As.PROPERTY,
        property = "class")
//        use = Id.CUSTOM,
//        include = As.EXISTING_PROPERTY,
//        property = "type")
//@JsonTypeIdResolver(CommandTypeIdResolver.class)
public abstract class Command {

    public static final String SERVER_SEARCH_REQUEST_STRING = "WHEREISKITS";

    public static final String SERVER_SEARCH_RESPONSE_STRING = "HEREISKITS";

    protected static final ObjectMapper MAPPER = new ObjectMapper();

    private CommandType type;

    public Command(
            CommandType type) {
        this.type = type;
    }

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
        if (!getClass().equals(o.getClass())) {
            return false;
        }
        Command other = (Command) o;
        return (type == null && other.getType() == null)
                || type.equals(other.getType());
    }

    public static String serializeCommand(Command command) {
        try {
            return MAPPER.writeValueAsString(command);
        } catch (JsonProcessingException e) {
            // TODO log
        }
        return null;
    }

    public static Command parseString(String value) {
        if (value == null) {
            return null;
        }
        try {
            return MAPPER.readValue(value, Command.class);
        } catch (JsonParseException e) {
            // TODO log
        } catch (JsonMappingException e) {
            // TODO log
        } catch (IOException e) {
            // this should never happen
            // TODO log
        }
        return null;
    }
}
