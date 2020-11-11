public class ProjectConfig {
    public void run() {
        UserGateway ug = new UserGateway();
        RoomGateway rg = new RoomGateway();
        EventGateway eg = new EventGateway();
        while(true) {
            LoginController lc = startSession(ug, rg, eg);
            endSession(ug, rg, eg, lc);
        }
    }

    public LoginController startSession(UserGateway ug, RoomGateway rg, EventGateway eg) {
        UserManager um = ug.deserializeData();
        RoomManager rm = rg.deserializeData();
        EventManager em = eg.deserializeData();

        LoginController lc = new LoginController(um, rm, em);
        lc.login();
        return lc;
    }

    public void endSession(UserGateway ug, RoomGateway rg, EventGateway eg, LoginController lc) {
        ug.serializeData(lc.um);
        rg.serializeData(lc.rm);
        eg.serializeData(lc.em);
    }
}
