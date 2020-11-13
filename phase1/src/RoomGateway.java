
import java.io.*;
import java.io.Serializable;

public class RoomGateway implements GatewayInterface<RoomManager>, Serializable {
    /**
     * Serial extension file rgt_save which stores serialized and
     * deserialized data
     */
    public String fileName = "rgt_save.ser";

    /**
     * This method serializes an inputted Room Manager's list of rooms
     * @param rm
     * @catch FileNotFoundException
     * @catch IOException
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
     * This method deserializes the given serialized file, and converts
     * it to a Room Manager object
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
            file2.close();
            input.close();
            return rm;
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
        finally {
            return rm;
        }
    }
//    public static void main(String[] args) {
//        RoomGateway rg = new RoomGateway();
//        Room r = new Room("");
//        RoomManager rm = new RoomManager();
//        rg.serializeData(rm);
//        RoomManager rr = rg.deserializeData();

}


