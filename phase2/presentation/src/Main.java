import javafx.application.Application;
import javafx.stage.Stage;
import util.ComponentFactory;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Project Phase 2");
        ComponentFactory.getInstance().createLoginComponent(primaryStage, "login.fxml");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
