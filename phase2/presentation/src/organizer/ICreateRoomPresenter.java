package organizer;

import common.IPresenter;
import javafx.event.ActionEvent;

public interface ICreateRoomPresenter extends IPresenter {
    void createRoomButtonAction(ActionEvent actionEvent);
    void setResultText(String resultText, String status);
    void observeAmenities();
}
