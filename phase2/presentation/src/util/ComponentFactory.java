package util;

import common.ISessionView;
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
    private static ComponentFactory Instance = new ComponentFactory();

    public static ComponentFactory getInstance() {
        return Instance;
    }

    private ComponentFactory() {}

    private Node createRoot(Stage stage, String fxml, BorderPane bp, boolean loggedIn) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/" + fxml));
            Node root = fxmlLoader.load();
            IView view = fxmlLoader.getController();
            view.setStage(stage);
            if (loggedIn) {
                ISessionView sessionView = (ISessionView) view;
                sessionView.setRoot(bp);
            }
            return root;
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createLoginComponent(Stage stage, String fxml) {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene((Parent) createRoot(stage, fxml, null, false), screenSize.getWidth(),
                screenSize.getHeight());
        stage.setScene(scene);
    }

    public void createLoggedInComponent(Stage stage, String fxml) {
        BorderPane root = new BorderPane();
        root.setTop(createRoot(stage, "toolbar.fxml", root, false));
        createCenterComponent(stage, root, fxml);
    }

    public void createCenterComponent(Stage stage, BorderPane root, String fxml) {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        root.setCenter(createRoot(stage, fxml, root,true));
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setScene(scene);
    }
}
