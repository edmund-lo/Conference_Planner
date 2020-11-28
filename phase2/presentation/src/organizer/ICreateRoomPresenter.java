package organizer;

import common.IPresenter;
import javafx.event.ActionEvent;

public interface ICreateRoomPresenter extends IPresenter {
    void createRoomButtonAction(ActionEvent actionEvent);
    void setError(String error, int errorId);
    void observeAmenities();
}
