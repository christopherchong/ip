public class HaruException extends Exception {
    public HaruException(String message) {
        super(message);
    }

    public static class InvalidCommandException extends HaruException {
        private static final String message =
        """
        ____________________________________________________________
        I don't recognize that command :(
        ____________________________________________________________
        """;

        public InvalidCommandException() {
            super(message);
        }
    }

    public static class NoTasksException extends HaruException {
        private static final String message =
        """
        ____________________________________________________________
        There are no tasks!
        ____________________________________________________________
        """;

        public NoTasksException() {
            super(message);
        }
    }

    public static class InvalidIndexException extends HaruException {
        private static final String message =
        """
        ____________________________________________________________
        Number entered was invalid!
        ____________________________________________________________
        """;

        public InvalidIndexException() {
            super(message);
        }
    }

    public static class MarkException extends HaruException {
        private static final String message =
        """
        ____________________________________________________________
        Task is already marked!
        ____________________________________________________________
        """;

        public MarkException() {
            super(message);
        }
    }

    public static class UnmarkException extends HaruException {
        private static final String message =
        """
        ____________________________________________________________
        Task is already unmarked!
        ____________________________________________________________
        """;

        public UnmarkException() {
            super(message);
        }
    }
}
