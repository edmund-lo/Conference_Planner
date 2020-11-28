package home.impl;

import home.IHomePresenter;
import home.IHomeView;
import javafx.event.ActionEvent;
import util.ComponentFactory;

public class HomePresenter implements IHomePresenter {
    private IHomeView view;

    public HomePresenter(IHomeView view) {
        this.view = view;
        init();
    }

    @Override
    public void unreadButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "messaging.fxml");
    }

    @Override
    public void viewScheduleButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoggedInComponent(this.view.getStage(), "viewSchedule.fxml");
    }

    @Override
    public void setGreeting(String greeting) {
        this.view.setTitle(greeting);
    }

    @Override
    public void setUnreadMessages(String unreadMessages) {
        this.view.setUnreadButtonText(unreadMessages);
    }

    @Override
    public void setAttendingEvents(String attendingEvents) {
        this.view.setViewScheduleButtonText(attendingEvents);
    }

    @Override
    public void init() {
        this.view.setUnreadButtonAction(this::unreadButtonAction);
        this.view.setViewScheduleButtonAction(this::viewScheduleButtonAction);
        setGreeting("Hello " + "!"); //call ac.getName method
        setUnreadMessages("You have " + 0 + " unread messages"); //call ac.getUnreadMessages method
        setAttendingEvents("You are attending " + 0 + " events today"); //call ac.getNearAttendingEvents
    }
}
