package model;

import javafx.beans.property.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class ScheduleEntry {
    private final ObjectProperty<LocalDateTime> start;
    private final ObjectProperty<LocalDateTime> end;
    private final StringProperty eventName;
    private final StringProperty roomName;
    private final StringProperty amenities;
    private final StringProperty attendees;
    private final StringProperty speakers;
    private final ObjectProperty<Duration> duration;
    private final IntegerProperty remainingSpots;
    private final IntegerProperty capacity;
    private final BooleanProperty liked;
    private final BooleanProperty vip;

    public ScheduleEntry() {
        this(null, null, null, null, null, null, null, null, 0, 0, false, false);
    }

    public ScheduleEntry(LocalDateTime start, LocalDateTime end, String eventName, String roomName, String amenities,
                         String attendees, String speakers, Duration duration, int remainingSpots, int capacity,
                         boolean liked, boolean vip) {
        this.start = new SimpleObjectProperty<>(start);
        this.end = new SimpleObjectProperty<>(end);
        this.eventName = new SimpleStringProperty(eventName);
        this.roomName = new SimpleStringProperty(roomName);
        this.amenities = new SimpleStringProperty(amenities);
        this.attendees = new SimpleStringProperty(attendees);
        this.speakers = new SimpleStringProperty(speakers);
        this.duration = new SimpleObjectProperty<>(duration);
        this.remainingSpots = new SimpleIntegerProperty(remainingSpots);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.liked = new SimpleBooleanProperty(liked);
        this.vip = new SimpleBooleanProperty(vip);
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

    public String getEventName() {
        return eventName.get();
    }

    public StringProperty eventNameProperty() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName.set(eventName);
    }

    public String getRoomName() {
        return roomName.get();
    }

    public StringProperty roomNameProperty() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName.set(roomName);
    }

    public String getAmenities() {
        return amenities.get();
    }

    public StringProperty amenitiesProperty() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities.set(amenities);
    }

    public String getAttendees() {
        return attendees.get();
    }

    public StringProperty attendeesProperty() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees.set(attendees);
    }

    public Duration getDuration() {
        return duration.get();
    }

    public ObjectProperty<Duration> durationProperty() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration.set(duration);
    }

    public int getRemainingSpots() {
        return remainingSpots.get();
    }

    public IntegerProperty remainingSpotsProperty() {
        return remainingSpots;
    }

    public void setRemainingSpots(int remainingSpots) {
        this.remainingSpots.set(remainingSpots);
    }

    public String getSpeakers() {
        return speakers.get();
    }

    public StringProperty speakersProperty() {
        return speakers;
    }

    public void setSpeakers(String speakers) {
        this.speakers.set(speakers);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
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

    public boolean isVip() {
        return vip.get();
    }

    public BooleanProperty vipProperty() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip.set(vip);
    }
}
