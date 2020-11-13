import java.util.Scanner;

public class ProjectConfig {
    public void run() {
        UserGateway ug = new UserGateway();
        RoomGateway rg = new RoomGateway();
        MessageGateway mg = new MessageGateway();
        EventGateway eg = new EventGateway();

        boolean running = true;
        Scanner sc = new Scanner(System.in);
        while(running) {
            LoginController lc = startSession(ug, rg, mg, eg);
            endSession(ug, rg, mg, eg, lc);
            System.out.println("Type 'exit' to exit program, anything else returns to login screen.");
            if (sc.nextLine().equals("exit"))
                running = false;
        }
        System.exit(0);
    }

    public LoginController startSession(UserGateway ug, RoomGateway rg, MessageGateway mg, EventGateway eg) {
        UserManager um = ug.deserializeData();
        RoomManager rm = rg.deserializeData();
        MessageManager mm = mg.deserializeData();
        EventManager em = eg.deserializeData();

        LoginController lc = new LoginController(um, rm, mm, em);
        lc.login();
        return lc;
    }

    public void endSession(UserGateway ug, RoomGateway rg, MessageGateway mg, EventGateway eg, LoginController lc) {
        ug.serializeData(lc.um);
        rg.serializeData(lc.rm);
        mg.serializeData(lc.mm);
        eg.serializeData(lc.em);
    }
}