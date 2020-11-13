//import java.io.FileOutputStream;
//import java.io.FileInputStream;
//import java.io.Serializable;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.io.File;
//import java.io.ObjectInputStream;
import java.io.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
public class RoomGateway implements GatewayInterface, Serializable {
    /**
     *
     */
    public String fileName = "rgt_save.ser";

    /**
     * This method serializes an inputted Room Manager's list of rooms
     * @param rm
     * @throws IOException
     */
    public void serializeData(RoomManager rm) {

        try {
            File new_file = new File(fileName);
            FileOutputStream store_file = new FileOutputStream(new_file);
            ObjectOutputStream conv_obj = new ObjectOutputStream(store_file);
            conv_obj.writeObject((RoomManager) rm);
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

    public HashMap<String, Room> deserializeData() {

        try {
            RoomManager rm = null;
            File new_file2 = new File(fileName);
            //
            FileInputStream file2 = new FileInputStream(new_file2);
            ObjectInputStream input = new ObjectInputStream(file2);

            rm = (RoomManager) input.readObject();
            file2.close();
            input.close();

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


    }
    public static void main(String[] args) {
        RoomGateway rg = new RoomGateway();
        Room r = new Room();
        RoomManager rm = new RoomManager();
        rm.createRoom("RoomManage", r);
        rg.serializeData(rm);
        RoomManager rr = rg.deserializeData();

    }


}