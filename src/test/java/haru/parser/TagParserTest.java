package haru.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import haru.HaruException;
import haru.command.Command;
import haru.command.TagCommand;
import haru.command.UntagCommand;

public class TagParserTest {
    @Test
    void parseTagCommand_returnsTagCommand() throws HaruException {
        Command cmd = Parser.parse("tag 1 #test");
        assertInstanceOf(TagCommand.class, cmd);
    }

    @Test
    void parseTagWithNoArguments_throwsHaruException() {
        assertThrows(HaruException.NoTagException.class, () ->
                Parser.parse("tag"));
    }

    @Test
    void parseTagWithNoIndex_throwsHaruException() {
        assertThrows(HaruException.NoTagException.class, () ->
                Parser.parse("tag #test"));
    }

    @Test
    void parseTagWithInvalidIndex_throwsHaruException() {
        assertThrows(HaruException.InvalidTagFormatException.class, () ->
                Parser.parse("tag e #test"));
    }

    @Test
    void parseTagWithNoTag_throwsHaruException() {
        assertThrows(HaruException.NoTagException.class, () ->
                Parser.parse("tag 1"));
    }

    @Test
    void parseTagWithInvalidTag_throwsHaruException() {
        assertThrows(HaruException.InvalidTagFormatException.class, () ->
                Parser.parse("tag 1 test"));
    }

    // untag
    @Test
    void parseUntagCommand_returnsUntagCommand() throws HaruException {
        Command cmd = Parser.parse("untag 1 #test");
        assertInstanceOf(UntagCommand.class, cmd);
    }

    @Test
    void parseUntagWithNoArguments_throwsHaruException() {
        assertThrows(HaruException.NoTagException.class, () ->
                Parser.parse("untag"));
    }

    @Test
    void parseUntagWithNoIndex_throwsHaruException() {
        assertThrows(HaruException.NoTagException.class, () ->
                Parser.parse("untag #test"));
    }

    @Test
    void parseUntagWithInvalidIndex_throwsHaruException() {
        assertThrows(HaruException.InvalidTagFormatException.class, () ->
                Parser.parse("untag e #test"));
    }

    @Test
    void parseUntagWithNoTag_throwsHaruException() {
        assertThrows(HaruException.NoTagException.class, () ->
                Parser.parse("untag 1"));
    }

    @Test
    void parseUntagWithInvalidTag_throwsHaruException() {
        assertThrows(HaruException.InvalidTagFormatException.class, () ->
                Parser.parse("untag 1 test"));
    }
}
