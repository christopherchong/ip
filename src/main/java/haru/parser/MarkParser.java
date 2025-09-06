package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.MarkCommand;
import haru.command.UnmarkCommand;

public class MarkParser {
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
