package haru.parser;

import java.time.LocalDateTime;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;
import haru.task.Deadline;
import haru.util.DateTimeUtil;

public class DeadlineParser {
    public static Command parse(String arguments) throws HaruException {
        validateFormat(arguments);
        String[] deadlineArguments = arguments.split(" /by ", 2);
        String deadlineDescription = deadlineArguments[0];
        LocalDateTime by = DateTimeUtil.parseInput(deadlineArguments[1]);
        return new AddCommand(new Deadline(deadlineDescription, by));
    }

    private static void validateFormat(String arguments) throws HaruException {
        boolean hasBy = arguments.contains(" /by ");
        if (!hasBy) {
            throw new HaruException.InvalidDeadlineException();
        }
    }
}
