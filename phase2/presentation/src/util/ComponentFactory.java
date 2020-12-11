package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Factory class that helps create
 */
public class ComponentFactory {
    private static final ComponentFactory Instance = new ComponentFactory();
    private final Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

    /**
     * Gets the current instance of a ComponentFactory
     * @return An instance of a ComponentFactory object
     */
    public static ComponentFactory getInstance() {
        return Instance;
    }

    /**
     * Empty ComponentFactory constructor
     */
    private ComponentFactory() {}

    /**
     * Tries to create a root node view with associated username and accountType in the view controller
     * @param fxml String FXML file name to load
     * @return JavaFX Node object that represents the scene
     */
    private Node createRoot(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("fxml/" + fxml));
            return fxmlLoader.load();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stage getStageFromEvent(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        return (Stage) node.getScene().getWindow();
    }


    public void createFirstComponent(Stage stage, String fxml) {
        Scene scene = new Scene((Parent) createRoot(fxml), screenSize.getWidth(), screenSize.getHeight());
        stage.setScene(scene);
    }

    /**
     * Creates a scene for views when the user has not logged in yet
     * @param actionEvent JavaFX ActionEvent object representing user input event
     * @param fxml String FXML file name to load
     */
    public void createLoggedOutComponent(ActionEvent actionEvent, String fxml) {
        createFirstComponent(getStageFromEvent(actionEvent), fxml);
    }

    /**
     * Creates a scene for views when the user has logged in
     * @param actionEvent JavaFX ActionEvent object representing user input event
     * @param fxml String FXML file name to load
     */
    public void createLoggedInComponent(ActionEvent actionEvent, String fxml) {
        BorderPane root = new BorderPane();
        root.setTop(createRoot("toolbar.fxml"));
        root.setCenter(createRoot(fxml));
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        getStageFromEvent(actionEvent).setScene(scene);
    }
}
