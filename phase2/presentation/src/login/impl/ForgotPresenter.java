package login.impl;

//import Controllers.ILoginController
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import login.IForgotPresenter;
import login.IForgotView;
import util.ComponentFactory;
import java.util.Random;

public class ForgotPresenter implements IForgotPresenter {
    private IForgotView view;
    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    public ForgotPresenter(IForgotView view) {
        this.view = view;
        init();
    }

    @Override
    public void backButtonAction(ActionEvent actionEvent) {
        Stage stage = this.view.getStage();
        ComponentFactory.getInstance().createLoginComponent(stage, "login.fxml");
    }

    @Override
    public void recoverButtonAction(ActionEvent actionEvent) {
        clearError();

        if (this.view.getUsername().equals(""))
            setResult("Username cannot be empty!", 0);
        else if (!this.view.getPromptText().equals(this.view.getPromptInput()))
            setResult("Prompts do not match!", 1);
        else {
            // call lc.forgot method
            setResult("Your password has been reset to " + view.getPromptText(), 2);
        }
    }

    @Override
    public String generatePrompt() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    @Override
    public void setResult(String result, int resultId) {
        if (resultId == 0) {
            this.view.getUsernameField().pseudoClassStateChanged(errorClass, true);
        } else if (resultId == 1) {
            this.view.getPromptInputField().pseudoClassStateChanged(errorClass, true);
        }
        this.view.setResultMsg(result);
    }

    @Override
    public void init() {
        this.view.setBackButtonAction(this::backButtonAction);
        this.view.setRecoverButtonAction(this::recoverButtonAction);
        this.view.setPromptText(generatePrompt());
    }

    private void clearError() {
        this.view.setResultMsg("");
        this.view.getUsernameField().pseudoClassStateChanged(errorClass, false);
        this.view.getPromptInputField().pseudoClassStateChanged(errorClass, false);
    }
}
