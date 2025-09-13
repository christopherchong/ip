package haru.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.command.Command;
import haru.command.MarkCommand;
import haru.command.UnmarkCommand;

public class MarkParserTest {
    // mark
    @Test
    void parseMarkCommand_returnsMarkCommand() throws HaruException {
        Command cmd = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, cmd);
    }

    @Test
    void parseMarkWithoutNumber_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () ->
                Parser.parse("mark"));
    }

    @Test
    void parseMarkWithoutNumber2_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () ->
                Parser.parse("mark e"));
    }

    // unmark
    @Test
    void parseUnmarkCommand_returnsUnmarkCommand() throws HaruException {
        Command cmd = Parser.parse("unmark 2");
        assertInstanceOf(UnmarkCommand.class, cmd);
    }

    @Test
    void parseUnmarkWithoutNumber_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () ->
                Parser.parse("unmark"));
    }

    @Test
    void parseUnmarkWithoutNumber2_throwsHaruException() {
        assertThrows(HaruException.NumberFormatException.class, () ->
                Parser.parse("unmark e"));
    }
}
