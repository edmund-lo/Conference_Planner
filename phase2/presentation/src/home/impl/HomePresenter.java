package home.impl;

import controllers.AttendeeController;
import home.IHomePresenter;
import home.IHomeView;
import org.json.simple.JSONObject;

public class HomePresenter implements IHomePresenter {
    private final IHomeView view;
    private final AttendeeController ac;

    public HomePresenter(IHomeView view) {
        this.view = view;
        this.ac = new AttendeeController(this.view.getSessionUsername());
        init();
    }

    @Override
    public void setGreeting() {
        //JSONObject responseJson = ac.getUser();
        JSONObject responseJson = new JSONObject();
        this.view.setTitle(String.valueOf(responseJson.get("result")));
    }

    @Override
    public void setUnreadMessages() {
        //JSONObject responseJson = ac.getUnreadMessages();
        JSONObject responseJson = new JSONObject();
        this.view.setUnreadButtonText(String.valueOf(responseJson.get("result")));
    }

    @Override
    public void setAttendingEvents() {
        //JSONObject responseJson = ac.getNextDayEvents();
        JSONObject responseJson = new JSONObject();
        this.view.setViewScheduleButtonText(String.valueOf(responseJson.get("result")));
    }

    @Override
    public void init() {
        setGreeting();
        setUnreadMessages();
        setAttendingEvents();
    }
}
