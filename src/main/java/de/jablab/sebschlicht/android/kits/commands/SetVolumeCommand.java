package de.jablab.sebschlicht.android.kits.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetVolumeCommand extends Command {

    protected static final String FIELD_VOLUME = "volumeType";

    private int volume;

    public SetVolumeCommand(
            @JsonProperty("volume") int volume) {
        super(CommandType.SET_VOLUME);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}
