package attendee.impl;

import adapter.ScheduleAdapter;
import adapter.UserAdapter;
import attendee.IFriendsPresenter;
import attendee.IFriendsView;
import controllers.AttendeeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.ScheduleEntry;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DateTimeUtil;
import util.TextResultUtil;

import java.time.LocalDateTime;
import java.util.List;

public class FriendsPresenter implements IFriendsPresenter {
    private IFriendsView view;
    private AttendeeController ac;
    private User selectedFriend;
    private User selectedUser;

    public FriendsPresenter(IFriendsView view) {
        this.view = view;
        this.ac = new AttendeeController(this.view.getSessionUsername());
        init();
    }

    @Override
    public void removeFriendButtonAction(ActionEvent actionEvent) {
        clearResultText(1);

        JSONObject responseJson = ac.removeFriend(selectedFriend.getUsername());
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 1);
    }

    @Override
    public void addFriendButtonAction(ActionEvent actionEvent) {
        clearResultText(2);

        JSONObject responseJson = ac.addFriend(selectedUser.getUsername());
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 2);
    }

    @Override
    public List<User> getUsers(String type) {
        JSONObject responseJson = new JSONObject();
        /*if (type.equals("pending")) responseJson = ac.getAllUsers();
        else responseJson = ac.getAllFriends();*/
        return UserAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));
    }

    @Override
    public List<ScheduleEntry> getCommonEvents(String username) {
        //JSONObject responseJson = ac.getCommonEvents(username);
        JSONObject responseJson = new JSONObject();
        return ScheduleAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));
    }

    @Override
    public void displayUserList(List<User> userList, boolean friend) {
        ObservableList<User> observableUsers = FXCollections.observableArrayList(userList);
        if (friend) {
            this.view.getFirstNameFriendColumn().setCellValueFactory(new PropertyValueFactory<>("firstName"));
            this.view.getLastNameFriendColumn().setCellValueFactory(new PropertyValueFactory<>("lastName"));
            this.view.getUsernameFriendColumn().setCellValueFactory(new PropertyValueFactory<>("username"));
            this.view.getUserTypeFriendColumn().setCellValueFactory(new PropertyValueFactory<>("userType"));
            this.view.getFriendTable().setItems(observableUsers);
            this.view.getFriendTable().getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> displayUserDetails(newValue, true));
        } else {
            this.view.getFirstNameFriendColumn().setCellValueFactory(new PropertyValueFactory<>("firstName"));
            this.view.getLastNameFriendColumn().setCellValueFactory(new PropertyValueFactory<>("lastName"));
            this.view.getUsernameFriendColumn().setCellValueFactory(new PropertyValueFactory<>("username"));
            this.view.getUserTypeFriendColumn().setCellValueFactory(new PropertyValueFactory<>("userType"));
            this.view.getPendingUserColumn().setCellValueFactory(param -> param.getValue().getSelected());
            this.view.getUserTable().setItems(observableUsers);
            this.view.getUserTable().getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> displayUserDetails(newValue, false));
        }
    }

    @Override
    public void displayUserDetails(User user, boolean friend) {
        if (friend) {
            this.selectedFriend = user;
            this.view.setUsernameFriend(user.getUsername());
            this.view.setUserTypeFriend(user.getUserType());
            this.view.setFirstNameFriend(user.getFirstName());
            this.view.setLastNameFriend(user.getLastName());
            List<ScheduleEntry> commonEvents = getCommonEvents(user.getUsername());
            displayCommonEvents(commonEvents);
        } else {
            this.selectedUser = user;
            this.view.setUsernameUser(user.getUsername());
            this.view.setUserTypeUser(user.getUserType());
            this.view.setFirstNameUser(user.getFirstName());
            this.view.setLastNameUser(user.getLastName());
        }
    }

    @Override
    public void displayCommonEvents(List<ScheduleEntry> commonEvents) {
        Text tableHeader = new Text("Room Schedule");
        TableView<ScheduleEntry> scheduleTable = new TableView<>();

        TableColumn<ScheduleEntry, String> column1 = new TableColumn<>("Event Name");
        TableColumn<ScheduleEntry, LocalDateTime> column2 = new TableColumn<>("Start");
        TableColumn<ScheduleEntry, LocalDateTime> column3 = new TableColumn<>("End");
        TableColumn<ScheduleEntry, Integer> column4 = new TableColumn<>("Remaining Spots");
        DateTimeUtil.getInstance().setScheduleDateTimeCellFactory(column2);
        DateTimeUtil.getInstance().setScheduleDateTimeCellFactory(column3);
        column1.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        column2.setCellValueFactory(new PropertyValueFactory<>("start"));
        column3.setCellValueFactory(new PropertyValueFactory<>("end"));
        column3.setCellValueFactory(new PropertyValueFactory<>("remainingSpots"));
        scheduleTable.getColumns().add(column1);
        scheduleTable.getColumns().add(column2);
        scheduleTable.getColumns().add(column3);
        scheduleTable.getColumns().add(column4);

        ObservableList<ScheduleEntry> observableSchedule = FXCollections.observableArrayList(commonEvents);
        scheduleTable.setItems(observableSchedule);

        this.view.getCommonEventTableContainer().getChildren().add(tableHeader);
        this.view.getCommonEventTableContainer().getChildren().add(scheduleTable);
    }

    @Override
    public void setResultText(String resultText, String status, int index) {
        this.view.setResultText(resultText, index);
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getResultTextControl(index));
    }

    @Override
    public void init() {
        this.view.setAddFriendButtonAction(this::addFriendButtonAction);
        this.view.setRemoveFriendButtonAction(this::removeFriendButtonAction);
        List<User> pending = getUsers("pending");
        for (User user : pending)
            user.setChecked(true);
        List<User> nonFriends = getUsers("nonFriends");
        List<User> friends = getUsers("friends");
        nonFriends.addAll(pending);
        displayUserList(nonFriends, false);
        displayUserList(friends, true);
    }

    private void clearResultText(int index) {
        this.view.setResultText("", index);
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl(index));
    }
}
