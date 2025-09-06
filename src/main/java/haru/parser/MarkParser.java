package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.MarkCommand;
import haru.command.UnmarkCommand;

/**
 * Parses {@code Mark} or {@code Unmark} input and converts it into the
 * corresponding {@link MarkCommand} or {@link UnmarkCommand}.
 */
public class MarkParser {
    /**
     * Parses mark or unmark input and converts it into the
     * corresponding {@link MarkCommand} or {@link UnmarkCommand}.
     *
     * @param arguments The mark or unmark input to be parsed.
     * @return the corresponding {@code MarkCommand} or {@code UnmarkCommand} object.
     * @throws HaruException If the input is invalid.
     */
    public static Command parse(String arguments, String command) throws HaruException {
        validateArguments(arguments);
        int taskIndex = Integer.parseInt(arguments) - 1;
        if (command.equals("mark")) {
            return new MarkCommand(taskIndex);
        }
        return new UnmarkCommand(taskIndex);
    }

    private static void validateArguments(String arguments) throws HaruException {
        boolean isNotNumeric = !arguments.matches("\\d+");
        if (arguments.isEmpty() || isNotNumeric) {
            throw new HaruException.NumberFormatException();
        }
    }
}
