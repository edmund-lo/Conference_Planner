import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.HashMap;
import java.util.List;
public class UserGateway implements GatewayInterface, Serializable {
    /**
     * TODO: JAVADOC
     * @return
     */
    public String fileName = "ugt_save.ser";

    public UserGateway() {

    }

    /**
     * This method serializes an inputted User Manager's data
     * @param um UserManager object
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
            file2.close();
            input.close();
            return um;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException e){
            System.out.println("IO Exception Raised!!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Room Manager Class was not found");
        }
        return um;
    }
//    public static void main(String[] args) {
//        //TODO: CREATE TEST CASES
//        EventGateway eg = new EventGateway();
//        Event e = new Event();
//        EventManager em = new EventManager();
//        em.("RoomManage", r);
//        rg.serializeData(rm);
//        RoomManager rr = rg.deserializeData();
//
//    }

}