package de.jablab.sebschlicht.android.kits.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of a {@link Command command} that is used to identify its subclass.
 *
 * @author sebschlicht
 *
 */
public enum CommandType {

    /**
     * set the volume to a specific level
     */
    SET_VOLUME("volume", SetVolumeCommand.class),

    /**
     * play one of the series intros
     */
    PLAY("play", PlayCommand.class),

    /**
     * register a KiTS application to the server
     */
    REGISTER("register", RegisterCommand.class),

    /**
     * stop playing the current series intro
     */
    STOP("stop", StopCommand.class);

    private String identifier;

    private Class<? extends Command> type;

    /**
     * Creates a new command type.
     *
     * @param identifier
     *            unique identifier
     * @param type
     *            command subclass that corresponds to this type
     */
    private CommandType(
            String identifier,
            Class<? extends Command> type) {
        this.identifier = identifier;
        this.type = type;
    }

    /**
     * TODO remove: for compatibility reasons only
     *
     * @return unique identifier
     */
    @JsonValue
    public String getIdentifier() {
        return identifier;
    }

    /**
     *
     * @return command subclass that corresponds to this type
     */
    public Class<? extends Command> getType() {
        return type;
    }

    /**
     * TODO remove: for compatibility reasons only<br>
     * <br>
     * Loads a command type from string.
     *
     * @param commandType
     *            unique identifier of a command type
     * @return command type with the unique identifier passed
     */
    @JsonCreator
    public static CommandType parseString(String commandType) {
        if (SET_VOLUME.getIdentifier().equalsIgnoreCase(commandType)) {
            return SET_VOLUME;
        } else if (PLAY.getIdentifier().equalsIgnoreCase(commandType)) {
            return PLAY;
        } else if (REGISTER.getIdentifier().equalsIgnoreCase(commandType)) {
            return REGISTER;
        } else if (STOP.getIdentifier().equalsIgnoreCase(commandType)) {
            return STOP;
        }
        return null;
    }
}
