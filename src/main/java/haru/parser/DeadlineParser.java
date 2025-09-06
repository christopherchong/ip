package haru.parser;

import java.time.LocalDateTime;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;
import haru.task.Deadline;
import haru.util.DateTimeUtil;

/**
 * Parses {@link Deadline} input and converts it into an {@link AddCommand}.
 */
public class DeadlineParser {
    /**
     * Parses {@link Deadline} input and converts it into an {@link AddCommand}.
     *
     * @param arguments The {@code Deadline} input to be parsed.
     * @return the corresponding {@code AddCommand} object.
     * @throws HaruException If the input is invalid.
     */
    public static Command parse(String arguments) throws HaruException {
        validateFormat(arguments);
        String[] deadlineArguments = arguments.split(" /by ", 2);
        assert deadlineArguments.length == 2 : "deadlineArguments should split into description and 'by' datetime";
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
