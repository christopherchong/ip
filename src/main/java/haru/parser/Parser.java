package haru.parser;

import java.time.LocalDateTime;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;
import haru.command.DeleteCommand;
import haru.command.ExitCommand;
import haru.command.FindCommand;
import haru.command.ListCommand;
import haru.command.MarkCommand;
import haru.command.UnmarkCommand;
import haru.task.Deadline;
import haru.task.Event;
import haru.task.Todo;
import haru.util.DateTimeUtil;

/**
 * Parses user input and converts it into an executable {@link Command}.
 * <p>
 * The {@code Parser} identifies the type of command from the input string
 * and constructs the corresponding {@code Command} object.
 * </p>
 */
public class Parser {
    /**
     * Parses the given user input and returns the corresponding {@link Command}.
     *
     * <p>List of supported commands:
     * <ul>
     *     <li>{@code list}</li>
     *     <li>{@code mark <index>}</li>
     *     <li>{@code unmark <index>}</li>
     *     <li>{@code todo <description>}</li>
     *     <li>{@code deadline <description> /by <date time>}</li>
     *     <li>{@code event <description> /from <date time> /to <date time>}</li>
     *     <li>{@code delete <index>}</li>
     *     <li>{@code bye}</li>
     *     <li>{@code find <description>}</li>
     * </ul>
     *
     * @param input The input to be parsed.
     * @return the corresponding {@code Command} object.
     * @throws HaruException If the input is invalid, incomplete, or formatted incorrectly.
     */
    public static Command parse(String input) throws HaruException {
        String command;
        String arguments = "";

        if (input.contains(" ")) {
            String[] inputArray = input.split(" ", 2);
            command = inputArray[0];
            assert command != null : "There should be a command after parsing";
            arguments = inputArray[1];
        } else {
            command = input;
        }

        switch (command) {
        case "list":
            return new ListCommand();

        case "mark":
        case "unmark":
            if (arguments.isEmpty() || !arguments.matches("\\d+")) {
                throw new HaruException.NumberFormatException();
            }
            int taskIndex = Integer.parseInt(arguments) - 1;
            if (command.equals("mark")) {
                return new MarkCommand(taskIndex);
            }
            return new UnmarkCommand(taskIndex);

        case "todo":
            if (arguments.isEmpty() || !arguments.matches(".*[a-zA-Z0-9].*")) {
                throw new HaruException.InvalidTodoException();
            }
            return new AddCommand(new Todo(arguments));

        case "deadline":
            if (!arguments.contains(" /by ")) {
                throw new HaruException.InvalidDeadlineException();
            }
            String[] deadlineArguments = arguments.split(" /by ", 2);
            String deadlineDescription = deadlineArguments[0];
            LocalDateTime by = DateTimeUtil.parseInput(deadlineArguments[1]);
            return new AddCommand(new Deadline(deadlineDescription, by));

        case "event":
            if (!arguments.contains(" /from ") || !arguments.contains(" /to ")
                    || arguments.lastIndexOf(" /to ") < arguments.lastIndexOf(" /from ")) {
                throw new HaruException.InvalidEventException();
            }
            String[] eventArguments = arguments.split(" /from ", 2);
            String eventDescription = eventArguments[0];
            String eventDates = eventArguments[1];
            String[] dates = eventDates.split(" /to ", 2);
            LocalDateTime from = DateTimeUtil.parseInput(dates[0]);
            LocalDateTime to = DateTimeUtil.parseInput(dates[1]);
            if (to.isBefore(from)) {
                throw new HaruException.DateTimeOrderException();
            } else if (to.isEqual(from)) {
                throw new HaruException.SameDateTimeException();
            }
            return new AddCommand(new Event(eventDescription, from, to));

        case "delete":
            if (arguments.isEmpty() || !arguments.matches("\\d+")) {
                throw new HaruException.NumberFormatException();
            }
            int index = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(index);

        case "find":
            if (arguments.isEmpty()) {
                throw new HaruException.InvalidFindException();
            }
            return new FindCommand(arguments);

        case "bye":
            return new ExitCommand();

        default:
            throw new HaruException.InvalidCommandException();
        }
    }
}
