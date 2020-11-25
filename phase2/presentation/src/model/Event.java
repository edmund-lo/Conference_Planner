package model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Event {
    private final StringProperty eventName;
    private final StringProperty eventDesc;
    private final StringProperty speakers;
    private final StringProperty organizerName;
    private final StringProperty roomName;
    private final ObjectProperty<LocalDateTime> start;
    private final ObjectProperty<LocalDateTime> end;
    private final StringProperty duration;
    private final IntegerProperty remainingSpots;

    public Event() {
        this(null, null, null, null, null, null, null, null, 0);
    }

    public Event(String eventName, String eventDesc, String speakers, String organizerName, String roomName,
                 LocalDateTime start, LocalDateTime end, String duration, int remainingSpots) {
        this.eventName = new SimpleStringProperty(eventName);
        this.eventDesc = new SimpleStringProperty(eventDesc);
        this.speakers = new SimpleStringProperty(speakers);
        this.organizerName = new SimpleStringProperty(organizerName);
        this.roomName = new SimpleStringProperty(roomName);
        this.start = new SimpleObjectProperty<>(start);
        this.end = new SimpleObjectProperty<>(end);
        this.duration = new SimpleStringProperty(duration);
        this.remainingSpots = new SimpleIntegerProperty(remainingSpots);
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

    public String getEventDesc() {
        return eventDesc.get();
    }

    public StringProperty eventDescProperty() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc.set(eventDesc);
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

    public String getOrganizerName() {
        return organizerName.get();
    }

    public StringProperty organizerNameProperty() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName.set(organizerName);
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

    public String getDuration() {
        return duration.get();
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
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
}
