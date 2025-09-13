package haru.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;

public class EventParserTest {
    @Test
    void parseEventCommand_returnsAddCommand() throws HaruException {
        Command cmd = Parser.parse("event project meeting /from 2/12/2019 1400 /to 2/12/2019 1600");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void parseEventWithoutFrom_throwsHaruException() {
        assertThrows(HaruException.InvalidEventException.class, () ->
                Parser.parse("event meeting 2/12/2019 1600 /to 2/12/2019 1400"));
    }

    @Test
    void parseEventWithoutTo_throwsHaruException() {
        assertThrows(HaruException.InvalidEventException.class, () ->
                Parser.parse("event meeting /from 2/12/2019 1600 2/12/2019 1700"));
    }

    @Test
    void parseEventWithInvalidFrom_throwsHaruException() {
        assertThrows(HaruException.DateTimeParseException.class, () ->
                Parser.parse("event meeting /from 2/15/2019 1600 /to 2/12/2019 1400"));
    }

    @Test
    void parseEventWithInvalidTo_throwsHaruException() {
        assertThrows(HaruException.DateTimeParseException.class, () ->
                Parser.parse("event meeting /from 2/12/2019 1600 /to 2/15/2019 1400"));
    }

    @Test
    void parseEventWithSameDates_throwsHaruException() {
        assertThrows(HaruException.SameDateTimeException.class, () ->
                Parser.parse("event meeting /from 2/12/2019 1600 /to 2/12/2019 1600"));
    }

    @Test
    void parseEventWithInvalidDateOrder_throwsHaruException() {
        assertThrows(HaruException.DateTimeOrderException.class, () ->
                Parser.parse("event meeting /from 2/12/2019 1600 /to 2/12/2019 1400"));
    }
}
