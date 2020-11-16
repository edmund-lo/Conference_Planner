package Presenters;

/**
 * A Presenter class that provides output to the user at time of login.
 */

public class LoginPresenter {
    /**
     * Lets the user know that the username entered was taken.
     */
    public void UsernameTaken(){
        System.out.println("Username is taken! \nType \"login\" to login if account already exists and you wish to " +
                "login. \nIf not, press enter to try a different username");
    }

    /**
     * Asks the user to type in a username.
     */
    public void EnterUsername(){
        System.out.println("Enter Username:");
    }

    /**
     * Asks the user to enter the type of account to be created.
     */
    public void AccountType(){
        System.out.println("What is the account type? Press:" +
                "\n1. for Organizer" +
                "\n2. for Attendee" +
                "\n3. for Speaker");
    }
    //Outputs request for password
    /**
     * Asks the user to type in a password.
     */
    public void EnterPassword(){
        System.out.println("Enter Your Password:");
    }
    /**
     * Lets user know that the username or password that they entered was incorrect.
     */
    public void IncorrectCredentials(){
        System.out.println("Incorrect Username or Password.");
    }

    /**
     * Asks the user to enter "New" if they would like to create a new account.
     */
    public void New() {
        System.out.println("Type \"New\" to create new account. Press enter to continue to the login screen");
    }

    /**
     * Asks the user to enter a valid number if the input is not a valid option for them.
     */
    public void ValidNumber() {
        System.out.println("Please enter a valid number.");
    }

    /**
     * Prints login message.
     */
    public void Login() {
        System.out.println("Login:");
    }

    /**
     * Prints create account message.
     */
    public void CreateAccountP() {
        System.out.println("Create Account");
    }
    /**
     * Lets the user know that their account has been successfully created.
     */
    public void AccountMade() {
        System.out.println("Account has been made! Return to the login screen to login to your account.");
    }

    /**
     * Username cannot be empty.
     */
    public void EmptyName() {
        System.out.println("Username cannot be empty!");
    }
}