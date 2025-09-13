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
        return message;
    }

    /**
     * Thrown when the task file cannot be read because it is corrupted.
     */
    public static class CorruptedFileException extends HaruException {
        public CorruptedFileException() {
            super("Oh no! It looks like the task file got corrupted! Let's try to fix it.");
        }
    }

    /**
     * Thrown when an unrecognized command is provided.
     */
    public static class InvalidCommandException extends HaruException {
        public InvalidCommandException() {
            super("Hmm, I don't recognize that command. Let's try something else!");
        }
    }

    /**
     * Thrown when a number is expected but was not provided.
     */
    public static class NumberFormatException extends HaruException {
        public NumberFormatException() {
            super("Oops! There wasn't a number. Could you try entering a number?");
        }
    }

    /**
     * Thrown when arguments are provided in the list command.
     */
    public static class InvalidListException extends HaruException {
        public InvalidListException() {
            super("To see all tasks, just type \"list\". Easy peasy!");
        }
    }

    /**
     * Thrown when there is no tasks in the task list.
     */
    public static class NoTasksException extends HaruException {
        public NoTasksException() {
            super("Looks like there aren't any tasks yet! Let's add some!");
        }
    }

    /**
     * Thrown when an invalid index is provided.
     */
    public static class InvalidIndexException extends HaruException {
        public InvalidIndexException() {
            super("Hmm, that number doesn't match any task. Let's check again!");
        }
    }

    /**
     * Thrown when the task to be marked was already marked.
     */
    public static class MarkException extends HaruException {
        public MarkException() {
            super("This task is already marked as done! No worries!");
        }
    }

    /**
     * Thrown when the task to be unmarked was already unmarked.
     */
    public static class UnmarkException extends HaruException {
        public UnmarkException() {
            super("This task is already not done. All good!");
        }
    }

    /**
     * Thrown when the input does not match the format required for todo.
     */
    public static class InvalidTodoException extends HaruException {
        public InvalidTodoException() {
            super("Uh-oh! The task wasn't entered correctly.\nTry: \"todo <task>\"");
        }
    }

    /**
     * Thrown when the input does not match the format required for deadline.
     */
    public static class InvalidDeadlineException extends HaruException {
        public InvalidDeadlineException() {
            super("Hmm, that deadline didn't look right.\nTry: \"deadline <task> /by <date/time>\"");
        }
    }

    /**
     * Thrown when the input does not match the format required for event.
     */
    public static class InvalidEventException extends HaruException {
        public InvalidEventException() {
            super("Oops! The event wasn't entered correctly.\nTry: \"event <task> /from <date/time> /to <date/time>\"");
        }
    }

    /**
     * Thrown when the date time input does not match the format required.
     */
    public static class DateTimeParseException extends HaruException {
        public DateTimeParseException() {
            super("Hmm, the date wasn't quite right.\nTry day/month/year time (e.g., 2/12/2019 1800)");
        }
    }

    /**
     * Thrown when the date time input for 'to' is earlier than 'from'.
     */
    public static class DateTimeOrderException extends HaruException {
        public DateTimeOrderException() {
            super("Oops! The 'to' date is before the 'from' date. Let's fix that!");
        }
    }

    /**
     * Thrown when the date time input for 'to' is the same as 'from'.
     */
    public static class SameDateTimeException extends HaruException {
        public SameDateTimeException() {
            super("The 'to' and 'from' dates are the same. Let's enter different times!");
        }
    }

    /**
     * Thrown when no description was provided in the find command.
     */
    public static class InvalidFindException extends HaruException {
        public InvalidFindException() {
            super("Uh-oh! You didn't provide a description. Enter a keyword to find tasks!");
        }
    }

    /**
     * Thrown when invalid tag format was provided in the tag command.
     */
    public static class InvalidTagFormatException extends HaruException {
        public InvalidTagFormatException() {
            super("Hmm, that tag format isn't right.\nTry: \"tag <index> #<tag name>\"");
        }
    }

    /**
     * Thrown when no tag was provided in the tag command.
     */
    public static class NoTagException extends HaruException {
        public NoTagException() {
            super("Looks like you didn't provide a tag. Let's try again!");
        }
    }

    /**
     * Thrown when a tag already exists for a task.
     */
    public static class ExistingTagException extends HaruException {
        public ExistingTagException(String tag) {
            super("This task already has a " + tag + " tag! No worries though.");
        }
    }

    /**
     * Thrown when tag provided to untag does not exist in the task.
     */
    public static class NoExistingTagException extends HaruException {
        public NoExistingTagException(String tag) {
            super("Hmm, this task doesn't have a " + tag + " tag. Let's check again!");
        }
    }

    /**
     * Thrown when arguments are provided in the bye command.
     */
    public static class InvalidByeException extends HaruException {
        public InvalidByeException() {
            super("To exit the program, just type \"bye\". Got it?");
        }
    }
}
