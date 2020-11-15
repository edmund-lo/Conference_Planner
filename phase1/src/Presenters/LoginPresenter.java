package Presenters;

/**
 * A Presenter class that provides output to the user at time of login
 */

public class LoginPresenter {
    /**
     * Lets the user know that the username entered was taken.
     */
    public void UsernameTaken(){
        System.out.println("Username is taken! \nType \"login\" to login if account already exists and you wish to " +
                "login. \nIf not, press enter to try a different username");
    }
    //prompts username request
    public void EnterUsername(){
        System.out.println("Enter Username:");
    }
    //prompts type of Account
    public void AccountType(){
        System.out.println("What is the account type? Press:" +
                "\n1. for Organizer" +
                "\n2. for Attendee" +
                "\n3. for Speaker");
    }
    //Outputs request for password
    public void EnterPassword(){
        System.out.println("Enter Your Password:");
    }
    //Prints if incorrect credentials are provided
    public void IncorrectCredentials(){
        System.out.println("Incorrect Username or Password.");
    }
    //Create new account
    public void New() {
        System.out.println("Type \"New\" to create new account. Press enter to continue to the login screen");
    }
    //If invalid number is entered, prints statement
    public void ValidNumber() {
        System.out.println("Please enter a valid number.");
    }
    //Print at login
    public void Login() {
        System.out.println("Login:");
    }

    public void CreateAccountP() {
        System.out.println("Create Account");
    }

    public void AccountMade() {
        System.out.println("Account has been made! Return to the login screen to login to your account.");
    }
}