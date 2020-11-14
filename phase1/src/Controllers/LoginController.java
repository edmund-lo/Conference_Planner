package Controllers;

import Presenters.*;
import UseCases.EventManager;
import UseCases.MessageManager;
import UseCases.RoomManager;
import UseCases.UserManager;

import java.util.ArrayList;
import java.util.Scanner;


public class LoginController extends LoginPresenter {
    private ArrayList<String[]> Accounts;
    private Scanner sc;
    protected UserManager um;
    protected RoomManager rm;
    protected MessageManager mm;
    protected EventManager em;
    protected UserController controller;

    public LoginController(UserManager um, RoomManager rm, MessageManager mm, EventManager em){
        this.Accounts = um.getAccountInfo();
        this.sc = new Scanner(System.in);
        this.um = um;
        this.rm = rm;
        this.mm = mm;
        this.em = em;
    }

    public void CreateAccount(){
        String Username;
        String Password;
        String type;

        int UsernameCheck;
        boolean UsernameSet = false;
        do {
            LoginPresenter.CreateAccountP();
            UsernameCheck = 0;
            LoginPresenter.EnterUsername();
            Username = sc.nextLine();

            for (String[] users : Accounts){
                if (users[0].equals(Username)){
                    UsernameCheck++;
                }
            }

            if (UsernameCheck == 0)
                UsernameSet = true;
            else{
                LoginPresenter.UsernameTaken();
                String login = sc.nextLine();
                if (login.equals("login")){
                    login();
                    return;
                }
            }

        }while(!UsernameSet);

        LoginPresenter.EnterPassword();
        Password = sc.nextLine();

        LoginPresenter.AccountType();
        type = sc.nextLine();

        switch(type){
            case "o":
                um.createNewOrganizer(Username, Password);
                Accounts = um.getAccountInfo();
                break;
            case "a":
                um.createNewAttendee(Username, Password);
                Accounts = um.getAccountInfo();
                break;
            case "s":
                um.createNewSpeaker(Username, Password);
                Accounts = um.getAccountInfo();
                break;
            default:
                LoginPresenter.ValidNumber();
        }

    }

    public void login(){
        String Username;
        String Password;

        boolean UsernameExists;
        boolean PasswordExists;
        String AccountType = "";

        do {
            LoginPresenter.Login();
            UsernameExists = false;
            PasswordExists = false;

            LoginPresenter.EnterUsername();
            Username = sc.nextLine();

            LoginPresenter.EnterPassword();
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

            if (!(UsernameExists && PasswordExists)){
                LoginPresenter.IncorrectCredentials();
                LoginPresenter.New();
                if (sc.nextLine().equals("New")){
                    CreateAccount();
                    return;
                }

            }

        }while(!(UsernameExists && PasswordExists));

        switch(AccountType){
            case "o":
                this.controller = new OrganizerController(em, um, rm, mm, Username);
                break;
            case "a":
                this.controller = new AttendeeController(em, um, rm, mm, Username);
                break;
            case "s":
                this.controller = new SpeakerController(em, um, rm, mm, Username);
                break;
            default:
                LoginPresenter.ValidNumber();
        }

        this.em = controller.em;
        this.um = controller.um;
        this.rm = controller.rm;
        this.mm = controller.mm;

    }

}