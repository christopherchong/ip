package haru.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.command.AddCommand;
import haru.command.Command;

public class TodoParserTest {
    @Test
    void parseTodoCommand_returnsAddCommand() throws HaruException {
        Command cmd = Parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void parseTodoWithoutDescription_throwsHaruException() {
        assertThrows(HaruException.InvalidTodoException.class, () ->
                Parser.parse("todo"));
    }
}
