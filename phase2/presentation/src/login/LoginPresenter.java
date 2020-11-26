package login;

//import Controllers.ILoginController;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import util.ComponentFactory;

public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;
    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");
    //private ILoginController lc;

    public LoginPresenter(ILoginView view) {
        this.view = view;
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
        Stage stage = this.view.getStage();
        ComponentFactory.getInstance().createLoginComponent(stage, "register.fxml");
    }

    @Override
    public void forgotPasswordButtonAction(ActionEvent actionEvent) {
        //Stage stage = this.view.getStage();
        //ComponentFactory.getInstance().createLoginComponent(stage, "forgot.fxml");
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
