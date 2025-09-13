package haru.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.command.Command;
import haru.command.ListCommand;

public class ListParserTest {
    @Test
    void parseListCommand_returnsListCommand() throws HaruException {
        Command cmd = Parser.parse("list");
        assertInstanceOf(ListCommand.class, cmd);
    }

    @Test
    void parseListWithArguments_throwsHaruException() {
        assertThrows(HaruException.InvalidListException.class, () ->
                Parser.parse("list test"));
    }
}
