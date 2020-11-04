import jdk.vm.ci.meta.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private int roomID;
    private String name;
    private HashMap<LocalDateTime, String> schedule;
    private int capacity = 2;
    private ArrayList<User> occupants;
    private Speaker currentSpeaker;

    public Room(String name){
        this.name = name;
        this.schedule = new HashMap<>();
        this.occupants = new ArrayList<>();
    }

    public int getRoomID(){
        return this.roomID;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String getName() {
        return this.name;
    }

    public HashMap<LocalDateTime, String> getSchedule() {
        return this.schedule;
    }

    public ArrayList<User> getOccupants() {
        return occupants;
    }

    public void addOccupant(User person){
        this.occupants.add(person);
    }

    public Speaker getCurrentSpeaker() {
        return currentSpeaker;
    }

    public void setCurrentSpeaker(Speaker currentSpeaker) {
        this.currentSpeaker = currentSpeaker;
    }

    public boolean canAddOccupant(){
        return this.occupants.size() < this.capacity;
    }

    public boolean canBook(LocalDateTime time){
        return this.schedule.get(time) == null;
    }

    public void addEvent(Event event){
        this.schedule.put(event.getStartTime(), event.getEventName());
    }
    
}
