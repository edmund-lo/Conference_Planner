package common;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public interface IView {
    Stage getStage();
    void setStage(Stage stage);
    Text getResultTextControl();
}
