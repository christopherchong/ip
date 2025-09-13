package haru.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.command.Command;
import haru.command.ExitCommand;

public class ByeParserTest {
    @Test
    void parseExitCommand_returnsExitCommand() throws HaruException {
        Command cmd = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, cmd);
    }

    @Test
    void parseExitWithArguments_throwsHaruException() {
        assertThrows(HaruException.InvalidByeException.class, () ->
                Parser.parse("bye bye"));
    }
}
