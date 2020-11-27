package common;

import javafx.scene.layout.BorderPane;

public interface ISessionView extends IView {
    BorderPane getRoot();
    void setRoot(BorderPane root);
}
