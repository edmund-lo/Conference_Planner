package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
    private static final DateTimeUtil Instance = new DateTimeUtil();

    public static DateTimeUtil getInstance() { return Instance; }

    private DateTimeUtil() {}

    /** The date pattern that is used for conversion. Change as you wish. */
    private static final String DATETIME_PATTERN = "dd.MM.yyyy";
    /** The date formatter. */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    public String format(LocalDateTime dateTime) {
        if (dateTime == null)
            return null;
        return DATE_FORMATTER.format(dateTime);
    }

    /**
     * Converts a String in the format of the defined {@link DateTimeUtil#DATETIME_PATTERN}
     * to a {@link LocalDateTime} object.
     *
     * Returns null if the String could not be converted.
     *
     * @param dateTimeString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDateTime parse(String dateTimeString) {
        try {
            return DATE_FORMATTER.parse(dateTimeString, LocalDateTime::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param dateTimeString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateTimeString) {
        // Try to parse the String.
        return DateTimeUtil.parse(dateTimeString) != null;
    }
}
