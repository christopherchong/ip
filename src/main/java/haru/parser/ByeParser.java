package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.ExitCommand;

/**
 * Parses bye input and converts it into an {@link ExitCommand}.
 */
public class ByeParser {
    /**
     * Parses bye input and converts it into an {@link ExitCommand}.
     *
     * @param arguments The {@code Deadline} input to be parsed.
     * @return the corresponding {@code ExitCommand} object.
     * @throws HaruException If the input is invalid.
     */
    public static Command parse(String arguments) throws HaruException {
        validateFormat(arguments);
        return new ExitCommand();
    }

    private static void validateFormat(String arguments) throws HaruException {
        if (!arguments.isEmpty()) {
            throw new HaruException.InvalidByeException();
        }
    }
}
