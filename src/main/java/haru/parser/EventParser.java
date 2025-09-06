package haru.parser;

import java.time.LocalDateTime;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;
import haru.task.Event;
import haru.util.DateTimeUtil;

/**
 * Parses {@link Event} input and converts it into an {@link AddCommand}.
 */
public class EventParser {
    /**
     * Parses {@link Event} input and converts it into an {@link AddCommand}.
     *
     * @param arguments The {@code Event} input to be parsed.
     * @return the corresponding {@code AddCommand} object.
     * @throws HaruException If the input is invalid.
     */
    public static Command parse(String arguments) throws HaruException {
        validateFormat(arguments);
        String[] eventArguments = arguments.split(" /from ", 2);
        String description = eventArguments[0];
        String datesArray = eventArguments[1];
        String[] dates = datesArray.split(" /to ", 2);
        LocalDateTime from = DateTimeUtil.parseInput(dates[0]);
        LocalDateTime to = DateTimeUtil.parseInput(dates[1]);
        validateDateOrder(from, to);
        return new AddCommand(new Event(description, from, to));
    }

    private static void validateFormat(String arguments) throws HaruException {
        boolean hasFromAndTo = arguments.contains(" /from ") && arguments.contains(" /to ");
        boolean hasFromAfterTo = arguments.indexOf(" /from ") > arguments.indexOf(" /to ");
        if (!hasFromAndTo || hasFromAfterTo) {
            throw new HaruException.InvalidEventException();
        }
    }

    private static void validateDateOrder(LocalDateTime from, LocalDateTime to) throws HaruException {
        if (to.isBefore(from)) {
            throw new HaruException.DateTimeOrderException();
        }
        if (to.isEqual(from)) {
            throw new HaruException.SameDateTimeException();
        }
    }
}
