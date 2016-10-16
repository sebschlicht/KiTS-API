package de.jablab.sebschlicht.android.kits.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IntroType {

    SHORT("short"),

    FULL("full");

    private String identifier;

    private IntroType(
            String identifier) {
        this.identifier = identifier;
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }

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
