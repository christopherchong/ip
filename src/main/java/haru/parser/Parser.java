package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.ExitCommand;
import haru.command.ListCommand;

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
        String[] parsed = parseInput(input);
        String command = parsed[0];
        String arguments = parsed[1];

        return switch (command) {
        case "list" -> new ListCommand();
        case "mark", "unmark" -> MarkParser.parse(arguments, command);
        case "todo" -> TodoParser.parse(arguments);
        case "deadline" -> DeadlineParser.parse(arguments);
        case "event" -> EventParser.parse(arguments);
        case "delete" -> DeleteParser.parse(arguments);
        case "find" -> FindParser.parse(arguments);
        case "bye" -> new ExitCommand();
        default -> throw new HaruException.InvalidCommandException();
        };
    }

    private static String[] parseInput(String input) {
        String command;
        String arguments = "";

        if (input.contains(" ")) {
            String[] inputArray = input.split(" ", 2);
            command = inputArray[0];
            arguments = inputArray[1];
        } else {
            command = input;
        }
        return new String[] { command, arguments };
    }
}
