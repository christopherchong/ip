package haru;

/**
 * Acts as the base class for all exceptions in the application.
 *
 * <p>Each specific error is represented as a static nested subclass,
 * such as {@link InvalidCommandException} or {@link DateTimeParseException}.
 * These provide user-friendly error messages formatted with a separator line
 * for consistency in the UI.</p>
 */
public class HaruException extends Exception {
    private static final String LINE =
            "___________________________________________________________________________________\n";

    /**
     * Creates a {@code HaruException} with an error message,
     * formatted with horizontal separator lines.
     *
     * @param message the details of the error message.
     */
    public HaruException(String message) {
        super(formatMessage(message));
    }

    /**
     * Formats the error message with horizontal separator lines before and after.
     *
     * @param message The error message to be formatted.
     * @return The error message with horizontal separator lines surrounding it.
     */
    private static String formatMessage(String message) {
        return LINE + message + "\n" + LINE;
    }

    /**
     * Thrown when the task file cannot be read because it is corrupted.
     */
    public static class CorruptedFileException extends HaruException {
        public CorruptedFileException() {
            super("The task file is corrupted!");
        }
    }

    /**
     * Thrown when an unrecognized command is provided.
     */
    public static class InvalidCommandException extends HaruException {
        public InvalidCommandException() {
            super("I don't recognize that command :(");
        }
    }

    /**
     * Thrown when a number is expected but was not provided.
     */
    public static class NumberFormatException extends HaruException {
        public NumberFormatException() {
            super("A number was not entered! :(");
        }
    }

    /**
     * Thrown when there is no tasks in the task list.
     */
    public static class NoTasksException extends HaruException {
        public NoTasksException() {
            super("There are no tasks!");
        }
    }

    /**
     * Thrown when there is no tasks in the task list.
     */
    public static class InvalidIndexException extends HaruException {
        public InvalidIndexException() {
            super("Number entered was invalid!");
        }
    }

    /**
     * Thrown when the task to be marked was already marked.
     */
    public static class MarkException extends HaruException {
        public MarkException() {
            super("Task is already marked!");
        }
    }

    /**
     * Thrown when the task to be unmarked was already unmarked.
     */
    public static class UnmarkException extends HaruException {
        public UnmarkException() {
            super("Task is already unmarked!");
        }
    }

    /**
     * Thrown when the input does not match the format required for todo.
     */
    public static class InvalidTodoException extends HaruException {
        public InvalidTodoException() {
            super("The task was not entered correctly!\n" + "Try doing \"todo <task>\"");
        }
    }

    /**
     * Thrown when the input does not match the format required for deadline.
     */
    public static class InvalidDeadlineException extends HaruException {
        public InvalidDeadlineException() {
            super("The task was not entered correctly!\n" + "Try doing \"deadline <task> /by <date/time>\"");
        }
    }

    /**
     * Thrown when the input does not match the format required for event.
     */
    public static class InvalidEventException extends HaruException {
        public InvalidEventException() {
            super("The task was not entered correctly!\n" + "Try \"event <task> /from <date/time> /to <date/time>\"");
        }
    }

    /**
     * Thrown when the date time input does not match the format required.
     */
    public static class DateTimeParseException extends HaruException {
        public DateTimeParseException() {
            super("The date was not entered correctly!\n" + "Try following day/month/year time (e.g. 2/12/2019 1800)");
        }
    }

    /**
     * Thrown when the date time input for 'to' is earlier than 'from'.
     */
    public static class DateTimeOrderException extends HaruException {
        public DateTimeOrderException() {
            super("The 'to' date is earlier than the 'from' date!\n" + "Try entering the dates again!");
        }
    }

    /**
     * Thrown when the date time input for 'to' is the same as 'from'.
     */
    public static class SameDateTimeException extends HaruException {
        public SameDateTimeException() {
            super("The 'to' and 'from' dates are the same!\n" + "Try entering the dates again!");
        }
    }

    /**
     * Thrown when no description was provided in the find command.
     */
    public static class InvalidFindException extends HaruException {
        public InvalidFindException() {
            super("No description was provided. Enter a keyword!");
        }
    }
}
