package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.FindCommand;

public class FindParser {
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
