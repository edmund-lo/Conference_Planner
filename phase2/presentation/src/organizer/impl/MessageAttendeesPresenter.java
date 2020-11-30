package organizer.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;
import organizer.IMessageUsersPresenter;
import organizer.IMessageUsersView;

import java.util.ArrayList;
import java.util.List;

public class MessageAttendeesPresenter implements IMessageUsersPresenter {
    private IMessageUsersView view;
    private ObservableList<User> users;
    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public MessageAttendeesPresenter(IMessageUsersView view) {
        this.view = view;
        init();
    }

    @Override
    public void sendButtonAction(ActionEvent actionEvent) {
        clearResult();

        if (this.view.getRecipients().equals(""))
            setResult("Recipients field is empty!", false);
        else {
            //call oc.sendAllAttendees method
            String[] recipients = this.view.getRecipients().split(", ");
            setResult("Successfully sent message to selected recipients.", true);
        }
    }

    @Override
    public void selectAllAction(ActionEvent actionEvent) {
        boolean checked = this.view.getSelectAll().isSelected();
        for (User u : this.users)
            u.setChecked(checked);
    }

    @Override
    public void setResult(String result, boolean success) {
        this.view.setResultMsg(result);
        if (!success)
            this.view.getRecipientsField().pseudoClassStateChanged(errorClass, true);
    }

    @Override
    public List<User> getAllUsers() {
        //List<String[]> resultJson = uc.getAllUsers method
        //List<User> userList = UserAdapter.adapt(resultJson);
        List<User> userList = new ArrayList<>();
        return userList;
    }

    @Override
    public void displayUserList() {
        //This callback tell the cell how to bind the user model 'selected' property to the cell, itself
        this.view.getCheckedColumn().setCellValueFactory(param -> param.getValue().getSelected());
        this.view.getFirstNameColumn().setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.view.getLastNameColumn().setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.view.getUsernameColumn().setCellValueFactory(new PropertyValueFactory<>("username"));
        this.view.getUserTable().setItems(this.users);
        this.view.getUserTable().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateRecipientList());
    }


    @Override
    public void updateRecipientList() {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (User u : this.users) {
            if (u.getChecked()) {
                sb.append(prefix);
                prefix = ", ";
                sb.append(u.getUsername());
            }
        }
        this.view.setRecipients(sb.toString());
    }

    @Override
    public void init() {
        this.users = FXCollections.observableArrayList(getAllUsers());
        displayUserList();
        this.view.setSendButtonAction(this::sendButtonAction);
        this.view.setSelectAllAction(this::selectAllAction);
    }

    private void clearResult() {
        this.view.setResultMsg("");
        this.view.getRecipientsField().pseudoClassStateChanged(errorClass, false);
    }
}
