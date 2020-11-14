package Presenters;

import Presenters.LoginPresenter;

public abstract class MessagePresenter extends LoginPresenter {
    public void writeMessage() {
        System.out.println("Write your message here: ");
    }
    //displays menu options
    public void displayMessageOptions() {
        System.out.println("Press \"1\" if you would like to display all messages. " +
                "\n Press \"2\" if you would like to send a message. \n If not, press enter to continue. ");
    }
    //This needs work, determine if separate method to send to all Speakers and Attendees
    public void specifyReceiverOfMessage() {
        System.out.println("Enter \"1\" if you would like to send a message to a Entities.Speaker. " +
                "\nPress \"2\" to send a message to an Entities.Attendee." +
                "\nPress \"3\" to send a message to All Speakers. \nPress \"4\" to send a message to All Attendees.");
    }
    //display messages
    public void showMessages() {
        System.out.println("Here are your messages.");
    }

}