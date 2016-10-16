package de.jablab.sebschlicht.android.kits.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Command to set the remote playback volume of the server.
 *
 * @author sebschlicht
 *
 */
public class SetVolumeCommand extends Command {

    private int volume;

    /**
     * Creates a new set volume command.
     *
     * @param volume
     *            new playback volume
     */
    public SetVolumeCommand(
            @JsonProperty("volume") int volume) {
        super(CommandType.SET_VOLUME);
        this.volume = volume;
    }

    /**
     *
     * @return new playback volume
     */
    public int getVolume() {
        return volume;
    }
}
