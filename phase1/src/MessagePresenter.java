public abstract class MessagePresenter extends LoginPresenter {
    public void writeMessage() {
        System.out.println("Write your message here: ");
    }
    public void confirmDisplayMessages() {
        System.out.println("Press \"1\" if you would like to display all messages. \n If not, press enter to continue");
    }
    //This needs work, determine if separate method to send to all Speakers and Attendees
    public void specifyReceiverOfMessage() {
        System.out.println("Enter \"1\" if you would like to send a message to a Speaker. " +
                "\n Press \"2\" to send a message to an Attendee." +
                "Press \"3\" to send a message to All Speakers. \n Press \"4\" to send a message to All Attendees.");
    }
}