package de.jablab.sebschlicht.android.kits.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Command to play one of the intros of a series.
 * 
 * @author sebschlicht
 *
 */
public class PlayCommand extends Command {

    private String name;

    private IntroType introType;

    /**
     * Creates a new play command.
     *
     * @param name
     *            series name
     * @param introType
     *            type of the intro that should be played
     */
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

    /**
     *
     * @return series name
     */
    public String getName() {
        return name;
    }

    public void setIntroType(IntroType introType) {
        this.introType = introType;
    }

    /**
     *
     * @return type of the intro that should be played
     */
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
