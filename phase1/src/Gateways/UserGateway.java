package Gateways;

import Gateways.GatewayInterface;
import UseCases.UserManager;

import java.io.*;

public class UserGateway implements GatewayInterface<UserManager>, Serializable {
    /**
     * TODO: JAVADOC
     * @return
     */
    public String fileName = "ugt_save.ser";

    public UserGateway() {

    }

    /**
     * This method serializes an inputted Entities.User Manager's data
     * @param um UseCases.UserManager object
     * @catch IOException
     * @catch FileNotFoundException
     */
    public void serializeData(UserManager um) {
        try {
            File new_file = new File(fileName);
            FileOutputStream store_file = new FileOutputStream(new_file);
            ObjectOutputStream conv_obj = new ObjectOutputStream(store_file);
            conv_obj.writeObject((UserManager) um);
            conv_obj.close();
            store_file.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException e){
            System.out.println("IO Exception Raised!!");
        }
    }

    /**
     * TODO: JAVADOC
     * @return
     */
    public UserManager deserializeData() {
        UserManager um = new UserManager();
        try {
            File new_file2 = new File(fileName);
            //
            FileInputStream file2 = new FileInputStream(new_file2);
            ObjectInputStream input = new ObjectInputStream(file2);

            um = (UserManager) input.readObject();
            input.close();
            file2.close();
            return um;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException e){}

        catch (ClassNotFoundException e) {
            System.out.println("Entities.Room Manager Class was not found");
        }
        finally {
            return um;
        }
    }
//    public static void main(String[] args) {
//        //TODO: CREATE TEST CASES
//        Gateways.EventGateway eg = new Gateways.EventGateway();
//        Entities.Event e = new Entities.Event();
//        UseCases.EventManager em = new UseCases.EventManager();
//        em.("RoomManage", r);
//        rg.serializeData(rm);
//        UseCases.RoomManager rr = rg.deserializeData();
//
//    }

}