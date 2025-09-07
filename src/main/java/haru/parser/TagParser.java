package haru.parser;

import haru.HaruException;
import haru.command.Command;
import haru.command.TagCommand;
import haru.command.UntagCommand;

/**
 * Parses {@code Tag} input and converts it into an {@link TagCommand}.
 */
public class TagParser {
    /**
     * Parses {@code Tag} input and converts it into an {@link TagCommand}.
     *
     * @param arguments The {@code Tag} input to be parsed.
     * @return the corresponding {@code TagCommand} object.
     * @throws HaruException If the input is invalid.
     */
    public static Command parse(String arguments, String command) throws HaruException {
        checkForEmptyArguments(arguments);
        String[] argumentArray = arguments.split(" ");
        validateFormat(argumentArray[0], argumentArray[1]);
        int index = Integer.parseInt(argumentArray[0]) - 1;
        String tag = argumentArray[1];
        if (command.equals("tag")) {
            return new TagCommand(index, tag);
        }
        return new UntagCommand(index, tag);
    }

    private static void checkForEmptyArguments(String arguments) throws HaruException {
        if (!arguments.contains(" ")) {
            throw new HaruException.NoTagException();
        }
    }

    private static void validateFormat(String index, String tag) throws HaruException {
        if (index.isEmpty() || tag.isEmpty()) {
            throw new HaruException.InvalidTagFormatException();
        }

        boolean isInvalidIndex = !index.matches("\\d+");
        boolean hasNoHashtag = !tag.startsWith("#");
        if (isInvalidIndex || hasNoHashtag) {
            throw new HaruException.InvalidTagFormatException();
        }
    }
}
