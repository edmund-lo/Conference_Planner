package home.impl;

import adapter.MessageThreadAdapter;
import adapter.ScheduleAdapter;
import adapter.UserAdapter;
import controllers.AttendeeController;
import home.IHomePresenter;
import home.IHomeView;
import model.MessageThread;
import model.ScheduleEntry;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class HomePresenter implements IHomePresenter {
    private IHomeView view;
    private AttendeeController ac;

    public HomePresenter(IHomeView view) {
        this.view = view;
        this.ac = new AttendeeController(this.view.getSessionUsername());
        init();
    }

    @Override
    public void setGreeting() {
        //JSONObject responseJson = ac.getUser();
        JSONObject responseJson = new JSONObject();
        User user = UserAdapter.getInstance().adaptData((JSONArray) responseJson.get("data")).get(0);
        this.view.setTitle("Welcome " + user.getFirstName() + "!");
    }

    @Override
    public void setUnreadMessages() {
        //JSONObject responseJson = ac.getUnreadMessages();
        JSONObject responseJson = new JSONObject();
        List<MessageThread> unreadMessages = MessageThreadAdapter.getInstance()
                .adaptData((JSONArray) responseJson.get("data"));
        String result = "You have " + unreadMessages.size() + " unread messages.";
        if (unreadMessages.size() == 1) result = "You have 1 unread message.";
        this.view.setUnreadButtonText(result);
    }

    @Override
    public void setAttendingEvents() {
        //JSONObject responseJson = ac.getNextDayEvents();
        JSONObject responseJson = new JSONObject();
        List<ScheduleEntry> attendingEvents = ScheduleAdapter.getInstance()
                .adaptData((JSONArray) responseJson.get("data"));
        String result = "You are attending " + attendingEvents.size() + " events during the next 24 hours.";
        if (attendingEvents.size() == 1) result = "You are attending 1 event during the next 24 hours.";
        this.view.setViewScheduleButtonText(result);
    }

    @Override
    public void init() {
        setGreeting();
        setUnreadMessages();
        setAttendingEvents();
    }
}
