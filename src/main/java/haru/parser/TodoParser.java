package haru.parser;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;
import haru.task.Todo;

public class TodoParser {
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
