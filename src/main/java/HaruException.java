public class HaruException extends Exception {
    private static final String LINE = "____________________________________________________________\n";

    public HaruException(String message) {
        super(formatMessage(message));
    }

    private static String formatMessage(String message) {
        return LINE + message + "\n" + LINE;
    }

    public static class InvalidCommandException extends HaruException {
        public InvalidCommandException() {
            super("I don't recognize that command :(");
        }
    }

    public static class NumberFormatException extends HaruException {
        public NumberFormatException() {
            super("A number was not entered! :(");
        }
    }

    public static class NoTasksException extends HaruException {
        public NoTasksException() {
            super("There are no tasks!");
        }
    }

    public static class InvalidIndexException extends HaruException {
        public InvalidIndexException() {
            super("Number entered was invalid!");
        }
    }

    public static class MarkException extends HaruException {
        public MarkException() {
            super("Task is already marked!");
        }
    }

    public static class UnmarkException extends HaruException {
        public UnmarkException() {
            super("Task is already unmarked!");
        }
    }

    public static class InvalidTodoException extends HaruException {
        public InvalidTodoException() {
            super("The task was not entered correctly!\n"
                    + "Try doing \"todo <task>\"");
        }
    }

    public static class InvalidDeadlineException extends HaruException {
        public InvalidDeadlineException() {
            super("The task was not entered correctly!\n"
                    + "Try doing \"deadline <task> /by <date/time>\"");
        }
    }

    public static class InvalidEventException extends HaruException {
        public InvalidEventException() {
            super("The task was not entered correctly!\n"
                    + "Try doing \"event <task> /from <date/time> /to <date/time>\"");
        }
    }
}