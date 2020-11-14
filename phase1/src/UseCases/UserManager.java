package UseCases;

import Entities.Attendee;
import Entities.Organizer;
import Entities.Speaker;
import Entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManager implements Serializable {
    private HashMap<String, User> allUsers;

    public UserManager(HashMap<String, User> allUsers) {
        this.allUsers = allUsers;
    }

    public UserManager() {
        this.allUsers = new HashMap<>();
    }

    public HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public void createNewAttendee(String username, String password) {
        Attendee attendee = new Attendee(username, password);
        allUsers.put(username, attendee);
    }

    public void createNewOrganizer(String username, String password) {
        Organizer organizer = new Organizer(username, password);
        allUsers.put(username, organizer);
    }

    public void createNewSpeaker(String username, String password) {
        Speaker speaker = new Speaker(username, password);
        allUsers.put(username, speaker);
    }

    public boolean canSignUp(String username, String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        return allUsers.get(username).canSignUp(eventID, startTime, endTime);
    }

    public void signUp(String username, String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        allUsers.get(username).signUp(eventID, startTime, endTime);
    }

    public void cancel(String username, String eventID) {
        allUsers.get(username).cancel(eventID);
    }

    public boolean canAddSpeakerEvent(String username, String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        Speaker speaker = (Speaker) allUsers.get(username);
        return speaker.canAddSpeakerEvent(eventID, startTime, endTime);
    }

    public void addSpeakerEvent(String username, String eventID, LocalDateTime startTime, LocalDateTime endTime) {
        Speaker speaker = (Speaker) allUsers.get(username);
        speaker.addSpeakerEvent(eventID, startTime, endTime);
    }

    public void cancelSpeakerEvent(String username, String eventID) {
        Speaker speaker = (Speaker) allUsers.get(username);
        speaker.cancelSpeakerEvent(eventID);
    }

    public void addMessageToUser(String username, String messageID) {
        allUsers.get(username).addMessageID(messageID);
    }

    public boolean checkUniqueUsername(String username) {
        for (User user : allUsers.values()) {
            if (username.equals(user.getUsername())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkLogin(String username, String password) {
        for (User user : allUsers.values()) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, LocalDateTime[]> getSchedule(String username) {
        return allUsers.get(username).getSchedule();
    }

    public HashMap<String, LocalDateTime[]> getSpeakerSchedule(String username) {
        Speaker speaker = (Speaker) allUsers.get(username);
        return speaker.getSpeakerSchedule();
    }

    public User getUserByUsername(String username) {
        return allUsers.get(username);
    }

    public List<String> getAllSpeakerNames() {
        List<String> speakers = new ArrayList<>();
        for (User user : allUsers.values()) {
           if (user instanceof Speaker) {
               speakers.add(user.getUsername());
           }
        }
        return speakers;
    }

    public ArrayList<String[]> getAccountInfo() {
        ArrayList<String[]> accountInfo = new ArrayList<>();
        for (User user : allUsers.values()) {
            String[] info = {user.getUsername(), user.getPassword(), user.getClass().getSimpleName()};
            accountInfo.add(info);
        }
        return accountInfo;
    }

    public boolean isAttendee(String username) {
        return allUsers.get(username) instanceof Attendee;
    }

    public boolean isOrganizer(String username) {
        return allUsers.get(username) instanceof Organizer;
    }

    public boolean isSpeaker(String username) {
        return allUsers.get(username) instanceof Speaker;
    }

    public ArrayList<String> getAllMessageableUsers(String username) {
        ArrayList<String> usernames = new ArrayList<>();
        for (User user: allUsers.values()){
            if (user instanceof Speaker || user instanceof Organizer && user.getUsername().equals(username))
                usernames.add(user.toString());
        }

        return usernames;
    }

}
