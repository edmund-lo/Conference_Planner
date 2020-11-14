package Gateways;

import Gateways.GatewayInterface;
import UseCases.RoomManager;

import java.io.*;
import java.io.Serializable;

public class RoomGateway implements GatewayInterface<RoomManager>, Serializable {
    /**
     * Serial extension file rgt_save which stores serialized and
     * deserialized data
     */
    public String fileName = "rgt_save.ser";

    /**
     * This method serializes an inputted Entities.Room Manager's list of rooms
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
        catch (IOException e){}
    }

    /**
     * This method deserializes the given serialized file, and converts
     * it to a Entities.Room Manager object
     * @return Entities.Room Manager object
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
        catch (IOException e){
            System.out.println("IO Exception Raised!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Entities.Room Manager Class was not found");
        }
        finally {
            return rm;
        }
    }
//    public static void main(String[] args) {
//        Gateways.RoomGateway rg = new Gateways.RoomGateway();
//        Entities.Room r = new Entities.Room("");
//        UseCases.RoomManager rm = new UseCases.RoomManager();
//        rg.serializeData(rm);
//        UseCases.RoomManager rr = rg.deserializeData();

}


