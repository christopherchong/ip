package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.DeleteCommand;

/**
 * Parses {@code Delete} input and converts it into an {@link DeleteCommand}.
 */
public class DeleteParser {
    /**
     * Parses {@code Delete} input and converts it into an {@link DeleteCommand}.
     *
     * @param arguments The {@code Delete} input to be parsed.
     * @return the corresponding {@code DeleteCommand} object.
     * @throws HaruException If the input is invalid.
     */
    public static Command parse(String arguments) throws HaruException {
        validateArguments(arguments);
        int index = Integer.parseInt(arguments) - 1;
        return new DeleteCommand(index);
    }

    private static void validateArguments(String arguments) throws HaruException {
        boolean isNotNumeric = !arguments.matches("\\d+");
        if (arguments.isEmpty() || isNotNumeric) {
            throw new HaruException.NumberFormatException();
        }
    }
}
