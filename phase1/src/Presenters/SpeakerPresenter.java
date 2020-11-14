package Presenters;

public abstract class SpeakerPresenter {
    //writes messages to attendees
    public void writeMessageToAttendees() {
        System.out.println("Enter the message that you would like to send to the Attendees: ");
    }

    public static void displayMenu(){
        System.out.println("Select Option " +
                "\n 0. Logout" +
                "\n 1. Sign Up for Events" +
                "\n 2. Cancel Events" +
                "\n 3. Entities.Message Menu" +
                "\n 4. Entities.Message All Attendees" +
                "\n 5. Entities.Message All Speakers" +
                "\n 6. Schedule a Entities.Speaker" +
                "\n 7. Create a Entities.Room");
    }

}
