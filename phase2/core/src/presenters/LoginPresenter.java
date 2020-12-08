package presenters;
import org.json.simple.*;

/**
 * A Presenter class that provides output to the user at time of login.
 */

public class LoginPresenter {
    private PresenterUtil<String> pu;

    public LoginPresenter(){
        pu = new PresenterUtil<>();
    }

    /**
     * Lets the user know that the username entered was taken.
     */
    public JSONObject UsernameTaken(){
        return pu.createJSON("error", "Username is taken!");
    }

    /**
     * Lets user know that the username or password that they entered was incorrect.
     */
    public JSONObject IncorrectCredentials(){
        return pu.createJSON("warning", "Incorrect Username or Password.");
    }

    /**
     * Lets the user know that their account has been successfully created.
     */
    public JSONObject AccountMade() {
        return pu.createJSON("success", "Account has been made!");
    }

    /**
     * Username cannot be empty.
     */
    public JSONObject EmptyName() {
       return pu.createJSON("warning", "Username cannot be empty!");
    }

    public void SecurityQuestion1() {
        System.out.println("Question 1: What is your date of birth?");
    }

    public void SecurityQuestion2() {
        System.out.println("Question 2: ");
    }

    public JSONObject NoAccount() {
        return pu.createJSON("error", "No account found");
    }

    public JSONObject IncorrectAnswers() {
        return pu.createJSON("error", "The answers to the security questions were incorrect.");
    }

    public JSONObject EmptyPassword() {
        return pu.createJSON("warning", "Password should have at least 6 characters");
    }

    public JSONObject AccountLocked() {
        return pu.createJSON("warning", "Your account has been locked due to suspicious activity. " +
                "\nPlease contact an Admin to get help.");
    }

    public JSONObject SuccessfulLogin(JSONArray data) {
        return pu.createJSON("success", "Login Successful!", "JSONArray", data);
    }

    public JSONObject noWhiteSpace() {
        return pu.createJSON("warning", "Username cannot contain whitespaces");
    }

    public JSONObject passwordChanged() {
        return pu.createJSON("success", "Your password has successfully been changed");
    }

    public JSONObject incorrectAnswers() {
        return pu.createJSON("warning", "The answers to the security questions were incorrect");
    }

    public JSONObject correctAnswers() {
        return pu.createJSON("success", "The answers to the security questions are correct");
    }
}