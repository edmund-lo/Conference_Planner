package util;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import model.LoginLog;
import model.MessageThread;
import model.ScheduleEntry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
    private static final DateTimeUtil Instance = new DateTimeUtil();

    public static DateTimeUtil getInstance() { return Instance; }

    private DateTimeUtil() {}

    /** The date pattern that is used for conversion. Change as you wish. */
    private static final String DATETIME_PATTERN = "dd.MMM.yyyy HH:mm";
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
    public LocalDateTime parse(String dateTimeString) {
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
    public boolean validDate(String dateTimeString) {
        // Try to parse the String.
        return parse(dateTimeString) != null;
    }

    public String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        return String.format("%d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60);
    }

    public void setScheduleDateTimeCellFactory(TableColumn<ScheduleEntry, LocalDateTime> column) {
        column.setCellFactory(c -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty)
                    setText(null);
                else
                    this.setText(format(dateTime));
            }
        });
    }

    public void setMessageDateTimeCellFactory(TableColumn<MessageThread, LocalDateTime> column) {
        column.setCellFactory(c -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty)
                    setText(null);
                else
                    this.setText(format(dateTime));
            }
        });
    }

    public void setLoginDateTimeCellFactory(TableColumn<LoginLog, LocalDateTime> column) {
        column.setCellFactory(c -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean empty) {
                super.updateItem(dateTime, empty);
                if (empty)
                    setText(null);
                else
                    this.setText(format(dateTime));
            }
        });
    }
}
