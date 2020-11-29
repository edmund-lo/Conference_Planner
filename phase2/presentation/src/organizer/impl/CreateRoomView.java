package organizer.impl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import organizer.ICreateRoomView;
import util.NumberTextField;

import java.math.BigDecimal;

public class CreateRoomView implements ICreateRoomView {
    @FXML
    private TextField roomName;
    @FXML
    private NumberTextField capacity;
    @FXML
    private CheckBox amenity1;
    @FXML
    private CheckBox amenity2;
    @FXML
    private CheckBox amenity3;
    @FXML
    private Text createResultMsg;

    @FXML
    public void executeAddCreateRoom(ActionEvent event) {
        if (createRoomButtonAction != null) createRoomButtonAction.handle(event);
    }
    @FXML
    public void initialize() {
        this.presenter = new CreateRoomPresenter(this);
    }

    private CreateRoomPresenter presenter;
    private EventHandler<ActionEvent> createRoomButtonAction;
    private Stage stage;
    private String sessionUsername;

    @Override
    public String getRoomName() {
        return this.roomName.getText();
    }

    @Override
    public void setRoomName(String roomName) {
        this.roomName.setText(roomName);
    }

    @Override
    public int getCapacity() {
        return this.capacity.getNumber().intValue();
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity.setNumber(BigDecimal.valueOf(capacity));
    }

    @Override
    public boolean getAmenity(int index) {
        CheckBox amenity = new CheckBox();
        if (index == 1)
            amenity = getAmenity1();
        else if (index == 2)
            amenity = getAmenity2();
        else if (index == 3)
            amenity = getAmenity3();
        return amenity.isSelected();
    }

    @Override
    public void setAmenity(int index, boolean checked) {
        CheckBox amenity = new CheckBox();
        if (index == 1)
            amenity = getAmenity1();
        else if (index == 2)
            amenity = getAmenity2();
        else if (index == 3)
            amenity = getAmenity3();
        amenity.setSelected(checked);
    }

    @Override
    public void setResultMsg(String result) {
        this.createResultMsg.setText(result);
    }

    @Override
    public TextField getRoomNameField() {
        return this.roomName;
    }

    @Override
    public NumberTextField getCapacityField() {
        return this.capacity;
    }

    @Override
    public CheckBox getAmenity1() {
        return this.amenity1;
    }

    @Override
    public CheckBox getAmenity2() {
        return this.amenity2;
    }

    @Override
    public CheckBox getAmenity3() {
        return this.amenity3;
    }

    @Override
    public EventHandler<ActionEvent> getCreateRoomButtonAction() {
        return this.createRoomButtonAction;
    }

    @Override
    public void setCreateRoomButtonAction(EventHandler<ActionEvent> eventHandler) {
        this.createRoomButtonAction = eventHandler;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public String getSessionUsername() {
        return this.sessionUsername;
    }

    @Override
    public void setSessionUsername(String username) {
        this.sessionUsername = username;
    }
}
