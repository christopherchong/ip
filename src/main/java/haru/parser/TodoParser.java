package haru.parser;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;
import haru.task.Todo;

/**
 * Parses {@link Todo} input and converts it into an {@link AddCommand}.
 */
public class TodoParser {
    /**
     * Parses {@link Todo} input and converts it into an {@link AddCommand}.
     *
     * @param arguments The {@code Todo} input to be parsed.
     * @return the corresponding {@code AddCommand} object.
     * @throws HaruException If the input is invalid.
     */
    public static Command parse(String arguments) throws HaruException {
        validateFormat(arguments);
        return new AddCommand(new Todo(arguments));
    }

    private static void validateFormat(String arguments) throws HaruException {
        boolean isNotAlphanumeric = !arguments.matches(".*[a-zA-Z0-9].*");
        if (arguments.isEmpty() || isNotAlphanumeric) {
            throw new HaruException.InvalidTodoException();
        }
    }
}
