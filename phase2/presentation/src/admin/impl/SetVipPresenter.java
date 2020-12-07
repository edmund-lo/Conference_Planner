package admin.impl;

import adapter.UserAdapter;
import admin.ISetVipPresenter;
import admin.ISetVipView;
import controllers.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.TextResultUtil;

import java.util.List;

public class SetVipPresenter implements ISetVipPresenter {
    private ISetVipView view;
    private AdminController ac;

    public SetVipPresenter(ISetVipView view) {
        this.view = view;
        this.ac = new AdminController(this.view.getSessionUsername());
        init();
    }

    @Override
    public void changeVipButtonAction(ActionEvent actionEvent) {
        clearResultText();

        //JSONObject responseJson = ac.setVip(this.view.getUsername(), this.view.getVip());
        JSONObject responseJson = new JSONObject();
        setResultText(String.valueOf(responseJson.get("result")), String.valueOf(responseJson.get("status")));
        if (responseJson.get("status").equals("success")) init();
    }

    @Override
    public void setResultText(String resultText, String status) {
        this.view.setResultText(resultText);
        TextResultUtil.getInstance().addPseudoClass(status, this.view.getResultTextControl());
    }

    @Override
    public List<User> getAttendeeUsers() {
        //JSONObject responseJson = ac.getAllAttendees();
        JSONObject responseJson = new JSONObject();
        return UserAdapter.getInstance().adaptData((JSONArray) responseJson.get("data"));

    }

    @Override
    public void displayUsers(List<User> attendees) {
        this.view.getUsernameColumn().setCellValueFactory(new PropertyValueFactory<>("username"));
        this.view.getFirstNameColumn().setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.view.getLastNameColumn().setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.view.getVipColumn().setCellValueFactory(param -> param.getValue().vipProperty());
        this.view.getUserTable().setItems(FXCollections.observableList(attendees));
        this.view.getUserTable().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayUserDetails(newValue));
    }

    @Override
    public void displayUserDetails(User attendee) {
        this.view.setUsername(attendee.getUsername());
        this.view.setFirstName(attendee.getFirstName());
        this.view.setLastName(attendee.getLastName());
        this.view.setVip(attendee.isVip() ? "Yes" : "No");
    }

    @Override
    public void init() {
        this.view.setChangeVipButtonAction(this::changeVipButtonAction);
        List<User> attendees = getAttendeeUsers();
        displayUsers(attendees);
    }

    private void clearResultText() {
        this.view.setResultText("");
        TextResultUtil.getInstance().removeAllPseudoClasses(this.view.getResultTextControl());
    }
}
