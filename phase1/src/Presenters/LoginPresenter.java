package Presenters;

public class LoginPresenter {

    public void UsernameTaken(){
        System.out.println("Username is taken! \nType \"login\" to login if account already exists and you wish to " +
                "login. \n If not, press enter to continue");
    }

    public void EnterUsername(){
        System.out.println("Enter Username:");
    }

    public void AccountType(){
        System.out.println("What is the account type? Press:" +
                "\n\"o\" for Entities.Organizer" +
                "\n\"a\" for Entities.Attendee" +
                "\n\"s\" for Entities.Speaker");
    }

    public void EnterPassword(){
        System.out.println("Enter Your Password");
    }

    public void IncorrectCredentials(){
        System.out.println("Incorrect Username or Password.");
    }

    public void New() {
        System.out.println("Type \"New\" to create new account. Press enter to continue");
    }

    public void ValidNumber() {
        System.out.println("Please enter a valid number.");
    }

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