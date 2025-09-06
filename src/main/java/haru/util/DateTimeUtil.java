package haru.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import haru.HaruException;

/**
 * Utility class for parsing and formatting {@link LocalDateTime}.
 */
public class DateTimeUtil {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/y Hmm");
    private static final DateTimeFormatter STORAGE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

    /**
     * Parses a datetime user input into a {@link LocalDateTime}.
     *
     * @param input the user input
     * @return the corresponding LocalDateTime
     * @throws HaruException.DateTimeParseException if input is invalid
     */
    public static LocalDateTime parseInput(String input) throws HaruException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new HaruException.DateTimeParseException();
        }
    }

    /**
     * Parses a datetime string from task file into a {@link LocalDateTime}.
     *
     * @param input the stored string
     * @return the corresponding LocalDateTime
     * @throws HaruException.CorruptedFileException if input is invalid
     */
    public static LocalDateTime parseStorage(String input) throws HaruException {
        try {
            return LocalDateTime.parse(input, STORAGE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new HaruException.CorruptedFileException();
        }
    }

    /**
     * Formats a {@link LocalDateTime} for storage in the task file.
     *
     * @param dateTime a LocalDateTime input
     * @return the formatted LocalDateTime
     */
    public static String formatForStorage(LocalDateTime dateTime) {
        return dateTime.format(STORAGE_FORMATTER);
    }

    /**
     * Formats a {@link LocalDateTime} for user display.
     *
     * @param dateTime a LocalDateTime input
     * @return the formatted LocalDateTime
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMATTER);
    }
}
