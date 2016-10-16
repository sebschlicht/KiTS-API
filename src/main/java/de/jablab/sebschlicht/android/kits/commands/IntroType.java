package de.jablab.sebschlicht.android.kits.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of the intro that the {@link PlayCommand play command} intends to play.
 * 
 * @author sebschlicht
 *
 */
public enum IntroType {

    /**
     * short audio intro
     */
    SHORT("short"),

    /**
     * full video intro
     */
    FULL("full");

    private String identifier;

    /**
     * Creates a new intro type
     *
     * @param identifier
     *            unique identifier
     */
    private IntroType(
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
     *
     * @param type
     *            unique identifier of an intro type
     * @return intro type with the unique identifier passed
     */
    @JsonCreator
    public static IntroType parseString(String type) {
        if (SHORT.getIdentifier().equalsIgnoreCase(type)) {
            return SHORT;
        } else if (FULL.getIdentifier().equalsIgnoreCase(type)) {
            return FULL;
        }
        return null;
    }
}
