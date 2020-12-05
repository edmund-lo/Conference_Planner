package organizer.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.ScheduleEntry;
import org.json.simple.JSONObject;
import organizer.ICreateEventPresenter;
import organizer.ICreateEventView;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateEventPresenter implements ICreateEventPresenter {
    private ICreateEventView view;
    private final ObservableSet<CheckBox> selectedAmenities = FXCollections.observableSet();
    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public CreateEventPresenter(ICreateEventView view) {
        this.view = view;
        init();
    }

    @Override
    public void createEventButtonAction(ActionEvent actionEvent) {
        clearResult();

        //call oc.createEvent method
        this.view.getTitledPane(3).setDisable(true);
        this.view.getTitledPane(1).setDisable(false);
        this.view.getTitledPane(1).setExpanded(true);
    }

    @Override
    public void previousFirstButtonAction(ActionEvent actionEvent) {
        this.view.getTitledPane(2).setDisable(true);
        this.view.getTitledPane(1).setDisable(false);
        this.view.getTitledPane(1).setExpanded(true);
    }

    @Override
    public void previousSecondButtonAction(ActionEvent actionEvent) {
        this.view.getTitledPane(3).setDisable(true);
        this.view.getTitledPane(2).setDisable(false);
        this.view.getTitledPane(2).setExpanded(true);
    }

    @Override
    public void findRoomsButtonAction(ActionEvent actionEvent) {
        this.view.getRoomComboBox().getItems().clear();
        this.view.setStart(LocalDateTime.now());
        this.view.setEnd(LocalDateTime.now());

        this.view.getTableContainer().getChildren().clear();
        this.view.getTitledPane(2).setDisable(false);
        this.view.getTitledPane(1).setDisable(true);
        this.view.getTitledPane(2).setExpanded(true);
    }

    @Override
    public void previewRoomButtonAction(ActionEvent actionEvent) {
        clearError();

        this.view.getTableContainer().getChildren().clear();
        List<ScheduleEntry> roomSchedule = getRoomSchedule();
        displayRoomSchedule(roomSchedule);
    }

    @Override
    public void summaryButtonAction(ActionEvent actionEvent) {
        this.view.setSummaryEventName(this.view.getEventName());
        this.view.setSummaryRoomName(this.view.getRoomName());
        this.view.setSummaryCapacity(this.view.getCapacity());
        this.view.setSummaryStart(this.view.getStart());
        this.view.setSummaryEnd(this.view.getEnd());
        this.view.setSummaryAmenities(amenitiesToString());

        this.view.getTitledPane(2).setDisable(true);
        this.view.getTitledPane(3).setDisable(false);
        this.view.getTitledPane(3).setExpanded(true);
    }

    @Override
    public void setError(String error, int errorId) {
        if (errorId == 0)
            this.view.getRoomComboBox().pseudoClassStateChanged(errorClass, true);
        else if (errorId == 1) {
            this.view.getStartPicker().pseudoClassStateChanged(errorClass, true);
            this.view.getEndPicker().pseudoClassStateChanged(errorClass, true);
        }
        this.view.setErrorMsg(error);
    }

    @Override
    public void setResult(String result) {
        this.view.setResultMsg(result);
    }

    @Override
    public void observeAmenities() {
        configureCheckBox(this.view.getAmenityBox(1));
        configureCheckBox(this.view.getAmenityBox(2));
        configureCheckBox(this.view.getAmenityBox(3));
        configureCheckBox(this.view.getAmenityBox(4));
    }

    @Override
    public List<ScheduleEntry> getRoomSchedule() {
        String roomName = this.view.getRoomName();
        LocalDateTime start = this.view.getStart();
        //JSONObject resultJson = sc.getRoomSchedule(roomName, start)
        //List<ScheduleEntry> schedule = ScheduleAdapter.adapt(resultJson);
        List<ScheduleEntry> schedule = new ArrayList<>();
        return schedule;
    }

    @Override
    public void displayRoomSchedule(List<ScheduleEntry> schedule) {
        Text tableHeader = new Text("Room Schedule");
        TableView<ScheduleEntry> scheduleTable = new TableView<>();

        TableColumn<ScheduleEntry, String> column1 = new TableColumn<>("Event Name");
        TableColumn<ScheduleEntry, LocalDateTime> column2 = new TableColumn<>("Start");
        TableColumn<ScheduleEntry, LocalDateTime> column3 = new TableColumn<>("End");
        DateTimeUtil.getInstance().setScheduleDateTimeCellFactory(column2);
        DateTimeUtil.getInstance().setScheduleDateTimeCellFactory(column3);
        column1.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        column2.setCellValueFactory(new PropertyValueFactory<>("start"));
        column3.setCellValueFactory(new PropertyValueFactory<>("end"));
        scheduleTable.getColumns().add(column1);
        scheduleTable.getColumns().add(column2);
        scheduleTable.getColumns().add(column3);

        ObservableList<ScheduleEntry> observableSchedule = FXCollections.observableArrayList(schedule);
        scheduleTable.setItems(observableSchedule);

        this.view.getTableContainer().getChildren().add(tableHeader);
        this.view.getTableContainer().getChildren().add(scheduleTable);
    }

    @Override
    public void init() {
        observeAmenities();
        this.view.setCreateEventButtonAction(this::createEventButtonAction);
        this.view.setFindRoomsButtonAction(this::findRoomsButtonAction);
        this.view.setPreviewRoomButtonAction(this::previewRoomButtonAction);
        this.view.setPreviousFirstButtonAction(this::previousSecondButtonAction);
        this.view.setPreviousSecondButtonAction(this::previousSecondButtonAction);
        this.view.setSummaryButtonAction(this::summaryButtonAction);
        this.view.getTitledPane(1).setExpanded(true);
    }

    private void configureCheckBox(CheckBox checkBox) {
        if (checkBox.isSelected())
            selectedAmenities.add(checkBox);

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected)
                selectedAmenities.add(checkBox);
            else
                selectedAmenities.remove(checkBox);
        });
    }

    private String amenitiesToString() {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (CheckBox cb : selectedAmenities) {
            if (cb.isSelected()) {
                sb.append(prefix);
                prefix = ", ";
                sb.append(cb.getText());
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private JSONObject constructEventJson() {
        JSONObject queryJson = new JSONObject();
        queryJson.put("eventName", this.view.getEventName());
        queryJson.put("capacity", this.view.getCapacity());
        queryJson.put("roomName", this.view.getRoomName());
        queryJson.put("start", this.view.getStart());
        queryJson.put("end", this.view.getEnd());
        queryJson.put("chairs", this.view.getAmenityBox(1).isSelected());
        queryJson.put("tables", this.view.getAmenityBox(2).isSelected());
        queryJson.put("projector", this.view.getAmenityBox(3).isSelected());
        queryJson.put("sound", this.view.getAmenityBox(4).isSelected());
        return queryJson;
    }

    private void clearError() {
        this.view.setErrorMsg("");
        this.view.getRoomComboBox().pseudoClassStateChanged(errorClass, false);
        this.view.getStartPicker().pseudoClassStateChanged(errorClass, false);
        this.view.getEndPicker().pseudoClassStateChanged(errorClass, false);
    }

    private void clearResult() {
        this.view.setResultMsg("");
    }
}
