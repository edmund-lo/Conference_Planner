package Presenters;

public abstract class UserPresenter {
    //type of menu options:
    // go back, sign up for an event with a number
    // cancel an event with a known number
    // message a user, view list of messages
    public void displayMenuOptions() {
        System.out.println("Type \"b\" to go back. \nType \"s\" to sign up for an event.  " +
                "\nType \"c\" to cancel an event. \nType \"m\" to message a user" +
                "\nType \"v\" to view your messages. " +
                "\nType \"e\" to view events in the conference. " +
                "\nType \"vm\" to view all messages received");
    }
    public void signUpForEvent() {
        System.out.println("Enter the number of the event you wish to sign up for: ");
    }
    public void cancelEvent() {
        System.out.println("Enter the number of the event you wish to cancel attendance for: ");
    }

    public void UserDoesNotExist() {
        System.out.println("This user does not exist.");
    }

    public void cannotMessageOrganizer() {
        System.out.println("You cannot message an Entities.Organizer");
    }
    public void InvalidUserEntry() {
        System.out.println("The user you entered was not valid. Enter a valid option.");
    }

    public void alreadySignedUp() {
        System.out.println("You are already signed up for an event at this time.");
    }
    public void eventFullCapacity() {
        System.out.println("The event is already at full capacity");
    }

    public void enterReceiver() {
        System.out.println("Enter the user you wish to message: ");
    }

    public void sendMessage() {
        System.out.println("Write the message that you would like to send: ");
    }

}