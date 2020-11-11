import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class LoginController {
    private ArrayList<String[]> Accounts;
    Scanner inputReader;

    public LoginController(ArrayList Accounts){
        this.Accounts = Accounts;
        this.inputReader = new Scanner(System.in);
    }

    public void CreateAccount(){
        String Username;
        String Password;
        String type;

        int UsernameCheck;
        boolean UsernameSet = false;
        do {
            UsernameCheck = 0
            LoginPresenter.EnterUsername();
            UserName = myObj.nextLine();

            for (users in Accounts){
                if (users[0] == Username){
                    UsernameCheck++;
                }
            }

            if (UsernameCheck = 0)
                UsernameSet = true;
            else{
                LoginPresenter.UsernameTaken();
                String login = myObj.nextLine();
                if (login == "login")
                    login();
            }

        }while(!UsernameSet)

        LoginPresenter.EnterPassword();
        Password = myObj.nextLine();

        LoginPresenter.AccountType();
        type = myObj.nextLine();


        Accounts.add(new String[] {Username, Password, type})
    }

    public boolean login(){
        String Username;
        String Password;

        boolean UsernameExists;
        boolean PasswordExists;
        String AccountType;

        do {
            UsernameExists = false;
            PasswordExists = false;

            LoginPresenter.EnterUsername();
            UserName = myObj.nextLine();

            LoginPresenter.EnterPassword();
            Password = myObj.nextLine();

            for (users in Accounts){
                if (users[0] == Username){
                    UsernameExits = true;
                    if (users[1] == Password){
                        PasswordExists = true;
                        AccountType = users[2];
                    }
                }
            }

            if (!(UsernameExists && PasswordExists))
                    LoginPresenter.IncorrectCredentials;

        }while(!(UsernameExists && PasswordExists))

        if (AccountType == "o")
            OrganizerController(Username)
        else if (AccountType == "a")
            AttendeeController(Username)
        else if (AccountType == "s")
            SpeakerController(Username)
    }

}