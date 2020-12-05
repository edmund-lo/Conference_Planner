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
    private Text resultText;

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
    private String sessionUserType;

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
        return getAmenityBox(index).isSelected();
    }

    @Override
    public void setAmenity(int index, boolean checked) {
        CheckBox amenity = new CheckBox();

        amenity.setSelected(checked);
    }

    @Override
    public void setResultMsg(String result) {
        this.resultText.setText(result);
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
    public CheckBox getAmenityBox(int index) {
        CheckBox amenity = null;
        if (index == 1)
            amenity = this.amenity1;
        else if (index == 2)
            amenity =  this.amenity2;
        else if (index == 3)
            amenity =  this.amenity3;
        return amenity;
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
    public Text getResultTextControl() {
        return this.resultText;
    }

    @Override
    public String getSessionUsername() {
        return this.sessionUsername;
    }

    @Override
    public void setSessionUsername(String username) {
        this.sessionUsername = username;
    }

    @Override
    public String getSessionUserType() {
        return this.sessionUserType;
    }

    @Override
    public void setSessionUserType(String userType) {
        this.sessionUserType = userType;
    }
}
