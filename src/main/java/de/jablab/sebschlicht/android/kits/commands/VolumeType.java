package de.jablab.sebschlicht.android.kits.commands;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of volume change the {@link ChangeVolumeCommand change volume command}
 * intends to achieve. It is used to determine the new playback volume.
 *
 * @author sebschlicht
 *
 */
@Deprecated
public enum VolumeType {

    /**
     * decrease playback volume
     */
    DOWN("down"),

    /**
     * increase playback volume
     */
    UP("up");

    private String identifier;

    /**
     * Creates a new playback volume change type.
     *
     * @param identifier
     *            unique identifier
     */
    private VolumeType(
            String identifier) {
        this.identifier = identifier;
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
     * TODO remove: for compatibility reasons only<br>
     * <br>
     * Loads a volume change type from string.
     *
     * @param type
     *            unique identifier of a volume change type
     * @return volume change type with the unique identifier passed
     */
    public static VolumeType parseString(String type) {
        if (DOWN.getIdentifier().equalsIgnoreCase(type)) {
            return DOWN;
        } else if (UP.getIdentifier().equalsIgnoreCase(type)) {
            return UP;
        }
        return null;
    }
}
