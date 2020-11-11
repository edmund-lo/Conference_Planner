import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class LoginController {
    private ArrayList<String[]> Accounts;
    private Scanner sc;
    private LoginPresenter lp;

    public LoginController(ArrayList<String[]> Accounts, LoginPresenter lp){
        this.Accounts = Accounts;
        this.sc = new Scanner(System.in);
        this.lp = lp;
    }

    public void CreateAccount(){
        String Username;
        String Password;
        String type;

        int UsernameCheck;
        boolean UsernameSet = false;
        do {
            UsernameCheck = 0;
            lp.EnterUsername();
            Username = sc.nextLine();

            for (String[] users : Accounts){
                if (users[0].equals(Username)){
                    UsernameCheck++;
                }
            }

            if (UsernameCheck == 0)
                UsernameSet = true;
            else{
                lp.UsernameTaken();
                String login = sc.nextLine();
                if (login.equals("login"))
                    login();
            }

        }while(!UsernameSet);

        lp.EnterPassword();
        Password = sc.nextLine();

        lp.AccountType();
        type = sc.nextLine();


        Accounts.add(new String[] {Username, Password, type});
    }

    public void login(){
        String Username;
        String Password;

        boolean UsernameExists;
        boolean PasswordExists;
        String AccountType = "";

        do {
            UsernameExists = false;
            PasswordExists = false;

            lp.EnterUsername();
            Username = sc.nextLine();

            lp.EnterPassword();
            Password = sc.nextLine();

            for (String[] users : Accounts){
                if (users[0].equals(Username)){
                    UsernameExists = true;
                    if (users[1].equals(Password)){
                        PasswordExists = true;
                        AccountType = users[2];
                    }
                }
            }

            if (!(UsernameExists && PasswordExists))
                    lp.IncorrectCredentials();

        }while(!(UsernameExists && PasswordExists));

        switch(AccountType){
            case "o":
                //OrganizerController(Username);
                break;
            case "a":
                //AttendeeController(Username);
                break;
            case "s":
                //SpeakerController(Username);
                break;

        }
    }

}