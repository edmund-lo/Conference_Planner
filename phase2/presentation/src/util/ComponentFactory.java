package util;

import common.ILoggedInView;
import common.IView;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;

public class ComponentFactory {
    private static final ComponentFactory Instance = new ComponentFactory();

    public static ComponentFactory getInstance() {
        return Instance;
    }

    private ComponentFactory() {}

    private Node createRoot(Stage stage, String fxml, String username, int accountType) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/" + fxml));
            Node root = fxmlLoader.load();
            IView view = fxmlLoader.getController();
            view.setStage(stage);
            if (!username.equals("")) {
                ILoggedInView loggedInView = (ILoggedInView) view;
                loggedInView.setSessionUsername(username);
                loggedInView.setSessionUserType(accountType);
            }
            return root;
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createLoggedOutComponent(Stage stage, String fxml) {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene((Parent) createRoot(stage, fxml, "", -1), screenSize.getWidth(),
                screenSize.getHeight());
        stage.setScene(scene);
    }

    public void createLoggedInComponent(Stage stage, String fxml, String username, int accountType) {
        BorderPane root = new BorderPane();
        root.setTop(createRoot(stage, "toolbar.fxml", username, accountType));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        root.setCenter(createRoot(stage, fxml, username, accountType));
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setScene(scene);
    }
}
