package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.ListCommand;

/**
 * Parses list input and converts it into an {@link ListCommand}.
 */
public class ListParser {
    /**
     * Parses list input and converts it into an {@link ListCommand}.
     *
     * @param arguments The {@code list} input to be parsed.
     * @return the corresponding {@code ListCommand} object.
     * @throws HaruException If the input is invalid.
     */
    public static Command parse(String arguments) throws HaruException {
        validateFormat(arguments);
        return new ListCommand();
    }

    private static void validateFormat(String arguments) throws HaruException {
        if (!arguments.isEmpty()) {
            throw new HaruException.InvalidListException();
        }
    }
}
