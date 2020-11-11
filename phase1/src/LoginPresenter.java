public abstract class LoginPresenter {

    public static void UsernameTaken(){
        System.out.println("Username is taken! \nType \"login\" to login if account already exists and you wish to " +
                "login. \n If not, press enter to continue");
    }

    public static void EnterUsername(){
        System.out.println("Enter Username:");
    }

    public static void AccountType(){
        System.out.println("What is the account type? Press:\n \"o\" for Organizer" +
                "\n\"a\" for Attendee\n\"s\" for Speaker");
    }

    public static void EnterPassword(){
        System.out.println("Enter Your Password");
    }

    public static void IncorrectCredentials(){
        System.out.println("Incorrect Username or Password.");
    }

}