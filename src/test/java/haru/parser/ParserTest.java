package haru.parser;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;
import haru.command.DeleteCommand;
import haru.command.ExitCommand;
import haru.command.ListCommand;
import haru.command.MarkCommand;
import haru.command.UnmarkCommand;
import haru.task.Deadline;
import haru.task.Event;
import haru.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    // list
    @Test
    void parseListCommand_returnsListCommand() throws HaruException {
        Command cmd = Parser.parse("list");
        assertTrue(cmd instanceof ListCommand);
    }

    // mark
    @Test
    void parseMarkCommand_returnsMarkCommand() throws HaruException {
        Command cmd = Parser.parse("mark 1");
        assertTrue(cmd instanceof MarkCommand);
    }

    @Test
    void parseMarkWithoutNumber_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () -> {
            Parser.parse("mark");
        });
    }

    @Test
    void parseMarkWithoutNumber2_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () -> {
            Parser.parse("mark e");
        });
    }

    // unmark
    @Test
    void parseUnmarkCommand_returnsUnmarkCommand() throws HaruException {
        Command cmd = Parser.parse("unmark 2");
        assertTrue(cmd instanceof UnmarkCommand);
    }

    @Test
    void parseUnmarkWithoutNumber_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () -> {
            Parser.parse("unmark");
        });
    }

    @Test
    void parseUnmarkWithoutNumber2_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () -> {
            Parser.parse("unmark e");
        });
    }

    // to-do
    @Test
    void parseTodoCommand_returnsAddCommand() throws HaruException {
        Command cmd = Parser.parse("todo read book");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void parseTodoWithoutDescription_throwsHaruException() {
        assertThrows(HaruException.InvalidTodoException.class, () -> {
            Parser.parse("todo");
        });
    }

    // deadline
    @Test
    void parseDeadlineCommand_returnsAddCommand() throws HaruException {
        Command cmd = Parser.parse("deadline return book /by 2/12/2019 1800");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void parseDeadlineWithoutBy_throwsHaruException() {
        assertThrows(HaruException.InvalidDeadlineException.class, () -> {
            Parser.parse("deadline return book 2/12/2019 1800");
        });
    }

    @Test
    void parseDeadlineWithInvalidDate_throwsHaruException() {
        assertThrows(HaruException.DateTimeParseException.class, () -> {
            Parser.parse("deadline return book /by 2/15/2019 1800");
        });
    }

    // event
    @Test
    void parseEventCommand_returnsAddCommand() throws HaruException {
        Command cmd = Parser.parse("event project meeting /from 2/12/2019 1400 /to 2/12/2019 1600");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void parseEventWithoutFrom_throwsHaruException() {
        assertThrows(HaruException.InvalidEventException.class, () -> {
            Parser.parse("event meeting 2/12/2019 1600 /to 2/12/2019 1400");
        });
    }

    @Test
    void parseEventWithoutTo_throwsHaruException() {
        assertThrows(HaruException.InvalidEventException.class, () -> {
            Parser.parse("event meeting /from 2/12/2019 1600 2/12/2019 1700");
        });
    }

    @Test
    void parseEventWithInvalidFrom_throwsHaruException() {
        assertThrows(HaruException.DateTimeParseException.class, () -> {
            Parser.parse("event meeting /from 2/15/2019 1600 /to 2/12/2019 1400");
        });
    }

    @Test
    void parseEventWithInvalidTo_throwsHaruException() {
        assertThrows(HaruException.DateTimeParseException.class, () -> {
            Parser.parse("event meeting /from 2/12/2019 1600 /to 2/15/2019 1400");
        });
    }

    @Test
    void parseEventWithSameDates_throwsHaruException() {
        assertThrows(HaruException.SameDateTimeException.class, () -> {
            Parser.parse("event meeting /from 2/12/2019 1600 /to 2/12/2019 1600");
        });
    }

    @Test
    void parseEventWithInvalidDateOrder_throwsHaruException() {
        assertThrows(HaruException.DateTimeOrderException.class, () -> {
            Parser.parse("event meeting /from 2/12/2019 1600 /to 2/12/2019 1400");
        });
    }

    // delete
    @Test
    void parseDeleteCommand_returnsDeleteCommand() throws HaruException {
        Command cmd = Parser.parse("delete 3");
        assertTrue(cmd instanceof DeleteCommand);
    }

    @Test
    void parseDeleteWithoutNumber_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () -> {
            Parser.parse("delete");
        });
    }

    @Test
    void parseDeleteWithoutNumber2_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () -> {
            Parser.parse("delete e");
        });
    }

    // invalid command
    @Test
    void parseInvalidCommand_throwsHaruException() {
        assertThrows(HaruException.InvalidCommandException.class, () -> {
            Parser.parse("blah");
        });
    }
}