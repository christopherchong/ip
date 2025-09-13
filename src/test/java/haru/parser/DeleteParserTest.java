package haru.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.command.Command;
import haru.command.DeleteCommand;

public class DeleteParserTest {
    @Test
    void parseDeleteCommand_returnsDeleteCommand() throws HaruException {
        Command cmd = Parser.parse("delete 3");
        assertInstanceOf(DeleteCommand.class, cmd);
    }

    @Test
    void parseDeleteWithoutNumber_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () ->
                Parser.parse("delete"));
    }

    @Test
    void parseDeleteWithoutNumber2_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () ->
                Parser.parse("delete e"));
    }

}
