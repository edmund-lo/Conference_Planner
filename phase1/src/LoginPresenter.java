public abstract class LoginPresenter {

    public void UsernameTaken(){
        System.out.println("Username is taken!");
    }

    public void EnterUsername(){
        System.out.println("Enter Username:");
    }

    public void AccountType(){
        System.out.println("What is the account type? Press:\n \"o\" for Organizer\n\"a\" for Attendee\n\"s\" for Speaker");
    }

    public void EnterPassword(){
        System.out.println("Enter Your Password");
    }

    public void IncorrectCredentials(){
        System.out.println("Incorrect Username or Password.");
    }

}