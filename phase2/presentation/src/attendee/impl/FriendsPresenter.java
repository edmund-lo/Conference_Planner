package attendee.impl;

import adapter.ScheduleAdapter;
import adapter.UserAdapter;
import attendee.IFriendsPresenter;
import attendee.IFriendsView;
import common.UserAccountHolder;
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
import model.UserAccount;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DateTimeUtil;
import util.TextResultUtil;

import java.time.LocalDateTime;
import java.util.List;

public class FriendsPresenter implements IFriendsPresenter {
    private final IFriendsView view;
    private final AttendeeController ac;
    private User selectedFriend;
    private User selectedUser;
    private User selectedPending;

    public FriendsPresenter(IFriendsView view) {
        this.view = view;
        getUserData();
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
    public void removeRequestButtonAction(ActionEvent actionEvent) {
        clearResultText(2);

        JSONObject responseJson = ac.removeFriendRequest(selectedUser.getUsername());
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 1);
    }

    @Override
    public void addFriendButtonAction(ActionEvent actionEvent) {
        clearResultText(2);

        JSONObject responseJson = ac.sendFriendRequest(selectedUser.getUsername());
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 2);
    }

    @Override
    public void acceptButtonAction(ActionEvent actionEvent) {
        clearResultText(3);

        JSONObject responseJson = ac.addFriend(selectedPending.getUsername());
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 1);
    }

    @Override
    public void declineButtonAction(ActionEvent actionEvent) {
        clearResultText(3);

        JSONObject responseJson = ac.declineRequest(selectedPending.getUsername());
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")), 1);
    }

    @Override
    public List<User> getUsers(String type) {
        JSONObject responseJson = new JSONObject();
        switch (type) {
            case "nonFriends":
                responseJson = ac.getNonFriends();
                break;
            case "friends":
                responseJson = ac.getFriends();
                break;
            case "sent":
                responseJson = ac.getSentRequests();
                break;
            case "received":
                responseJson = ac.getFriendRequests();
                break;
        }
        return UserAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));
    }

    @Override
    public List<ScheduleEntry> getCommonEvents(String username) {
        JSONObject responseJson = ac.getCommonEvents(username);
        return ScheduleAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));
    }

    @Override
    public void displayUserList(List<User> userList, String type) {
        ObservableList<User> observableUsers = FXCollections.observableArrayList(userList);
        switch (type) {
            case "friends":
                this.view.getFirstNameFriendColumn().setCellValueFactory(new PropertyValueFactory<>("firstName"));
                this.view.getLastNameFriendColumn().setCellValueFactory(new PropertyValueFactory<>("lastName"));
                this.view.getUsernameFriendColumn().setCellValueFactory(new PropertyValueFactory<>("username"));
                this.view.getUserTypeFriendColumn().setCellValueFactory(new PropertyValueFactory<>("userType"));
                this.view.getFriendTable().setItems(observableUsers);
                this.view.getFriendTable().getSelectionModel().selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> displayUserDetails(newValue, type));
                break;
            case "nonFriends":
                this.view.getFirstNameUserColumn().setCellValueFactory(new PropertyValueFactory<>("firstName"));
                this.view.getLastNameUserColumn().setCellValueFactory(new PropertyValueFactory<>("lastName"));
                this.view.getUsernameUserColumn().setCellValueFactory(new PropertyValueFactory<>("username"));
                this.view.getUserTypeUserColumn().setCellValueFactory(new PropertyValueFactory<>("userType"));
                this.view.getPendingUserColumn().setCellValueFactory(param -> param.getValue().getSelected());
                this.view.getUserTable().setItems(observableUsers);
                this.view.getUserTable().getSelectionModel().selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> displayUserDetails(newValue, type));
                break;
            case "pending":
                this.view.getFirstNamePendingColumn().setCellValueFactory(new PropertyValueFactory<>("firstName"));
                this.view.getLastNamePendingColumn().setCellValueFactory(new PropertyValueFactory<>("lastName"));
                this.view.getUsernamePendingColumn().setCellValueFactory(new PropertyValueFactory<>("username"));
                this.view.getUserTypePendingColumn().setCellValueFactory(new PropertyValueFactory<>("userType"));
                this.view.getPendingTable().setItems(observableUsers);
                this.view.getPendingTable().getSelectionModel().selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> displayUserDetails(newValue, type));
                break;
        }
    }

    @Override
    public void displayUserDetails(User user, String type) {
        switch (type) {
            case "friends":
                this.selectedFriend = user;
                this.view.setUsernameFriend(user.getUsername());
                this.view.setUserTypeFriend(user.getUserType());
                this.view.setFirstNameFriend(user.getFirstName());
                this.view.setLastNameFriend(user.getLastName());
                List<ScheduleEntry> commonEvents = getCommonEvents(user.getUsername());
                displayCommonEvents(commonEvents);
                break;
            case "nonFriends":
                this.selectedUser = user;
                this.view.setUsernameUser(user.getUsername());
                this.view.setUserTypeUser(user.getUserType());
                this.view.setFirstNameUser(user.getFirstName());
                this.view.setLastNameUser(user.getLastName());
                this.view.getRemoveRequestButton().setDisable(!user.isChecked());
                break;
            case "pending":
                this.selectedPending = user;
                this.view.setUsernamePending(user.getUsername());
                this.view.setUserTypePending(user.getUserType());
                this.view.setFirstNamePending(user.getFirstName());
                this.view.setLastNamePending(user.getLastName());
                break;
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
        this.view.setRemoveRequestButtonAction(this::removeRequestButtonAction);
        this.view.setAcceptButtonAction(this::acceptButtonAction);
        this.view.setDeclineButtonAction(this::declineButtonAction);

        List<User> pendingSent = getUsers("sent");
        for (User user : pendingSent)
            user.setChecked(true);
        List<User> nonFriends = getUsers("nonFriends");
        List<User> friends = getUsers("friends");
        List<User> pendingReceived = getUsers("received");
        pendingSent.addAll(nonFriends);
        displayUserList(pendingSent, "nonFriends");
        displayUserList(friends, "friends");
        displayUserList(pendingReceived, "pending");
    }

    @Override
    public void getUserData() {
        UserAccountHolder holder = UserAccountHolder.getInstance();
        UserAccount account = holder.getUserAccount();
        this.view.setSessionUsername(account.getUsername());
        this.view.setSessionUserType(account.getUserType());
    }

    private void clearResultText(int index) {
        this.view.setResultText("", index);
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl(index));
    }
}
