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

/**
 * Factory class that helps create
 */
public class ComponentFactory {
    private static final ComponentFactory Instance = new ComponentFactory();

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
     * @param stage JavaFX Stage object that all scenes are set in
     * @param fxml String FXML file name to load
     * @param username String username for current logged in session
     * @param accountType String user type for current logged in session
     * @return JavaFX Node object that represents the scene
     */
    private Node createRoot(Stage stage, String fxml, String username, String accountType) {
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

    /**
     * Creates a scene for views when the user has not logged in yet
     * @param stage JavaFX Stage object that all scenes are set in
     * @param fxml String FXML file name to load
     */
    public void createLoggedOutComponent(Stage stage, String fxml) {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene((Parent) createRoot(stage, fxml, "", ""), screenSize.getWidth(),
                screenSize.getHeight());
        stage.setScene(scene);
    }

    /**
     * Creates a scene for views when the user has logged in
     * @param stage JavaFX Stage object that all scenes are set in
     * @param fxml String FXML file name to load
     * @param username String username for current logged in session
     * @param accountType String user type for current logged in session
     */
    public void createLoggedInComponent(Stage stage, String fxml, String username, String accountType) {
        BorderPane root = new BorderPane();
        root.setTop(createRoot(stage, "toolbar.fxml", username, accountType));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        root.setCenter(createRoot(stage, fxml, username, accountType));
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        stage.setScene(scene);
    }
}
