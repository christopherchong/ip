public class HaruException extends Exception {
    public HaruException(String message) {
        super(message);
    }

    public static class InvalidCommandException extends HaruException {
        private static final String message = """
                ____________________________________________________________
                I don't recognize that command :(
                ____________________________________________________________
                """;

        public InvalidCommandException() {
            super(message);
        }
    }

    public static class NumberFormatException extends HaruException {
        private static final String message = """
                ____________________________________________________________
                A number was not entered! :(
                ____________________________________________________________
                """;

        public NumberFormatException() {
            super(message);
        }
    }

    public static class NoTasksException extends HaruException {
        private static final String message = """
                ____________________________________________________________
                There are no tasks!
                ____________________________________________________________
                """;

        public NoTasksException() {
            super(message);
        }
    }

    public static class InvalidIndexException extends HaruException {
        private static final String message = """
                ____________________________________________________________
                Number entered was invalid!
                ____________________________________________________________
                """;

        public InvalidIndexException() {
            super(message);
        }
    }

    public static class MarkException extends HaruException {
        private static final String message = """
                ____________________________________________________________
                Task is already marked!
                ____________________________________________________________
                """;

        public MarkException() {
            super(message);
        }
    }

    public static class UnmarkException extends HaruException {
        private static final String message = """
                ____________________________________________________________
                Task is already unmarked!
                ____________________________________________________________
                """;

        public UnmarkException() {
            super(message);
        }
    }

    public static class InvalidTodoException extends HaruException {
        private static final String message = """
                ____________________________________________________________
                The task was not entered correctly!
                Try doing "todo <task>"
                ____________________________________________________________
                """;

        public InvalidTodoException() {
            super(message);
        }
    }

    public static class InvalidDeadlineException extends HaruException {
        private static final String message = """
                ____________________________________________________________
                The task was not entered correctly!
                Try doing "deadline <task> /by <date/time>"
                ____________________________________________________________
                """;

        public InvalidDeadlineException() {
            super(message);
        }
    }

    public static class InvalidEventException extends HaruException {
        private static final String message = """
                ____________________________________________________________
                The task was not entered correctly!
                Try doing "event <task> /from <date/time> /to <date/time>"
                ____________________________________________________________
                """;

        public InvalidEventException() {
            super(message);
        }
    }
}
