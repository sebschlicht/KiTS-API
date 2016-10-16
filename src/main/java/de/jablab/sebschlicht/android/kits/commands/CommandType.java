package de.jablab.sebschlicht.android.kits.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CommandType {

    SET_VOLUME("volume", SetVolumeCommand.class),

    PLAY("play", PlayCommand.class),

    REGISTER("register", RegisterCommand.class),

    STOP("stop", StopCommand.class);

    private String identifier;

    private Class<? extends Command> type;

    private CommandType(
            String identifier,
            Class<? extends Command> type) {
        this.identifier = identifier;
        this.type = type;
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }

    public Class<? extends Command> getType() {
        return type;
    }

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
