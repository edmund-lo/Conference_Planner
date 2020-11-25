package model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class ScheduleEntry {
    private final ObjectProperty<LocalDateTime> start;
    private final ObjectProperty<LocalDateTime> end;
    private final StringProperty title;
    private final StringProperty description;
    private final StringProperty otherAttendees;
    private final BooleanProperty liked;

    public ScheduleEntry() {
        this(null, null, null, null, null, false);
    }

    public ScheduleEntry(LocalDateTime start, LocalDateTime end, String title, String description,
                         String otherAttendees, boolean liked) {
        this.start = new SimpleObjectProperty<>(start);
        this.end = new SimpleObjectProperty<>(end);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.otherAttendees = new SimpleStringProperty(otherAttendees);
        this.liked = new SimpleBooleanProperty(liked);
    }

    public LocalDateTime getStart() {
        return start.get();
    }

    public ObjectProperty<LocalDateTime> startProperty() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start.set(start);
    }

    public LocalDateTime getEnd() {
        return end.get();
    }

    public ObjectProperty<LocalDateTime> endProperty() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end.set(end);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getOtherAttendees() {
        return otherAttendees.get();
    }

    public StringProperty otherAttendeesProperty() {
        return otherAttendees;
    }

    public void setOtherAttendees(String otherAttendees) {
        this.otherAttendees.set(otherAttendees);
    }

    public boolean isLiked() {
        return liked.get();
    }

    public BooleanProperty likedProperty() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked.set(liked);
    }
}
