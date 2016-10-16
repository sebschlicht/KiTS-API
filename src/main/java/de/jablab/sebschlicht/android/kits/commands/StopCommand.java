package de.jablab.sebschlicht.android.kits.commands;

/**
 * Command to stop the playback at the server.
 *
 * @author sebschlicht
 *
 */
public class StopCommand extends Command {

    /**
     * Creates a new stop playback command.
     */
    public StopCommand() {
        super(CommandType.STOP);
    }
}
