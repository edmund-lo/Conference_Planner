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

    public void SecurityQuestion3() {
        System.out.println("Question 3: ");
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
}