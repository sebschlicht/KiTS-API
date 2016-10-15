package de.jablab.sebschlicht.android.kits.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayCommand extends Command {

    protected static final String FIELD_NAME = "name";

    protected static final String FIELD_INTRO_TYPE = "introType";

    private String name;

    private IntroType introType;

    public PlayCommand(
            @JsonProperty("name") String name,
            @JsonProperty("introType") IntroType introType) {
        super(CommandType.PLAY);
        this.name = name;
        this.introType = introType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIntroType(IntroType introType) {
        this.introType = introType;
    }

    public IntroType getIntroType() {
        return introType;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        PlayCommand other = (PlayCommand) o;

        if (name == null && other.name != null) {
            return false;
        } else if (name != null && !name.equals(other.name)) {
            return false;
        }
        if (introType == null && other.introType != null) {
            return false;
        } else if (introType != null && !introType.equals(other.introType)) {
            return false;
        }
        return true;
    }
}
