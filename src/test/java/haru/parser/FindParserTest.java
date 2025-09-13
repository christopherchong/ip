package haru.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.command.Command;
import haru.command.FindCommand;

public class FindParserTest {
    @Test
    void parseFindCommand_returnsFindCommand() throws HaruException {
        Command cmd = Parser.parse("find book");
        assertInstanceOf(FindCommand.class, cmd);
    }

    @Test
    void parseFindWithoutKeyword_throwsHaruException() {
        assertThrows(HaruException.InvalidFindException.class, () ->
                Parser.parse("find"));
    }
}
