package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.DeleteCommand;

public class DeleteParser {
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
