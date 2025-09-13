package haru.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;

public class ParserTest {
    @Test
    void parseInvalidCommand_throwsHaruException() {
        assertThrows(HaruException.InvalidCommandException.class, () ->
                Parser.parse("blah"));
    }
}
