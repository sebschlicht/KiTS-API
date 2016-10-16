package de.jablab.sebschlicht.android.kits.commands;

/**
 * Command to register a KiTS application to the server.
 *
 * @author sebschlicht
 *
 */
public class RegisterCommand extends Command {

    /**
     * Creates a new registration command.
     */
    public RegisterCommand() {
        super(CommandType.REGISTER);
    }
}
