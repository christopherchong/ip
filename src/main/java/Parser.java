import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static String[] parse(String input) {
        String command;
        String arguments = "";

        if (input.contains(" ")) {
            String[] inputArray = input.split(" ", 2);
            command = inputArray[0];
            arguments = inputArray[1];
        } else {
            command = input;
        }

        return new String[]{command, arguments};
    }

    public static Command parse2(String input) throws HaruException {
        String command;
        String arguments = "";

        if (input.contains(" ")) {
            String[] inputArray = input.split(" ", 2);
            command = inputArray[0];
            arguments = inputArray[1];
        } else {
            command = input;
        }

        switch (command) {
        case "list": {
            return new ListCommand();
        }
        case "mark":
        case "unmark": {
            if (arguments.isEmpty() || !arguments.matches("\\d+")) {
                throw new HaruException.NumberFormatException();
            }
            int index = Integer.parseInt(arguments) - 1;
            if (command.equals("mark")) {
                return new MarkCommand(index);
            }
            return new UnmarkCommand(index);
        }
        case "todo": {
            if (arguments.isEmpty()) {
                throw new HaruException.InvalidTodoException();
            }
            String description = arguments;
            return new AddCommand(new Todo(description));
        }
        case "deadline": {
            if (!arguments.contains(" /by ")) {
                throw new HaruException.InvalidDeadlineException();
            }
            String[] argumentsArray = arguments.split(" /by ", 2);
            String description = argumentsArray[0];
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y Hmm");
                LocalDateTime by = LocalDateTime.parse(argumentsArray[1], formatter);
                return new AddCommand(new Deadline(description, by));
            } catch (DateTimeParseException e) {
                throw new HaruException.DateTimeParseException();
            }
        }
        case "event": {
            if (!arguments.contains(" /from ") || !arguments.contains(" /to ")
                    || arguments.lastIndexOf(" /to ") < arguments.lastIndexOf(" /from ")) {
                throw new HaruException.InvalidEventException();
            }
            String[] argumentsArray = arguments.split(" /from ", 2);
            String description = argumentsArray[0];
            String dates = argumentsArray[1];
            String[] datesArray = dates.split(" /to ", 2);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y Hmm");
                LocalDateTime from = LocalDateTime.parse(datesArray[0], formatter);
                LocalDateTime to = LocalDateTime.parse(datesArray[1], formatter);
                if (to.isBefore(from)) {
                    throw new HaruException.DateTimeOrderException();
                } else if (to.isEqual(from)) {
                    throw new HaruException.SameDateTimeException();
                }
                return new AddCommand(new Event(description, from, to));
            } catch (DateTimeParseException e) {
                throw new HaruException.DateTimeParseException();
            }
        }
        case "delete": {
            if (arguments.isEmpty() || !arguments.matches("\\d+")) {
                throw new HaruException.NumberFormatException();
            }
            int index = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(index);
        }
        case "bye": {
            return new ExitCommand();
        }
        default: {
            throw new HaruException.InvalidCommandException();
        }
        }
    }
}
