package haru.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;

public class DeadlineParserTest {
    @Test
    void parseDeadlineCommand_returnsAddCommand() throws HaruException {
        Command cmd = Parser.parse("deadline return book /by 2/12/2019 1800");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void parseDeadlineWithoutBy_throwsHaruException() {
        assertThrows(HaruException.InvalidDeadlineException.class, () ->
                Parser.parse("deadline return book 2/12/2019 1800"));
    }

    @Test
    void parseDeadlineWithInvalidDate_throwsHaruException() {
        assertThrows(HaruException.DateTimeParseException.class, () ->
                Parser.parse("deadline return book /by 2/15/2019 1800"));
    }
}
