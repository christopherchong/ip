package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.FindCommand;

/**
 * Parses {@code Find} input and converts it into an {@link FindCommand}.
 */
public class FindParser {
    /**
     * Parses {@code Find} input and converts it into an {@link FindCommand}.
     *
     * @param arguments The {@code Find} input to be parsed.
     * @return the corresponding {@code FindCommand} object.
     * @throws HaruException If the input is invalid.
     */
    public static Command parse(String arguments) throws HaruException {
        validateArguments(arguments);
        return new FindCommand(arguments);
    }

    private static void validateArguments(String arguments) throws HaruException {
        if (arguments.isEmpty()) {
            throw new HaruException.InvalidFindException();
        }
    }
}
