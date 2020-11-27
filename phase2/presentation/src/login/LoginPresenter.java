package login;

//import Controllers.ILoginController;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import util.ComponentFactory;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;
    private final Stage stage;
    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");
    //private ILoginController lc;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.stage = this.view.getStage();
        init();
    }

    @Override
    public void loginButtonAction(ActionEvent actionEvent) {
        this.view.setErrorMsg("");
        this.view.getUsernameField().pseudoClassStateChanged(this.errorClass, false);
        this.view.getPasswordField().pseudoClassStateChanged(this.errorClass, false);

        if (this.view.getUsername().equals("") || this.view.getPassword().equals(""))
            setError("Fields cannot be empty!");
        else {
            //call lc.login method
        }
    }

    @Override
    public void registerButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoginComponent(this.stage, "register.fxml");
    }

    @Override
    public void forgotPasswordButtonAction(ActionEvent actionEvent) {
        ComponentFactory.getInstance().createLoginComponent(this.stage, "forgot.fxml");
    }

    @Override
    public void setError(String error) {
        this.view.getUsernameField().pseudoClassStateChanged(this.errorClass, true);
        this.view.getPasswordField().pseudoClassStateChanged(this.errorClass, true);
        this.view.setUsername("");
        this.view.setPassword("");
        this.view.setErrorMsg(error);
    }

    @Override
    public void init() {
        this.view.setLoginButtonAction(this::loginButtonAction);
        this.view.setRegisterButtonAction(this::registerButtonAction);
        this.view.setForgotPasswordButtonAction(this::forgotPasswordButtonAction);
    }
}
