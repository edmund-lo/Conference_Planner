package Gateways;

import UseCases.RoomManager;

import java.io.*;

/**
 * A Gateway class that serializes and deserializes the Room Manager class
 *
 */
public class RoomGateway implements GatewayInterface<RoomManager>, Serializable {
    /**
     * Serial extension file rgt_save which stores serialized and
     * deserialized data
     */
    public String fileName = "rgt_save.ser";

    /**
     * Serializes an inputted Room Manager's list of rooms
     *
     * @param rm the Room Manager class to be serialized
     *
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

    /**
     * Deserializes the given serialized file, and converts it to a Room Manager object
     *
     * @return Room Manager object
     */
    public RoomManager deserializeData() {

        RoomManager rm = new RoomManager();
        try {

            File new_file2 = new File(fileName);
            //
            FileInputStream file2 = new FileInputStream(new_file2);
            ObjectInputStream input = new ObjectInputStream(file2);

            rm = (RoomManager) input.readObject();
            input.close();
            file2.close();
            return rm;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException e){}

        catch (ClassNotFoundException e) {
            System.out.println("Entities.Room Manager Class was not found");
        }
        finally {
            return rm;
        }
    }
}


