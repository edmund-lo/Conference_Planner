public abstract class OrganizerPresenter extends LoginPresenter {

    //interacts with messageAllSpeakers and messageAllAttendees
    public void messageWho(){
        System.out.println("Press the corresponding number for who you would like to receive a message. \nType \"1\" to message all Speakers "
                +
                "\nType \"2\" to message all Attendees. \n If not, press enter to continue");
    }
    //messageAllSpeakersCmd should interact with this
    public void writeMessage() {
        System.out.println("Enter your message: ");
    }
    //interacts with createRoom
    public void createRoom() {
        System.out.println("Enter your room name: ");
    }
    //interacts with scheduleSpeaker in OrganizerController
    public void eventNumber() {
        System.out.println("Enter event number for the talk: ");
    }
    //interacts with scheduleSpeaker in OrganizerController
    public void enterSpeaker() {
        System.out.println("Enter the speaker's name: ");
    }


}